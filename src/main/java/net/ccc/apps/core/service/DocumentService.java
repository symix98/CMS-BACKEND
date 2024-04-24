package net.ccc.apps.core.service;

import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.StringFilter;
import net.ccc.apps.core.domain.enumeration.FormType;
import net.ccc.apps.core.service.criteria.StorageServiceCriteria;
import net.ccc.apps.core.service.criteria.StorageServiceParameterCriteria;
import net.ccc.apps.core.service.dto.StorageServiceParameterDTO;
import org.json.JSONObject;
import org.keycloak.jose.jws.JWSInput;
import org.keycloak.jose.jws.JWSInputException;
import org.keycloak.representations.AccessToken;
import org.keycloak.util.JsonSerialization;
//import com.nimbusds.oauth2.sdk.token.AccessToken;
import net.ccc.apps.core.domain.Document;
import net.ccc.apps.core.domain.enumeration.FormType;
import net.ccc.apps.core.repository.DocumentRepository;
import net.ccc.apps.core.repository.ProjectInfoRepository;
import net.ccc.apps.core.repository.StorageServiceRepository;
import net.ccc.apps.core.service.dto.DocumentDTO;
import net.ccc.apps.core.service.mapper.DocumentMapper;
import net.ccc.apps.core.service.queryService.StorageServiceParameterQueryService;
import net.ccc.apps.core.service.queryService.StorageServiceQueryService;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Service Implementation for managing {@link Document}.
 */
@Service
@Transactional
public class DocumentService {

    private final Logger log = LoggerFactory.getLogger(DocumentService.class);

    private final DocumentRepository documentRepository;

    private final DocumentMapper documentMapper;

    private final StorageServiceRepository storageRepository;

    private final StorageServiceQueryService storageQueryService;

    private final StorageServiceParameterQueryService storageParametersQueryService;

    private final ProjectInfoRepository projectInfoRepository;

    private String token;


    @Value("${storage-server.host}")
    String storageServer;


