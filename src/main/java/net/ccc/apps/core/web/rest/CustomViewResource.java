package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.ccc.apps.core.repository.CustomViewRepository;
import net.ccc.apps.core.service.queryService.CustomViewQueryService;
import net.ccc.apps.core.service.CustomViewService;
import net.ccc.apps.core.service.criteria.CustomViewCriteria;
import net.ccc.apps.core.service.dto.CustomViewDTO;
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
 * REST controller for managing {@link net.ccc.apps.core.domain.CustomView}.
 */
@RestController
@RequestMapping("/api")
public class CustomViewResource {

    private final Logger log = LoggerFactory.getLogger(CustomViewResource.class);

    private static final String ENTITY_NAME = "appscoreapiCustomView";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomViewService customViewService;

    private final CustomViewRepository customViewRepository;

    private final CustomViewQueryService customViewQueryService;

    public CustomViewResource(
        CustomViewService customViewService,
        CustomViewRepository customViewRepository,
        CustomViewQueryService customViewQueryService
    ) {
        this.customViewService = customViewService;
        this.customViewRepository = customViewRepository;
        this.customViewQueryService = customViewQueryService;
    }

    /**
     * {@code POST  /custom-views} : Create a new customView.
     *
     * @param customViewDTO the customViewDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customViewDTO, or with status {@code 400 (Bad Request)} if the customView has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/custom-views")
    public ResponseEntity<CustomViewDTO> createCustomView(@RequestBody CustomViewDTO customViewDTO) throws URISyntaxException {
        log.debug("REST request to save CustomView : {}", customViewDTO);
        if (customViewDTO.getId() != null) {
            throw new BadRequestAlertException("A new customView cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomViewDTO result = customViewService.save(customViewDTO);
        return ResponseEntity
            .created(new URI("/api/custom-views/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /custom-views/:id} : Updates an existing customView.
     *
     * @param id the id of the customViewDTO to save.
     * @param customViewDTO the customViewDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customViewDTO,
     * or with status {@code 400 (Bad Request)} if the customViewDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customViewDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/custom-views/{id}")
    public ResponseEntity<CustomViewDTO> updateCustomView(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CustomViewDTO customViewDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CustomView : {}, {}", id, customViewDTO);
        if (customViewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customViewDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customViewRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CustomViewDTO result = customViewService.save(customViewDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, customViewDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /custom-views/:id} : Partial updates given fields of an existing customView, field will ignore if it is null
     *
     * @param id the id of the customViewDTO to save.
     * @param customViewDTO the customViewDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customViewDTO,
     * or with status {@code 400 (Bad Request)} if the customViewDTO is not valid,
     * or with status {@code 404 (Not Found)} if the customViewDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the customViewDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/custom-views/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CustomViewDTO> partialUpdateCustomView(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CustomViewDTO customViewDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CustomView partially : {}, {}", id, customViewDTO);
        if (customViewDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customViewDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customViewRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CustomViewDTO> result = customViewService.partialUpdate(customViewDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, customViewDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /custom-views} : get all the customViews.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customViews in body.
     */
    @GetMapping("/custom-views")
    public ResponseEntity<List<CustomViewDTO>> getAllCustomViews(
        CustomViewCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get CustomViews by criteria: {}", criteria);
        Page<CustomViewDTO> page = customViewQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /custom-views/count} : count all the customViews.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/custom-views/count")
    public ResponseEntity<Long> countCustomViews(CustomViewCriteria criteria) {
        log.debug("REST request to count CustomViews by criteria: {}", criteria);
        return ResponseEntity.ok().body(customViewQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /custom-views/:id} : get the "id" customView.
     *
     * @param id the id of the customViewDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customViewDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/custom-views/{id}")
    public ResponseEntity<CustomViewDTO> getCustomView(@PathVariable Long id) {
        log.debug("REST request to get CustomView : {}", id);
        Optional<CustomViewDTO> customViewDTO = customViewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customViewDTO);
    }

    /**
     * {@code DELETE  /custom-views/:id} : delete the "id" customView.
     *
     * @param id the id of the customViewDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/custom-views/{id}")
    public ResponseEntity<Void> deleteCustomView(@PathVariable Long id) {
        log.debug("REST request to delete CustomView : {}", id);
        customViewService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
