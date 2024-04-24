package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import net.ccc.apps.core.repository.WorkflowStepMessageConfigRepository;
import net.ccc.apps.core.service.queryService.WorkflowStepMessageConfigQueryService;
import net.ccc.apps.core.service.WorkflowStepMessageConfigService;
import net.ccc.apps.core.service.criteria.WorkflowStepMessageConfigCriteria;
import net.ccc.apps.core.service.dto.WorkflowStepMessageConfigDTO;
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
 * REST controller for managing {@link net.ccc.apps.core.domain.WorkflowStepMessageConfig}.
 */
@RestController
@RequestMapping("/api")
public class WorkflowStepMessageConfigResource {

    private final Logger log = LoggerFactory.getLogger(WorkflowStepMessageConfigResource.class);

    private static final String ENTITY_NAME = "appscoreapiWorkflowStepMessageConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkflowStepMessageConfigService workflowStepMessageConfigService;

    private final WorkflowStepMessageConfigRepository workflowStepMessageConfigRepository;

    private final WorkflowStepMessageConfigQueryService workflowStepMessageConfigQueryService;

    public WorkflowStepMessageConfigResource(
        WorkflowStepMessageConfigService workflowStepMessageConfigService,
        WorkflowStepMessageConfigRepository workflowStepMessageConfigRepository,
        WorkflowStepMessageConfigQueryService workflowStepMessageConfigQueryService
    ) {
        this.workflowStepMessageConfigService = workflowStepMessageConfigService;
        this.workflowStepMessageConfigRepository = workflowStepMessageConfigRepository;
        this.workflowStepMessageConfigQueryService = workflowStepMessageConfigQueryService;
    }

    /**
     * {@code POST  /workflow-step-message-configs} : Create a new workflowStepMessageConfig.
     *
     * @param workflowStepMessageConfigDTO the workflowStepMessageConfigDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workflowStepMessageConfigDTO, or with status {@code 400 (Bad Request)} if the workflowStepMessageConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/workflow-step-message-configs")
    public ResponseEntity<WorkflowStepMessageConfigDTO> createWorkflowStepMessageConfig(
        @Valid @RequestBody WorkflowStepMessageConfigDTO workflowStepMessageConfigDTO
    ) throws URISyntaxException {
        log.debug("REST request to save WorkflowStepMessageConfig : {}", workflowStepMessageConfigDTO);
        if (workflowStepMessageConfigDTO.getId() != null) {
            throw new BadRequestAlertException("A new workflowStepMessageConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkflowStepMessageConfigDTO result = workflowStepMessageConfigService.save(workflowStepMessageConfigDTO);
        return ResponseEntity
            .created(new URI("/api/workflow-step-message-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /workflow-step-message-configs/:id} : Updates an existing workflowStepMessageConfig.
     *
     * @param id the id of the workflowStepMessageConfigDTO to save.
     * @param workflowStepMessageConfigDTO the workflowStepMessageConfigDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workflowStepMessageConfigDTO,
     * or with status {@code 400 (Bad Request)} if the workflowStepMessageConfigDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workflowStepMessageConfigDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/workflow-step-message-configs/{id}")
    public ResponseEntity<WorkflowStepMessageConfigDTO> updateWorkflowStepMessageConfig(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody WorkflowStepMessageConfigDTO workflowStepMessageConfigDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WorkflowStepMessageConfig : {}, {}", id, workflowStepMessageConfigDTO);
        if (workflowStepMessageConfigDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workflowStepMessageConfigDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workflowStepMessageConfigRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WorkflowStepMessageConfigDTO result = workflowStepMessageConfigService.save(workflowStepMessageConfigDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workflowStepMessageConfigDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /workflow-step-message-configs/:id} : Partial updates given fields of an existing workflowStepMessageConfig, field will ignore if it is null
     *
     * @param id the id of the workflowStepMessageConfigDTO to save.
     * @param workflowStepMessageConfigDTO the workflowStepMessageConfigDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workflowStepMessageConfigDTO,
     * or with status {@code 400 (Bad Request)} if the workflowStepMessageConfigDTO is not valid,
     * or with status {@code 404 (Not Found)} if the workflowStepMessageConfigDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the workflowStepMessageConfigDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/workflow-step-message-configs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WorkflowStepMessageConfigDTO> partialUpdateWorkflowStepMessageConfig(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody WorkflowStepMessageConfigDTO workflowStepMessageConfigDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WorkflowStepMessageConfig partially : {}, {}", id, workflowStepMessageConfigDTO);
        if (workflowStepMessageConfigDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workflowStepMessageConfigDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workflowStepMessageConfigRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WorkflowStepMessageConfigDTO> result = workflowStepMessageConfigService.partialUpdate(workflowStepMessageConfigDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workflowStepMessageConfigDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /workflow-step-message-configs} : get all the workflowStepMessageConfigs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workflowStepMessageConfigs in body.
     */
    @GetMapping("/workflow-step-message-configs")
    public ResponseEntity<List<WorkflowStepMessageConfigDTO>> getAllWorkflowStepMessageConfigs(
        WorkflowStepMessageConfigCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get WorkflowStepMessageConfigs by criteria: {}", criteria);
        Page<WorkflowStepMessageConfigDTO> page = workflowStepMessageConfigQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /workflow-step-message-configs/count} : count all the workflowStepMessageConfigs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/workflow-step-message-configs/count")
    public ResponseEntity<Long> countWorkflowStepMessageConfigs(WorkflowStepMessageConfigCriteria criteria) {
        log.debug("REST request to count WorkflowStepMessageConfigs by criteria: {}", criteria);
        return ResponseEntity.ok().body(workflowStepMessageConfigQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /workflow-step-message-configs/:id} : get the "id" workflowStepMessageConfig.
     *
     * @param id the id of the workflowStepMessageConfigDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workflowStepMessageConfigDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/workflow-step-message-configs/{id}")
    public ResponseEntity<WorkflowStepMessageConfigDTO> getWorkflowStepMessageConfig(@PathVariable Long id) {
        log.debug("REST request to get WorkflowStepMessageConfig : {}", id);
        Optional<WorkflowStepMessageConfigDTO> workflowStepMessageConfigDTO = workflowStepMessageConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workflowStepMessageConfigDTO);
    }

    /**
     * {@code DELETE  /workflow-step-message-configs/:id} : delete the "id" workflowStepMessageConfig.
     *
     * @param id the id of the workflowStepMessageConfigDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/workflow-step-message-configs/{id}")
    public ResponseEntity<Void> deleteWorkflowStepMessageConfig(@PathVariable Long id) {
        log.debug("REST request to delete WorkflowStepMessageConfig : {}", id);
        workflowStepMessageConfigService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
