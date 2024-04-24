package net.ccc.apps.core.web.rest;


import net.ccc.apps.core.service.DocumentService;
import net.ccc.apps.core.service.criteria.DocumentCriteria;
import net.ccc.apps.core.service.dto.DocumentDTO;
import net.ccc.apps.core.service.queryService.DocumentQueryService;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.Document}.
 */
@RestController
@RequestMapping("/api")
public class DocumentResource {

    private final Logger log = LoggerFactory.getLogger(DocumentResource.class);

    private static final String ENTITY_NAME = "document";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentService documentService;

    private final DocumentQueryService documentQueryService;

    public DocumentResource(DocumentService documentService, DocumentQueryService documentQueryService) {
        this.documentService = documentService;
        this.documentQueryService = documentQueryService;
    }

    /**
     * {@code POST  /documents} : Create a new document.
     *
     * @param documentDTO the documentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentDTO, or with status {@code 400 (Bad Request)} if the document has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/documents")
    public ResponseEntity<DocumentDTO> createDocument(@Valid @RequestBody DocumentDTO documentDTO) throws URISyntaxException {
        log.debug("REST request to save Document : {}", documentDTO);
        DocumentDTO result = null;
        try {
            result = documentService.save(documentDTO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.created(new URI("/api/documents/" + result.getDocumentId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getDocumentId()))
            .body(result);
    }


    /**
     * {@code PUT  /documents} : Updates an existing document.
     *
     * @param documentDTO the documentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentDTO,
     * or with status {@code 400 (Bad Request)} if the documentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documentDTO couldn't be updated.
     */
    @PutMapping("/documents")
    public ResponseEntity<DocumentDTO> updateDocument(@Valid @RequestBody DocumentDTO documentDTO) throws IOException {
        log.debug("REST request to update Document : {}", documentDTO);
        if (documentDTO.getDocumentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DocumentDTO result = documentService.save(documentDTO);
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, documentDTO.getDocumentId()))
                .body(result);

    }

    /**
     * {@code GET  /documents} : get all the documents.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documents in body.
     */
    @GetMapping("/documents")
    public ResponseEntity<List<DocumentDTO>> getAllDocuments(DocumentCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Documents by criteria: {}", criteria);
        Page<DocumentDTO> page = documentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /documents/count} : count all the documents.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/documents/count")
    public ResponseEntity<Long> countDocuments(DocumentCriteria criteria) {
        log.debug("REST request to count Documents by criteria: {}", criteria);
        return ResponseEntity.ok().body(documentQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /documents/:id} : get the "id" document.
     *
     * @param id the id of the documentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/documents/{id}")
    public ResponseEntity<DocumentDTO> getDocument(@PathVariable String id) {
        log.debug("REST request to get Document : {}", id);
        Optional<DocumentDTO> documentDTO = documentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentDTO);
    }

    /**
     * {@code DELETE  /documents/:id} : delete the "id" document.
     *
     * @param id the id of the documentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable String id) {
        log.debug("REST request to delete Document : {}", id);
        documentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/documents/auth-storage")
    public ResponseEntity<String> getAuthToken() throws IOException {
        String serviceId = documentService.getServiceId();
        String token = documentService.getJWTStorageToken(serviceId);
        return ResponseEntity.ok().body(token);
    }


    @GetMapping("/documents/download/{documentId}")
    public ResponseEntity<byte[]> download( @PathVariable String documentId) throws IOException {
        byte[] file = documentService.download( documentId);
        return ResponseEntity.ok().body(file);
    }

    @GetMapping("/documents/image/thumbnail/{documentId}")
    public ResponseEntity<byte[]> getThumbnail( @PathVariable String documentId) throws IOException {
        byte[] file = documentService.getThumbnail(documentId);
        return ResponseEntity.ok().body(file);
    }
}
