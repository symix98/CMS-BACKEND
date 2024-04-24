package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import net.ccc.apps.core.repository.WorkflowTemplateRepository;
import net.ccc.apps.core.service.queryService.WorkflowTemplateQueryService;
import net.ccc.apps.core.service.WorkflowTemplateService;
import net.ccc.apps.core.service.criteria.WorkflowTemplateCriteria;
import net.ccc.apps.core.service.dto.WorkflowTemplateDTO;
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
 * REST controller for managing {@link net.ccc.apps.core.domain.WorkflowTemplate}.
 */
@RestController
@RequestMapping("/api")
public class WorkflowTemplateResource {

    private final Logger log = LoggerFactory.getLogger(WorkflowTemplateResource.class);

    private static final String ENTITY_NAME = "appscoreapiWorkflowTemplate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkflowTemplateService workflowTemplateService;

    private final WorkflowTemplateRepository workflowTemplateRepository;

    private final WorkflowTemplateQueryService workflowTemplateQueryService;

    public WorkflowTemplateResource(
        WorkflowTemplateService workflowTemplateService,
        WorkflowTemplateRepository workflowTemplateRepository,
        WorkflowTemplateQueryService workflowTemplateQueryService
    ) {
        this.workflowTemplateService = workflowTemplateService;
        this.workflowTemplateRepository = workflowTemplateRepository;
        this.workflowTemplateQueryService = workflowTemplateQueryService;
    }

    /**
     * {@code POST  /workflow-templates} : Create a new workflowTemplate.
     *
     * @param workflowTemplateDTO the workflowTemplateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workflowTemplateDTO, or with status {@code 400 (Bad Request)} if the workflowTemplate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/workflow-templates")
    public ResponseEntity<WorkflowTemplateDTO> createWorkflowTemplate(@Valid @RequestBody WorkflowTemplateDTO workflowTemplateDTO)
        throws URISyntaxException {
        log.debug("REST request to save WorkflowTemplate : {}", workflowTemplateDTO);
        if (workflowTemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new workflowTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WorkflowTemplateDTO result = workflowTemplateService.save(workflowTemplateDTO);
        return ResponseEntity
            .created(new URI("/api/workflow-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /workflow-templates/:id} : Updates an existing workflowTemplate.
     *
     * @param id the id of the workflowTemplateDTO to save.
     * @param workflowTemplateDTO the workflowTemplateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workflowTemplateDTO,
     * or with status {@code 400 (Bad Request)} if the workflowTemplateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workflowTemplateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/workflow-templates/{id}")
    public ResponseEntity<WorkflowTemplateDTO> updateWorkflowTemplate(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody WorkflowTemplateDTO workflowTemplateDTO
    ) throws URISyntaxException {
        log.debug("REST request to update WorkflowTemplate : {}, {}", id, workflowTemplateDTO);
        if (workflowTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workflowTemplateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workflowTemplateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        WorkflowTemplateDTO result = workflowTemplateService.save(workflowTemplateDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workflowTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /workflow-templates/:id} : Partial updates given fields of an existing workflowTemplate, field will ignore if it is null
     *
     * @param id the id of the workflowTemplateDTO to save.
     * @param workflowTemplateDTO the workflowTemplateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workflowTemplateDTO,
     * or with status {@code 400 (Bad Request)} if the workflowTemplateDTO is not valid,
     * or with status {@code 404 (Not Found)} if the workflowTemplateDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the workflowTemplateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/workflow-templates/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WorkflowTemplateDTO> partialUpdateWorkflowTemplate(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody WorkflowTemplateDTO workflowTemplateDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WorkflowTemplate partially : {}, {}", id, workflowTemplateDTO);
        if (workflowTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workflowTemplateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workflowTemplateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WorkflowTemplateDTO> result = workflowTemplateService.partialUpdate(workflowTemplateDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workflowTemplateDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /workflow-templates} : get all the workflowTemplates.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workflowTemplates in body.
     */
    @GetMapping("/workflow-templates")
    public ResponseEntity<List<WorkflowTemplateDTO>> getAllWorkflowTemplates(
        WorkflowTemplateCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get WorkflowTemplates by criteria: {}", criteria);
        Page<WorkflowTemplateDTO> page = workflowTemplateQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /workflow-templates/count} : count all the workflowTemplates.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/workflow-templates/count")
    public ResponseEntity<Long> countWorkflowTemplates(WorkflowTemplateCriteria criteria) {
        log.debug("REST request to count WorkflowTemplates by criteria: {}", criteria);
        return ResponseEntity.ok().body(workflowTemplateQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /workflow-templates/:id} : get the "id" workflowTemplate.
     *
     * @param id the id of the workflowTemplateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workflowTemplateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/workflow-templates/{id}")
    public ResponseEntity<WorkflowTemplateDTO> getWorkflowTemplate(@PathVariable Long id) {
        log.debug("REST request to get WorkflowTemplate : {}", id);
        Optional<WorkflowTemplateDTO> workflowTemplateDTO = workflowTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workflowTemplateDTO);
    }

    /**
     * {@code DELETE  /workflow-templates/:id} : delete the "id" workflowTemplate.
     *
     * @param id the id of the workflowTemplateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/workflow-templates/{id}")
    public ResponseEntity<Void> deleteWorkflowTemplate(@PathVariable Long id) {
        log.debug("REST request to delete WorkflowTemplate : {}", id);
        workflowTemplateService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
