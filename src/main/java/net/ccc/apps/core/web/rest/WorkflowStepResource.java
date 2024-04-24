//package net.ccc.apps.core.web.rest;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//
//import net.ccc.apps.core.domain.AppUser;
//import net.ccc.apps.core.repository.WorkflowStepRepository;
//import net.ccc.apps.core.service.AppUserService;
//import net.ccc.apps.core.service.WorkflowEngine;
//import net.ccc.apps.core.service.dto.WorkflowProcessDTO;
//import net.ccc.apps.core.service.queryService.WorkflowStepQueryService;
//import net.ccc.apps.core.service.WorkflowStepService;
//import net.ccc.apps.core.service.criteria.WorkflowStepCriteria;
//import net.ccc.apps.core.service.dto.WorkflowStepDTO;
//import net.ccc.apps.errors.BadRequestAlertException;
//import net.ccc.apps.fabricationmanag.domain.Form;
//import net.ccc.apps.fabricationmanag.service.FormService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import tech.jhipster.web.util.HeaderUtil;
//import tech.jhipster.web.util.PaginationUtil;
//import tech.jhipster.web.util.ResponseUtil;
//
///**
// * REST controller for managing {@link net.ccc.apps.core.domain.WorkflowStep}.
// */
//@RestController
//@RequestMapping("/api")
//public class WorkflowStepResource {
//
//    private final Logger log = LoggerFactory.getLogger(WorkflowStepResource.class);
//
//    private static final String ENTITY_NAME = "appscoreapiWorkflowStep";
//
//    @Value("${jhipster.clientApp.name}")
//    private String applicationName;
//    private final FormService formService;
//    private final AppUserService appUserService;
//    private final WorkflowEngine workflowEngine;
//    private final WorkflowStepService workflowStepService;
//
//    private final WorkflowStepRepository workflowStepRepository;
//
//    private final WorkflowStepQueryService workflowStepQueryService;
//
//    public WorkflowStepResource(
//        FormService formService,
//        AppUserService appUserService,
//        WorkflowEngine workflowEngine,
//        WorkflowStepService workflowStepService,
//        WorkflowStepRepository workflowStepRepository,
//        WorkflowStepQueryService workflowStepQueryService
//    ) {
//        this.formService = formService;
//        this.appUserService = appUserService;
//        this.workflowEngine = workflowEngine;
//        this.workflowStepService = workflowStepService;
//        this.workflowStepRepository = workflowStepRepository;
//        this.workflowStepQueryService = workflowStepQueryService;
//    }
//
//    /**
//     * {@code POST  /workflow-steps} : Create a new workflowStep.
//     *
//     * @param workflowStepDTO the workflowStepDTO to create.
//     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workflowStepDTO, or with status {@code 400 (Bad Request)} if the workflowStep has already an ID.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PostMapping("/workflow-steps")
//    public ResponseEntity<WorkflowStepDTO> createWorkflowStep(@Valid @RequestBody WorkflowStepDTO workflowStepDTO)
//        throws URISyntaxException {
//        log.debug("REST request to save WorkflowStep : {}", workflowStepDTO);
//        if (workflowStepDTO.getId() != null) {
//            throw new BadRequestAlertException("A new workflowStep cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        WorkflowStepDTO result = workflowStepService.save(workflowStepDTO);
//        return ResponseEntity
//            .created(new URI("/api/workflow-steps/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * {@code PUT  /workflow-steps/:id} : Updates an existing workflowStep.
//     *
//     * @param id the id of the workflowStepDTO to save.
//     * @param workflowStepDTO the workflowStepDTO to update.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workflowStepDTO,
//     * or with status {@code 400 (Bad Request)} if the workflowStepDTO is not valid,
//     * or with status {@code 500 (Internal Server Error)} if the workflowStepDTO couldn't be updated.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PutMapping("/workflow-steps/{id}")
//    public ResponseEntity<WorkflowStepDTO> updateWorkflowStep(
//        @PathVariable(value = "id", required = false) final Long id,
//        @Valid @RequestBody WorkflowStepDTO workflowStepDTO
//    ) throws URISyntaxException {
//        log.debug("REST request to update WorkflowStep : {}, {}", id, workflowStepDTO);
//        if (workflowStepDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        if (!Objects.equals(id, workflowStepDTO.getId())) {
//            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
//        }
//
//        if (!workflowStepRepository.existsById(id)) {
//            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
//        }
//
//        WorkflowStepDTO result = workflowStepService.save(workflowStepDTO);
//        return ResponseEntity
//            .ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workflowStepDTO.getId().toString()))
//            .body(result);
//    }
//
//    /**
//     * {@code PATCH  /workflow-steps/:id} : Partial updates given fields of an existing workflowStep, field will ignore if it is null
//     *
//     * @param id the id of the workflowStepDTO to save.
//     * @param workflowStepDTO the workflowStepDTO to update.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workflowStepDTO,
//     * or with status {@code 400 (Bad Request)} if the workflowStepDTO is not valid,
//     * or with status {@code 404 (Not Found)} if the workflowStepDTO is not found,
//     * or with status {@code 500 (Internal Server Error)} if the workflowStepDTO couldn't be updated.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PatchMapping(value = "/workflow-steps/{id}", consumes = { "application/json", "application/merge-patch+json" })
//    public ResponseEntity<WorkflowStepDTO> partialUpdateWorkflowStep(
//        @PathVariable(value = "id", required = false) final Long id,
//        @NotNull @RequestBody WorkflowStepDTO workflowStepDTO
//    ) throws URISyntaxException {
//        log.debug("REST request to partial update WorkflowStep partially : {}, {}", id, workflowStepDTO);
//        if (workflowStepDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        if (!Objects.equals(id, workflowStepDTO.getId())) {
//            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
//        }
//
//        if (!workflowStepRepository.existsById(id)) {
//            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
//        }
//
//        Optional<WorkflowStepDTO> result = workflowStepService.partialUpdate(workflowStepDTO);
//
//        return ResponseUtil.wrapOrNotFound(
//            result,
//            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workflowStepDTO.getId().toString())
//        );
//    }
//
//    /**
//     * {@code GET  /workflow-steps} : get all the workflowSteps.
//     *
//     * @param pageable the pagination information.
//     * @param criteria the criteria which the requested entities should match.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workflowSteps in body.
//     */
//    @GetMapping("/workflow-steps")
//    public ResponseEntity<List<WorkflowStepDTO>> getAllWorkflowSteps(
//        WorkflowStepCriteria criteria,
//        @org.springdoc.api.annotations.ParameterObject Pageable pageable
//    ) {
//        log.debug("REST request to get WorkflowSteps by criteria: {}", criteria);
//        Page<WorkflowStepDTO> page = workflowStepQueryService.findByCriteria(criteria, pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
//        return ResponseEntity.ok().headers(headers).body(page.getContent());
//    }
//
//    /**
//     * {@code GET  /workflow-steps/count} : count all the workflowSteps.
//     *
//     * @param criteria the criteria which the requested entities should match.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
//     */
//    @GetMapping("/workflow-steps/count")
//    public ResponseEntity<Long> countWorkflowSteps(WorkflowStepCriteria criteria) {
//        log.debug("REST request to count WorkflowSteps by criteria: {}", criteria);
//        return ResponseEntity.ok().body(workflowStepQueryService.countByCriteria(criteria));
//    }
//
//    /**
//     * {@code GET  /workflow-steps/:id} : get the "id" workflowStep.
//     *
//     * @param id the id of the workflowStepDTO to retrieve.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workflowStepDTO, or with status {@code 404 (Not Found)}.
//     */
//    @GetMapping("/workflow-steps/{id}")
//    public ResponseEntity<WorkflowStepDTO> getWorkflowStep(@PathVariable Long id) {
//        log.debug("REST request to get WorkflowStep : {}", id);
//        Optional<WorkflowStepDTO> workflowStepDTO = workflowStepService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(workflowStepDTO);
//    }
//
//    /**
//     * {@code DELETE  /workflow-steps/:id} : delete the "id" workflowStep.
//     *
//     * @param id the id of the workflowStepDTO to delete.
//     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
//     */
//    @DeleteMapping("/workflow-steps/{id}")
//    public ResponseEntity<Void> deleteWorkflowStep(@PathVariable Long id) {
//        log.debug("REST request to delete WorkflowStep : {}", id);
//        workflowStepService.delete(id);
//        return ResponseEntity
//            .noContent()
//            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
//            .build();
//    }
//
//    @PostMapping("/formflow-steps/{id}/{toEmail}")
//    public ResponseEntity<WorkflowProcessDTO> createWorkflow (@RequestBody WorkflowProcessDTO workflowProcessDTO,
//                                                              @PathVariable(value = "id", required = true) final Long id,
//                                                              @PathVariable(value = "toEmail", required = true) final String toEmail )  throws URISyntaxException {
//        Form form = formService.findOne(id).get();
//        AppUser user = appUserService.findOne(workflowProcessDTO.getInitiatedByUserId()).get();
//        workflowEngine.createWorkflowProcess(id, form.getFormDescription(), workflowProcessDTO.getFormType(), user.getEmail(), toEmail);
//        return ResponseEntity.ok(workflowProcessDTO);
//    }
//
//    @PostMapping("/workflow-approval/{email}/{toEmail}")
//    public ResponseEntity<WorkflowProcessDTO> approveWorkflow(@RequestBody WorkflowProcessDTO workflowProcessDTO,
//                                                              @PathVariable(value = "email", required = true) final String email,
//                                                              @PathVariable(value = "toEmail", required = true) final String toEmail) throws URISyntaxException {
//        try {
//            workflowEngine.createApproveStep(workflowProcessDTO.getFormId(), email, toEmail, workflowProcessDTO.getFormType());
//            return ResponseEntity.ok(workflowProcessDTO);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    @PostMapping("/workflow-revise/{email}/{toEmail}")
//    public ResponseEntity<WorkflowProcessDTO> reviseWorkflow(@RequestBody WorkflowProcessDTO workflowProcessDTO,
//                                                             @PathVariable(value = "email", required = true) final String email,
//                                                             @PathVariable(value = "toEmail", required = true) final String toEmail) throws URISyntaxException {
//        try {
//            workflowEngine.createReviseStep(workflowProcessDTO.getFormId(), email, toEmail, workflowProcessDTO.getFormType());
//            return ResponseEntity.ok(workflowProcessDTO);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//}
