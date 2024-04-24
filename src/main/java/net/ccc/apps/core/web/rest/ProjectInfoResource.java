package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;

import net.ccc.apps.core.repository.ProjectInfoRepository;
import net.ccc.apps.core.service.queryService.ProjectInfoQueryService;
import net.ccc.apps.core.service.ProjectInfoService;
import net.ccc.apps.core.service.criteria.ProjectInfoCriteria;
import net.ccc.apps.core.service.dto.ProjectInfoDTO;
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
 * REST controller for managing {@link net.ccc.apps.core.domain.ProjectInfo}.
 */
@RestController
@RequestMapping("/api")
public class ProjectInfoResource {

    private final Logger log = LoggerFactory.getLogger(ProjectInfoResource.class);

    private static final String ENTITY_NAME = "projectInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectInfoService projectInfoService;

    private final ProjectInfoRepository projectInfoRepository;

    private final ProjectInfoQueryService projectInfoQueryService;

    public ProjectInfoResource(
        ProjectInfoService projectInfoService,
        ProjectInfoRepository projectInfoRepository,
        ProjectInfoQueryService projectInfoQueryService
    ) {
        this.projectInfoService = projectInfoService;
        this.projectInfoRepository = projectInfoRepository;
        this.projectInfoQueryService = projectInfoQueryService;
    }

    /**
     * {@code POST  /project-infos} : Create a new projectInfo.
     *
     * @param projectInfoDTO the projectInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectInfoDTO, or with status {@code 400 (Bad Request)} if the projectInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-infos")
    public ResponseEntity<ProjectInfoDTO> createProjectInfo(@Valid @RequestBody ProjectInfoDTO projectInfoDTO) throws URISyntaxException {
        log.debug("REST request to save ProjectInfo : {}", projectInfoDTO);
        if (projectInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new projectInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectInfoDTO result = projectInfoService.save(projectInfoDTO);
        return ResponseEntity
            .created(new URI("/api/project-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-infos/:id} : Updates an existing projectInfo.
     *
     * @param id the id of the projectInfoDTO to save.
     * @param projectInfoDTO the projectInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectInfoDTO,
     * or with status {@code 400 (Bad Request)} if the projectInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-infos/{id}")
    public ResponseEntity<ProjectInfoDTO> updateProjectInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProjectInfoDTO projectInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectInfo : {}, {}", id, projectInfoDTO);
        if (projectInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProjectInfoDTO result = projectInfoService.save(projectInfoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectInfoDTO.getId().toString()))
            .body(result);
    }

//    /**
//     * {@code PATCH  /project-infos/:id} : Partial updates given fields of an existing projectInfo, field will ignore if it is null
//     *
//     * @param id the id of the projectInfoDTO to save.
//     * @param projectInfoDTO the projectInfoDTO to update.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectInfoDTO,
//     * or with status {@code 400 (Bad Request)} if the projectInfoDTO is not valid,
//     * or with status {@code 404 (Not Found)} if the projectInfoDTO is not found,
//     * or with status {@code 500 (Internal Server Error)} if the projectInfoDTO couldn't be updated.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PatchMapping(value = "/project-infos/{id}", consumes = { "application/json", "application/merge-patch+json" })
//    public ResponseEntity<ProjectInfoDTO> partialUpdateProjectInfo(
//        @PathVariable(value = "id", required = false) final Long id,
//        @NotNull @RequestBody ProjectInfoDTO projectInfoDTO
//    ) throws URISyntaxException {
//        log.debug("REST request to partial update ProjectInfo partially : {}, {}", id, projectInfoDTO);
//        if (projectInfoDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        if (!Objects.equals(id, projectInfoDTO.getId())) {
//            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
//        }
//
//        if (!projectInfoRepository.existsById(id)) {
//            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
//        }
//
//        Optional<ProjectInfoDTO> result = projectInfoService.partialUpdate(projectInfoDTO);
//
//        return ResponseUtil.wrapOrNotFound(
//            result,
//            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, projectInfoDTO.getId().toString())
//        );
//    }

    /**
     * {@code GET  /project-infos} : get all the projectInfos.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectInfos in body.
     */
    @GetMapping("/project-infos")
    public ResponseEntity<List<ProjectInfoDTO>> getAllProjectInfos(
        ProjectInfoCriteria criteria,
        Pageable pageable
    ) {
        log.debug("REST request to get ProjectInfos by criteria: {}", criteria);
        Page<ProjectInfoDTO> page = projectInfoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /project-infos/count} : count all the projectInfos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/project-infos/count")
    public ResponseEntity<Long> countProjectInfos(ProjectInfoCriteria criteria) {
        log.debug("REST request to count ProjectInfos by criteria: {}", criteria);
        return ResponseEntity.ok().body(projectInfoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /project-infos/:id} : get the "id" projectInfo.
     *
     * @param id the id of the projectInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-infos/{id}")
    public ResponseEntity<ProjectInfoDTO> getProjectInfo(@PathVariable Long id) {
        log.debug("REST request to get ProjectInfo : {}", id);
        Optional<ProjectInfoDTO> projectInfoDTO = projectInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectInfoDTO);
    }

    /**
     * {@code DELETE  /project-infos/:id} : delete the "id" projectInfo.
     *
     * @param id the id of the projectInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-infos/{id}")
    public ResponseEntity<Void> deleteProjectInfo(@PathVariable Long id) {
        log.debug("REST request to delete ProjectInfo : {}", id);
        projectInfoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
