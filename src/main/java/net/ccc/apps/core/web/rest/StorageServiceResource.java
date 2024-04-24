package net.ccc.apps.core.web.rest;

import net.ccc.apps.core.service.StorageServiceService;
import net.ccc.apps.core.service.criteria.StorageServiceCriteria;
import net.ccc.apps.core.service.dto.StorageServiceDTO;
import net.ccc.apps.core.service.queryService.StorageServiceQueryService;
import net.ccc.apps.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.StorageService}.
 */
@RestController
@RequestMapping("/api")
public class StorageServiceResource {

    private final Logger log = LoggerFactory.getLogger(StorageServiceResource.class);

    private static final String ENTITY_NAME = "storageService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StorageServiceService storageServiceService;

    private final StorageServiceQueryService storageServiceQueryService;

    public StorageServiceResource(StorageServiceService storageServiceService, StorageServiceQueryService storageServiceQueryService) {
        this.storageServiceService = storageServiceService;
        this.storageServiceQueryService = storageServiceQueryService;
    }

    /**
     * {@code POST  /storage-services} : Create a new storageService.
     *
     * @param storageServiceDTO the storageServiceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new storageServiceDTO, or with status {@code 400 (Bad Request)} if the storageService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/storage-services")
    public ResponseEntity<StorageServiceDTO> createStorageService(@RequestBody StorageServiceDTO storageServiceDTO) throws URISyntaxException {
        log.debug("REST request to save StorageService : {}", storageServiceDTO);
        if (storageServiceDTO.getServiceId() != null) {
            throw new BadRequestAlertException("A new storageService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StorageServiceDTO result = storageServiceService.save(storageServiceDTO);
        return ResponseEntity.created(new URI("/api/storage-services/" + result.getServiceId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getServiceId()))
            .body(result);
    }

    /**
     * {@code PUT  /storage-services} : Updates an existing storageService.
     *
     * @param storageServiceDTO the storageServiceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated storageServiceDTO,
     * or with status {@code 400 (Bad Request)} if the storageServiceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the storageServiceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/storage-services")
    public ResponseEntity<StorageServiceDTO> updateStorageService(@RequestBody StorageServiceDTO storageServiceDTO) throws URISyntaxException {
        log.debug("REST request to update StorageService : {}", storageServiceDTO);
        if (storageServiceDTO.getServiceId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StorageServiceDTO result = storageServiceService.save(storageServiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, storageServiceDTO.getServiceId()))
            .body(result);
    }

    /**
     * {@code GET  /storage-services} : get all the storageServices.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of storageServices in body.
     */
    @GetMapping("/storage-services")
    public ResponseEntity<List<StorageServiceDTO>> getAllStorageServices(StorageServiceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get StorageServices by criteria: {}", criteria);
        Page<StorageServiceDTO> page = storageServiceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /storage-services/count} : count all the storageServices.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/storage-services/count")
    public ResponseEntity<Long> countStorageServices(StorageServiceCriteria criteria) {
        log.debug("REST request to count StorageServices by criteria: {}", criteria);
        return ResponseEntity.ok().body(storageServiceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /storage-services/:id} : get the "id" storageService.
     *
     * @param id the id of the storageServiceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the storageServiceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/storage-services/{id}")
    public ResponseEntity<StorageServiceDTO> getStorageService(@PathVariable String id) {
        log.debug("REST request to get StorageService : {}", id);
        Optional<StorageServiceDTO> storageServiceDTO = storageServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(storageServiceDTO);
    }

    /**
     * {@code DELETE  /storage-services/:id} : delete the "id" storageService.
     *
     * @param id the id of the storageServiceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/storage-services/{id}")
    public ResponseEntity<Void> deleteStorageService(@PathVariable String id) {
        log.debug("REST request to delete StorageService : {}", id);
        storageServiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