    public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper, StorageServiceRepository storageRepository, StorageServiceParameterQueryService storageParametersQueryService, StorageServiceQueryService storageQueryService, ProjectInfoRepository projectInfoRepository) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.storageRepository = storageRepository;
        this.storageQueryService = storageQueryService;
        this.storageParametersQueryService = storageParametersQueryService;
        this.projectInfoRepository = projectInfoRepository;
    }

    /**
     * Save a document.
     *
     * @param documentDTO the entity to save.
     * @return the persisted entity.
     */
    @Transactional
    public DocumentDTO save(DocumentDTO documentDTO) throws IOException {
        log.debug("Request to save Document : {}", documentDTO);
        String serviceId = getServiceId();
        documentDTO.setMimeType(documentDTO.getMimeType() != null ? documentDTO.getMimeType() : Files.probeContentType(Paths.get(documentDTO.getFileName())));
        documentDTO.setServiceId(UUID.fromString(serviceId));
        documentDTO.setCreatedBy(UserService.currentLoggedInUser().getEmail());
        documentDTO.setModifiedBy(UserService.currentLoggedInUser().getEmail());
        boolean isImage = isImage(documentDTO.getMimeType());
        documentDTO.setFolder(getFolderName(documentDTO.getServiceId().toString(), documentDTO.getFormType(), isImage));
        if (isImage)
            documentDTO.setFormType(FormType.Image.name());
        uploadFile(getJWTStorageToken(serviceId), documentDTO);
        Document document = documentMapper.toEntity(documentDTO);
        document = documentRepository.save(document);
        return documentMapper.toDto(document);
    }

    private void uploadFile(String token, DocumentDTO document) throws IOException {
        URL url = isImage(document.getMimeType()) ? new URL(storageServer + "api/image") : new URL(storageServer + "api/document");
//        HttpURLConnection con = "https".equals(url.getProtocol()) ? httpsStorageServerUrl(url.toString()) : httpStorageServerUrl(url.toString());
        HttpURLConnection con=getStorageServerConnection(url);
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        con.setRequestProperty("StorageAuthorization", "Bearer " + token);
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "*/*");
        String projectNo = getProjectNo();
        String jsonString =
            "{\"fileByteArray\" : \"" + document.getFile() + "\",    " +
                "\"documentId\" : \"" + document.getDocumentId() + "\", " +
                "\"folder\" : \"" + document.getFolder() + "\", " +
                "\"serviceURI\" : \"" + document.getServiceURI() + "\",  " +
                "\"title\": \"" + document.getTitle() + "\"," +
                "\"description\" : \"" + document.getDescription() + "\"," +
                "\"version\": \"" + document.getVersion() + "\"," +
                "\"mimeType\": \"" + document.getMimeType() + "\"," +
                "\"fileName\": \"" + document.getFileName() + "\"," +
                "\"metaData\":  \"{\\\"project no\\\":\\\"" + projectNo + "\\\"}\"," +
                "\"readers\": \"readers\"," +
                "\"editors\": \"editors\"," +
                "\"createdBy\": \"" + document.getCreatedBy() + "\"," +
                "\"createdDate\": \"" + document.getCreationDate() + "\"," +
                "\"lastModifiedBy\": \"" + document.getModifiedBy() + "\"," +
                "\"lastModifiedDate\": \"" + document.getModificationDate() + "\"}";
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonString.getBytes(Charset.forName("ASCII"));
            os.write(input, 0, input.length);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
        }
    }

    /**
     * Get all the documents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DocumentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Documents");
        return documentRepository.findAll(pageable)
            .map(documentMapper::toDto);
    }

    //to be changed
    public boolean isTokenTimeToLiveSufficient(AccessToken token) {
        return token != null && (token.getExp()  > Instant.now().getEpochSecond());
    }

    public String getJWTStorageToken(String serviceId) throws IOException {
        if (token == null)
            token = requestJWTToken(serviceId);
        else
            try {
                AccessToken accessToken = JsonSerialization.readValue(new JWSInput(token).getContent(), AccessToken.class);
                if (accessToken.isActive() && this.isTokenTimeToLiveSufficient(accessToken)) {
                    return token;
                } else {
                    token = requestJWTToken(serviceId);
                    log.debug("Access token is expired. recall Token");
                }
            } catch (IOException | JWSInputException e) {
                e.printStackTrace();
            }
        return token;
    }


    HttpURLConnection getStorageServerConnection (URL url) throws IOException{
//        HttpURLConnection  con = "https".equals(url.getProtocol()) ? httpsStorageServerUrl(url.toString()) : httpStorageServerUrl(url.toString());
        HttpURLConnection  con =  httpStorageServerUrl(url.toString());
        return con;

    }
    HttpURLConnection httpStorageServerUrl(String urlString) throws IOException {
        HttpURLConnection con;
        con = (HttpURLConnection) new URL(urlString).openConnection();
        return con;
    }

//    HttpsURLConnection httpsStorageServerUrl(String urlString) throws IOException {
//        HttpsURLConnection con;
//
//        URL url = new URL(null, urlString, new sun.net.www.protocol.https.Handler());
//        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
//            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                return null;
//            }
//
//            public void checkClientTrusted(X509Certificate[] certs, String authType) {
//            }
//
//            public void checkServerTrusted(X509Certificate[] certs, String authType) {
//            }
//        }
//        };
//
//        // Install the all-trusting trust manager
//        SSLContext sc = null;
//        try {
//            sc = SSLContext.getInstance("TLS");
//            sc.init(null, trustAllCerts, new java.security.SecureRandom());
//        } catch (NoSuchAlgorithmException | KeyManagementException e) {
//            throw new RuntimeException(e);
//        }
//        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//
//        // Create all-trusting host name verifier
//        HostnameVerifier allHostsValid = new HostnameVerifier() {
//            public boolean verify(String hostname, SSLSession session) {
//
//                return true;
//            }
//        };
//
//        // Install the all-trusting host verifier
//        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
//
//
//        con = (HttpsURLConnection) url.openConnection();
//        return con;
//    }

    public String requestJWTToken(String serviceId) throws IOException {
        AtomicReference<String> secretKey = new AtomicReference();
        storageRepository.findById(serviceId).ifPresent(service -> secretKey.set(
            service.getSecret()));
        String jsonInputString = "{\"serviceId\": \"" + serviceId + "\", \"secretKey\": \"" + secretKey.get() + "\"}";
        String token = ((JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getToken().getTokenValue();

        URL url = new URL(storageServer + "api/auth-token");
        HttpURLConnection con=getStorageServerConnection(url);
//        HttpURLConnection con = "https".equals(url.getProtocol()) ? httpsStorageServerUrl(url.toString()) : httpStorageServerUrl(url.toString());
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Authorization", "Bearer " + token);
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "*/*");
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(Charset.forName("ASCII"));
            os.write(input, 0, input.length);
        } catch (Exception e) {
            throw new BadRequestAlertException("Failed to get token", "storageService", e.getMessage());
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        final JSONObject obj = new JSONObject(content.toString());
        String idToken = obj.getString("id_token");
        return idToken;
    }

    /**
     * Get one document by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DocumentDTO> findOne(String id) {
        log.debug("Request to get Document : {}", id);
        Optional<DocumentDTO> documentDTO = documentRepository.findById(id).map(documentMapper::toDto);
        documentDTO.ifPresent(
            doc -> {
                try {
                    if (isImage(doc.getMimeType()))
                        documentDTO.get().setFile(getThumbnailBase64(doc.getDocumentId()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        );
        return documentDTO;
    }

    /**
     * Delete the document by id.
     *
     * @param id the id of the entity.
     */
    @Transactional
    public void delete(String id) {
        log.debug("Request to delete Document : {}", id);
        Optional<Document> toDelete = documentRepository.findById(id);
        toDelete.ifPresent(document -> {
            documentRepository.delete(document);
            try {
                deleteStorageDocument(document);
            } catch (IOException e) {
                throw new BadRequestAlertException("Failed to delete storage document", "Document", "null");
            }
        });
    }

    public String getServiceId() {
        AtomicReference<String> serviceId = new AtomicReference<>();
        StorageServiceCriteria criteria = new StorageServiceCriteria();
        BooleanFilter filter = new BooleanFilter();
        filter.setEquals(true);
        criteria.setDefaultValue(filter);
        storageQueryService.findByCriteria(criteria).stream().findAny().ifPresent(
            val -> serviceId.set(val.getServiceId())
        );
        return serviceId.get();
    }


    public byte[] download(String documentId) throws IOException {
        String serviceId = getServiceId();

        Optional<Document> doc = documentRepository.findById(documentId);
        if (doc.isPresent()) {
            String folder = doc.get().getFolder();
            String token = getJWTStorageToken(serviceId);
            URL url = isImage(doc.get().getMimeType()) ? new URL(storageServer + "api/image/attachment?folder=" + folder + "&documentId=" + documentId) : new URL(storageServer + "api/document/attachment?folder=" + folder + "&documentId=" + documentId);
//            HttpURLConnection con = "https".equals(url.getProtocol()) ? httpsStorageServerUrl(url.toString()) : httpStorageServerUrl(url.toString());
            HttpURLConnection con=getStorageServerConnection(url);
            //(HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setRequestProperty("StorageAuthorization", "Bearer " + token);
            con.setRequestProperty("Content-Type", doc.get().getMimeType());
            con.setRequestProperty("Accept", "*/*");
            con.disconnect();
            return con.getInputStream().readAllBytes();
        } else throw new RuntimeException("File not found");
    }

    public boolean fileExists(String folder, String documentId) throws IOException {
        String serviceId = getServiceId();
        Optional<Document> doc = documentRepository.findById(documentId);
        if (doc.isPresent()) {
            if (folder == null)
                folder = doc.get().getFolder();
            String token = getJWTStorageToken(serviceId);
            URL url = isImage(doc.get().getMimeType()) ? new URL(storageServer + "api/image?folder=" + folder + "&documentId=" + documentId) : new URL(storageServer + "api/document?folder=" + folder + "&documentId=" + documentId);
            //HttpURLConnection con = "https".equals(url.getProtocol()) ? httpsStorageServerUrl(url.toString()) : httpStorageServerUrl(url.toString());
            HttpURLConnection con=getStorageServerConnection(url);
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setRequestProperty("StorageAuthorization", "Bearer " + token);
            con.setRequestProperty("Content-Type", doc.get().getMimeType());
            con.setRequestProperty("Accept", "*/*");
            con.disconnect();

            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            final JSONObject obj = new JSONObject(content.toString());
            JSONObject docId = obj.getJSONObject("documentId");
            return docId != null;
        } else return false;

    }

    private String getFolderName(String serviceId, String formType, boolean isImage) {
        StorageServiceParameterCriteria criteria = new StorageServiceParameterCriteria();
        StringFilter paramNameFilter = new StringFilter();
        paramNameFilter.setEquals(formType.toLowerCase() + "-folder");
        criteria.setParamName(paramNameFilter);
        StringFilter serviceIdFilter = new StringFilter();
        serviceIdFilter.setEquals(serviceId);
        criteria.setServiceId(serviceIdFilter);
        List<StorageServiceParameterDTO> list = storageParametersQueryService.findByCriteria(criteria);
        String folderName = list.isEmpty() ? null : list.get(0).getParamValue();
        return isImage ? folderName + "/images" : folderName;
    }

    public byte[] getThumbnail(String documentId) throws IOException {
        String serviceId = getServiceId();
        Optional<Document> doc = documentRepository.findById(documentId);
        if (doc.isPresent()) {
            String token = getJWTStorageToken(serviceId);
            URL url = new URL(storageServer + "api/image/thumbnail?folder=" + doc.get().getFolder() + "&documentId=" + documentId);

//            HttpURLConnection con = "https".equals(url.getProtocol()) ? httpsStorageServerUrl(url.toString()) : httpStorageServerUrl(url.toString());
            HttpURLConnection con=getStorageServerConnection(url);

            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setRequestProperty("StorageAuthorization", "Bearer " + token);
            con.setRequestProperty("Content-Type", doc.get().getMimeType());
            con.setRequestProperty("Accept", "*/*");
            con.disconnect();
            return con.getInputStream().readAllBytes();
        } else throw new RuntimeException("File not found");
    }

    private String getThumbnailBase64(String documentId) throws IOException {
        return new String(Base64.getEncoder().encode(getThumbnail(documentId)));
    }

    private String getProjectNo() {
        return projectInfoRepository.findAll().get(0).getContractNumber();
    }

    private void deleteStorageDocument(Document doc) throws IOException {
        String serviceId = getServiceId();
        String token = getJWTStorageToken(serviceId);
        URL url = isImage(doc.getMimeType()) ? new URL(storageServer + "api/image?folder=" + doc.getFolder() + "&documentId=" + doc.getDocumentId()) : new URL(storageServer + "api/document?folder=" + doc.getFolder() + "&documentId=" + doc.getDocumentId() );
        //        HttpURLConnection con = "https".equals(url.getProtocol()) ? httpsStorageServerUrl(url.toString()) : httpStorageServerUrl(url.toString());
        HttpURLConnection con=getStorageServerConnection(url);
        con.setRequestMethod("DELETE");
        con.setDoOutput(true);
        con.setRequestProperty("StorageAuthorization", "Bearer " + token);
        con.setRequestProperty("Content-Type", doc.getMimeType());
        con.setRequestProperty("Accept", "*/*");
        con.disconnect();
        con.getInputStream().readAllBytes();
    }

    private boolean isImage(String mimeType) {
        return mimeType.startsWith("image");
    }
}
