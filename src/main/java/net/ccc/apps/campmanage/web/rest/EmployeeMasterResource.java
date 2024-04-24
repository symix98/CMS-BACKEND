package net.ccc.apps.campmanage.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.ccc.apps.campmanage.domain.EmployeeMaster;
import net.ccc.apps.campmanage.repository.EmployeeMasterRepository;
import net.ccc.apps.campmanage.service.EmployeeMasterQueryService;
import net.ccc.apps.campmanage.service.EmployeeMasterService;
import net.ccc.apps.campmanage.service.criteria.EmployeeMasterCriteria;
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

/**
 * REST controller for managing {@link net.ccc.apps.campmanage.domain.EmployeeMaster}.
 */
@RestController
@RequestMapping("/api")
public class EmployeeMasterResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeMasterResource.class);

    private static final String ENTITY_NAME = "campmanagEmployeeMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmployeeMasterService employeeMasterService;

    private final EmployeeMasterRepository employeeMasterRepository;

    private final EmployeeMasterQueryService employeeMasterQueryService;

    public EmployeeMasterResource(
        EmployeeMasterService employeeMasterService,
        EmployeeMasterRepository employeeMasterRepository,
        EmployeeMasterQueryService employeeMasterQueryService
    ) {
        this.employeeMasterService = employeeMasterService;
        this.employeeMasterRepository = employeeMasterRepository;
        this.employeeMasterQueryService = employeeMasterQueryService;
    }

    /**
     * {@code POST  /employee-masters} : Create a new employeeMaster.
     *
     * @param employeeMaster the employeeMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employeeMaster, or with status {@code 400 (Bad Request)} if the employeeMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/employee-masters")
    public ResponseEntity<EmployeeMaster> createEmployeeMaster(@RequestBody EmployeeMaster employeeMaster) throws URISyntaxException {
        log.debug("REST request to save EmployeeMaster : {}", employeeMaster);
        if (employeeMaster.getId() != null) {
            throw new BadRequestAlertException("A new employeeMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmployeeMaster result = employeeMasterService.save(employeeMaster);
        return ResponseEntity
            .created(new URI("/api/employee-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /employee-masters/:id} : Updates an existing employeeMaster.
     *
     * @param id the id of the employeeMaster to save.
     * @param employeeMaster the employeeMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employeeMaster,
     * or with status {@code 400 (Bad Request)} if the employeeMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the employeeMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/employee-masters/{id}")
    public ResponseEntity<EmployeeMaster> updateEmployeeMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EmployeeMaster employeeMaster
    ) throws URISyntaxException {
        log.debug("REST request to update EmployeeMaster : {}, {}", id, employeeMaster);
        if (employeeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, employeeMaster.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!employeeMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EmployeeMaster result = employeeMasterService.update(employeeMaster);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employeeMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /employee-masters/:id} : Partial updates given fields of an existing employeeMaster, field will ignore if it is null
     *
     * @param id the id of the employeeMaster to save.
     * @param employeeMaster the employeeMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employeeMaster,
     * or with status {@code 400 (Bad Request)} if the employeeMaster is not valid,
     * or with status {@code 404 (Not Found)} if the employeeMaster is not found,
     * or with status {@code 500 (Internal Server Error)} if the employeeMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/employee-masters/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EmployeeMaster> partialUpdateEmployeeMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EmployeeMaster employeeMaster
    ) throws URISyntaxException {
        log.debug("REST request to partial update EmployeeMaster partially : {}, {}", id, employeeMaster);
        if (employeeMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, employeeMaster.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!employeeMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EmployeeMaster> result = employeeMasterService.partialUpdate(employeeMaster);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employeeMaster.getId().toString())
        );
    }

    /**
     * {@code GET  /employee-masters} : get all the employeeMasters.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employeeMasters in body.
     */
    @GetMapping("/employee-masters")
    public ResponseEntity<List<EmployeeMaster>> getAllEmployeeMasters(
        EmployeeMasterCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get EmployeeMasters by criteria: {}", criteria);
        Page<EmployeeMaster> page = employeeMasterQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /employee-masters/count} : count all the employeeMasters.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/employee-masters/count")
    public ResponseEntity<Long> countEmployeeMasters(EmployeeMasterCriteria criteria) {
        log.debug("REST request to count EmployeeMasters by criteria: {}", criteria);
        return ResponseEntity.ok().body(employeeMasterQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /employee-masters/:id} : get the "id" employeeMaster.
     *
     * @param id the id of the employeeMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeeMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/employee-masters/{id}")
    public ResponseEntity<EmployeeMaster> getEmployeeMaster(@PathVariable Long id) {
        log.debug("REST request to get EmployeeMaster : {}", id);
        Optional<EmployeeMaster> employeeMaster = employeeMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(employeeMaster);
    }

    /**
     * {@code DELETE  /employee-masters/:id} : delete the "id" employeeMaster.
     *
     * @param id the id of the employeeMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/employee-masters/{id}")
    public ResponseEntity<Void> deleteEmployeeMaster(@PathVariable Long id) {
        log.debug("REST request to delete EmployeeMaster : {}", id);
        employeeMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
