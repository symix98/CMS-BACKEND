package net.ccc.apps.campmanage.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import net.ccc.apps.campmanage.domain.RefProject;
import net.ccc.apps.campmanage.repository.RefProjectRepository;
import net.ccc.apps.campmanage.service.RefProjectQueryService;
import net.ccc.apps.campmanage.service.RefProjectService;
import net.ccc.apps.campmanage.service.criteria.RefProjectCriteria;
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
 * REST controller for managing {@link net.ccc.apps.campmanage.domain.RefProject}.
 */
@RestController
@RequestMapping("/api")
public class RefProjectResource {

    private final Logger log = LoggerFactory.getLogger(RefProjectResource.class);

    private static final String ENTITY_NAME = "campmanagRefProject";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RefProjectService refProjectService;

    private final RefProjectRepository refProjectRepository;

    private final RefProjectQueryService refProjectQueryService;

    public RefProjectResource(
        RefProjectService refProjectService,
        RefProjectRepository refProjectRepository,
        RefProjectQueryService refProjectQueryService
    ) {
        this.refProjectService = refProjectService;
        this.refProjectRepository = refProjectRepository;
        this.refProjectQueryService = refProjectQueryService;
    }

    /**
     * {@code POST  /ref-projects} : Create a new refProject.
     *
     * @param refProject the refProject to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new refProject, or with status {@code 400 (Bad Request)} if the refProject has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ref-projects")
    public ResponseEntity<RefProject> createRefProject(@RequestBody RefProject refProject) throws URISyntaxException {
        log.debug("REST request to save RefProject : {}", refProject);
        if (refProject.getId() != null) {
            throw new BadRequestAlertException("A new refProject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefProject result = refProjectService.save(refProject);
        return ResponseEntity
            .created(new URI("/api/ref-projects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ref-projects/:id} : Updates an existing refProject.
     *
     * @param id the id of the refProject to save.
     * @param refProject the refProject to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refProject,
     * or with status {@code 400 (Bad Request)} if the refProject is not valid,
     * or with status {@code 500 (Internal Server Error)} if the refProject couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ref-projects/{id}")
    public ResponseEntity<RefProject> updateRefProject(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RefProject refProject
    ) throws URISyntaxException {
        log.debug("REST request to update RefProject : {}, {}", id, refProject);
        if (refProject.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refProject.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refProjectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RefProject result = refProjectService.update(refProject);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refProject.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ref-projects/:id} : Partial updates given fields of an existing refProject, field will ignore if it is null
     *
     * @param id the id of the refProject to save.
     * @param refProject the refProject to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refProject,
     * or with status {@code 400 (Bad Request)} if the refProject is not valid,
     * or with status {@code 404 (Not Found)} if the refProject is not found,
     * or with status {@code 500 (Internal Server Error)} if the refProject couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ref-projects/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RefProject> partialUpdateRefProject(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RefProject refProject
    ) throws URISyntaxException {
        log.debug("REST request to partial update RefProject partially : {}, {}", id, refProject);
        if (refProject.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refProject.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refProjectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RefProject> result = refProjectService.partialUpdate(refProject);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refProject.getId().toString())
        );
    }

    /**
     * {@code GET  /ref-projects} : get all the refProjects.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of refProjects in body.
     */
    @GetMapping("/ref-projects")
    public ResponseEntity<List<RefProject>> getAllRefProjects(
        RefProjectCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RefProjects by criteria: {}", criteria);
        Page<RefProject> page = refProjectQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ref-projects/count} : count all the refProjects.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ref-projects/count")
    public ResponseEntity<Long> countRefProjects(RefProjectCriteria criteria) {
        log.debug("REST request to count RefProjects by criteria: {}", criteria);
        return ResponseEntity.ok().body(refProjectQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ref-projects/:id} : get the "id" refProject.
     *
     * @param id the id of the refProject to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the refProject, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ref-projects/{id}")
    public ResponseEntity<RefProject> getRefProject(@PathVariable Long id) {
        log.debug("REST request to get RefProject : {}", id);
        Optional<RefProject> refProject = refProjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refProject);
    }

    /**
     * {@code DELETE  /ref-projects/:id} : delete the "id" refProject.
     *
     * @param id the id of the refProject to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ref-projects/{id}")
    public ResponseEntity<Void> deleteRefProject(@PathVariable Long id) {
        log.debug("REST request to delete RefProject : {}", id);
        refProjectService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
