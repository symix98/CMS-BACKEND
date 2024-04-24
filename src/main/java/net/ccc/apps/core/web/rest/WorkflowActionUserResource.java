package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.ccc.apps.core.repository.WorkflowActionUserRepository;
import net.ccc.apps.core.service.queryService.WorkflowActionUserQueryService;
import net.ccc.apps.core.service.WorkflowActionUserService;
import net.ccc.apps.core.service.criteria.WorkflowActionUserCriteria;
import net.ccc.apps.core.service.dto.WorkflowActionUserDTO;
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
 * REST controller for managing {@link net.ccc.apps.core.domain.WorkflowActionUser}.
 */
@RestController
@RequestMapping("/api")
public class WorkflowActionUserResource {

    private final Logger log = LoggerFactory.getLogger(WorkflowActionUserResource.class);

    private static final String ENTITY_NAME = "appscoreapiWorkflowActionUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkflowActionUserService workflowActionUserService;

    private final WorkflowActionUserRepository workflowActionUserRepository;

    private final WorkflowActionUserQueryService workflowActionUserQueryService;

    public WorkflowActionUserResource(
        WorkflowActionUserService workflowActionUserService,
        WorkflowActionUserRepository workflowActionUserRepository,
        WorkflowActionUserQueryService workflowActionUserQueryService
    ) {
        this.workflowActionUserService = workflowActionUserService;
        this.workflowActionUserRepository = workflowActionUserRepository;
        this.workflowActionUserQueryService = workflowActionUserQueryService;
    }

    /**
     * {@code POST  /workflow-action-users} : Create a new workflowActionUser.
     *
     * @param workflowActionUserDTO the workflowActionUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workflowActionUserDTO, or with status {@code 400 (Bad Request)} if the workflowActionUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/workflow-action-users")
    public ResponseEntity<WorkflowActionUserDTO> createWorkflowActionUser(@RequestBody WorkflowActionUserDTO workflowActionUserDTO)
        throws URISyntaxException {
        log.debug("REST request to save WorkflowActionUser : {}", workflowActionUserDTO);
        if (workflowActionUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new workflowActionUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkflowActionUserDTO result = workflowActionUserService.save(workflowActionUserDTO);
        return ResponseEntity
            .created(new URI("/api/workflow-action-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /workflow-action-users/:id} : Updates an existing workflowActionUser.
     *
     * @param id the id of the workflowActionUserDTO to save.
     * @param workflowActionUserDTO the workflowActionUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workflowActionUserDTO,
     * or with status {@code 400 (Bad Request)} if the workflowActionUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workflowActionUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/workflow-action-users/{id}")
    public ResponseEntity<WorkflowActionUserDTO> updateWorkflowActionUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WorkflowActionUserDTO workflowActionUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WorkflowActionUser : {}, {}", id, workflowActionUserDTO);
        if (workflowActionUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workflowActionUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workflowActionUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WorkflowActionUserDTO result = workflowActionUserService.save(workflowActionUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workflowActionUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /workflow-action-users/:id} : Partial updates given fields of an existing workflowActionUser, field will ignore if it is null
     *
     * @param id the id of the workflowActionUserDTO to save.
     * @param workflowActionUserDTO the workflowActionUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workflowActionUserDTO,
     * or with status {@code 400 (Bad Request)} if the workflowActionUserDTO is not valid,
     * or with status {@code 404 (Not Found)} if the workflowActionUserDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the workflowActionUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/workflow-action-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WorkflowActionUserDTO> partialUpdateWorkflowActionUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WorkflowActionUserDTO workflowActionUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WorkflowActionUser partially : {}, {}", id, workflowActionUserDTO);
        if (workflowActionUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workflowActionUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workflowActionUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WorkflowActionUserDTO> result = workflowActionUserService.partialUpdate(workflowActionUserDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workflowActionUserDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /workflow-action-users} : get all the workflowActionUsers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workflowActionUsers in body.
     */
    @GetMapping("/workflow-action-users")
    public ResponseEntity<List<WorkflowActionUserDTO>> getAllWorkflowActionUsers(
        WorkflowActionUserCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get WorkflowActionUsers by criteria: {}", criteria);
        Page<WorkflowActionUserDTO> page = workflowActionUserQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /workflow-action-users/count} : count all the workflowActionUsers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/workflow-action-users/count")
    public ResponseEntity<Long> countWorkflowActionUsers(WorkflowActionUserCriteria criteria) {
        log.debug("REST request to count WorkflowActionUsers by criteria: {}", criteria);
        return ResponseEntity.ok().body(workflowActionUserQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /workflow-action-users/:id} : get the "id" workflowActionUser.
     *
     * @param id the id of the workflowActionUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workflowActionUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/workflow-action-users/{id}")
    public ResponseEntity<WorkflowActionUserDTO> getWorkflowActionUser(@PathVariable Long id) {
        log.debug("REST request to get WorkflowActionUser : {}", id);
        Optional<WorkflowActionUserDTO> workflowActionUserDTO = workflowActionUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workflowActionUserDTO);
    }

    /**
     * {@code DELETE  /workflow-action-users/:id} : delete the "id" workflowActionUser.
     *
     * @param id the id of the workflowActionUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/workflow-action-users/{id}")
    public ResponseEntity<Void> deleteWorkflowActionUser(@PathVariable Long id) {
        log.debug("REST request to delete WorkflowActionUser : {}", id);
        workflowActionUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
