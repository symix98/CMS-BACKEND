package net.ccc.apps.core.web.rest;

import net.ccc.apps.core.service.StorageServiceParameterService;
import net.ccc.apps.core.service.criteria.StorageServiceParameterCriteria;
import net.ccc.apps.core.service.dto.StorageServiceParameterDTO;
import net.ccc.apps.core.service.queryService.StorageServiceParameterQueryService;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.StorageServiceParameter}.
 */
@RestController
@RequestMapping("/api")
public class StorageServiceParameterResource {

    private final Logger log = LoggerFactory.getLogger(StorageServiceParameterResource.class);

    private static final String ENTITY_NAME = "storageServiceParameter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StorageServiceParameterService storageServiceParameterService;

    private final StorageServiceParameterQueryService storageServiceParameterQueryService;

    public StorageServiceParameterResource(StorageServiceParameterService storageServiceParameterService, StorageServiceParameterQueryService storageServiceParameterQueryService) {
        this.storageServiceParameterService = storageServiceParameterService;
        this.storageServiceParameterQueryService = storageServiceParameterQueryService;
    }

    /**
     * {@code POST  /storage-service-parameters} : Create a new storageServiceParameter.
     *
     * @param storageServiceParameterDTO the storageServiceParameterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new storageServiceParameterDTO, or with status {@code 400 (Bad Request)} if the storageServiceParameter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/storage-service-parameters")
    public ResponseEntity<StorageServiceParameterDTO> createStorageServiceParameter(@Valid @RequestBody StorageServiceParameterDTO storageServiceParameterDTO) throws URISyntaxException {
        log.debug("REST request to save StorageServiceParameter : {}", storageServiceParameterDTO);
        if (storageServiceParameterDTO.getId() != null) {
            throw new BadRequestAlertException("A new storageServiceParameter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StorageServiceParameterDTO result = storageServiceParameterService.save(storageServiceParameterDTO);
        return ResponseEntity.created(new URI("/api/storage-service-parameters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /storage-service-parameters} : Updates an existing storageServiceParameter.
     *
     * @param storageServiceParameterDTO the storageServiceParameterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated storageServiceParameterDTO,
     * or with status {@code 400 (Bad Request)} if the storageServiceParameterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the storageServiceParameterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/storage-service-parameters")
    public ResponseEntity<StorageServiceParameterDTO> updateStorageServiceParameter(@Valid @RequestBody StorageServiceParameterDTO storageServiceParameterDTO) throws URISyntaxException {
        log.debug("REST request to update StorageServiceParameter : {}", storageServiceParameterDTO);
        if (storageServiceParameterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StorageServiceParameterDTO result = storageServiceParameterService.save(storageServiceParameterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, storageServiceParameterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /storage-service-parameters} : get all the storageServiceParameters.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of storageServiceParameters in body.
     */
    @GetMapping("/storage-service-parameters")
    public ResponseEntity<List<StorageServiceParameterDTO>> getAllStorageServiceParameters(StorageServiceParameterCriteria criteria, Pageable pageable) {
        log.debug("REST request to get StorageServiceParameters by criteria: {}", criteria);
        Page<StorageServiceParameterDTO> page = storageServiceParameterQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /storage-service-parameters/count} : count all the storageServiceParameters.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/storage-service-parameters/count")
    public ResponseEntity<Long> countStorageServiceParameters(StorageServiceParameterCriteria criteria) {
        log.debug("REST request to count StorageServiceParameters by criteria: {}", criteria);
        return ResponseEntity.ok().body(storageServiceParameterQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /storage-service-parameters/:id} : get the "id" storageServiceParameter.
     *
     * @param id the id of the storageServiceParameterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the storageServiceParameterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/storage-service-parameters/{id}")
    public ResponseEntity<StorageServiceParameterDTO> getStorageServiceParameter(@PathVariable Long id) {
        log.debug("REST request to get StorageServiceParameter : {}", id);
        Optional<StorageServiceParameterDTO> storageServiceParameterDTO = storageServiceParameterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(storageServiceParameterDTO);
    }

    /**
     * {@code DELETE  /storage-service-parameters/:id} : delete the "id" storageServiceParameter.
     *
     * @param id the id of the storageServiceParameterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/storage-service-parameters/{id}")
    public ResponseEntity<Void> deleteStorageServiceParameter(@PathVariable Long id) {
        log.debug("REST request to delete StorageServiceParameter : {}", id);
        storageServiceParameterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
