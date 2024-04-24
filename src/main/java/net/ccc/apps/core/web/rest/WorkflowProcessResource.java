package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;

import net.ccc.apps.core.repository.WorkflowProcessRepository;
import net.ccc.apps.core.service.queryService.WorkflowProcessQueryService;
import net.ccc.apps.core.service.WorkflowProcessService;
import net.ccc.apps.core.service.criteria.WorkflowProcessCriteria;
import net.ccc.apps.core.service.dto.WorkflowProcessDTO;
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
 * REST controller for managing {@link net.ccc.apps.core.domain.WorkflowProcess}.
 */
@RestController
@RequestMapping("/api")
public class WorkflowProcessResource {

    private final Logger log = LoggerFactory.getLogger(WorkflowProcessResource.class);

    private static final String ENTITY_NAME = "appscoreapiWorkflowProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkflowProcessService workflowProcessService;

    private final WorkflowProcessRepository workflowProcessRepository;

    private final WorkflowProcessQueryService workflowProcessQueryService;

    public WorkflowProcessResource(
        WorkflowProcessService workflowProcessService,
        WorkflowProcessRepository workflowProcessRepository,
        WorkflowProcessQueryService workflowProcessQueryService
    ) {
        this.workflowProcessService = workflowProcessService;
        this.workflowProcessRepository = workflowProcessRepository;
        this.workflowProcessQueryService = workflowProcessQueryService;
    }

    /**
     * {@code POST  /workflow-processes} : Create a new workflowProcess.
     *
     * @param workflowProcessDTO the workflowProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workflowProcessDTO, or with status {@code 400 (Bad Request)} if the workflowProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/workflow-processes")
    public ResponseEntity<WorkflowProcessDTO> createWorkflowProcess(@Valid @RequestBody WorkflowProcessDTO workflowProcessDTO)
        throws URISyntaxException {
        log.debug("REST request to save WorkflowProcess : {}", workflowProcessDTO);
        if (workflowProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new workflowProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkflowProcessDTO result = workflowProcessService.save(workflowProcessDTO);
        return ResponseEntity
            .created(new URI("/api/workflow-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /workflow-processes/:id} : Updates an existing workflowProcess.
     *
     * @param id the id of the workflowProcessDTO to save.
     * @param workflowProcessDTO the workflowProcessDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workflowProcessDTO,
     * or with status {@code 400 (Bad Request)} if the workflowProcessDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workflowProcessDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/workflow-processes/{id}")
    public ResponseEntity<WorkflowProcessDTO> updateWorkflowProcess(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody WorkflowProcessDTO workflowProcessDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WorkflowProcess : {}, {}", id, workflowProcessDTO);
        if (workflowProcessDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workflowProcessDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workflowProcessRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WorkflowProcessDTO result = workflowProcessService.save(workflowProcessDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workflowProcessDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /workflow-processes} : get all the workflowProcesses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workflowProcesses in body.
     */
    @GetMapping("/workflow-processes")
    public ResponseEntity<List<WorkflowProcessDTO>> getAllWorkflowProcesses(
        WorkflowProcessCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get WorkflowProcesses by criteria: {}", criteria);
        Page<WorkflowProcessDTO> page = workflowProcessQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /workflow-processes/count} : count all the workflowProcesses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/workflow-processes/count")
    public ResponseEntity<Long> countWorkflowProcesses(WorkflowProcessCriteria criteria) {
        log.debug("REST request to count WorkflowProcesses by criteria: {}", criteria);
        return ResponseEntity.ok().body(workflowProcessQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /workflow-processes/:id} : get the "id" workflowProcess.
     *
     * @param id the id of the workflowProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workflowProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/workflow-processes/{id}")
    public ResponseEntity<WorkflowProcessDTO> getWorkflowProcess(@PathVariable Long id) {
        log.debug("REST request to get WorkflowProcess : {}", id);
        Optional<WorkflowProcessDTO> workflowProcessDTO = workflowProcessService.findOneDTO(id);
        return ResponseUtil.wrapOrNotFound(workflowProcessDTO);
    }
    /**
     * {@code DELETE  /workflow-processes/:id} : delete the "id" workflowProcess.
     *
     * @param id the id of the workflowProcessDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/workflow-processes/{id}")
    public ResponseEntity<Void> deleteWorkflowProcess(@PathVariable Long id) {
        log.debug("REST request to delete WorkflowProcess : {}", id);
        workflowProcessService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
