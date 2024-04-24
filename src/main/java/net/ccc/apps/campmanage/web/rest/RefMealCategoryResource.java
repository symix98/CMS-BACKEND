package net.ccc.apps.campmanage.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import net.ccc.apps.campmanage.domain.RefMealCategory;
import net.ccc.apps.campmanage.repository.RefMealCategoryRepository;
import net.ccc.apps.campmanage.service.RefMealCategoryQueryService;
import net.ccc.apps.campmanage.service.RefMealCategoryService;
import net.ccc.apps.campmanage.service.criteria.RefMealCategoryCriteria;
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
 * REST controller for managing {@link net.ccc.apps.campmanage.domain.RefMealCategory}.
 */
@RestController
@RequestMapping("/api")
public class RefMealCategoryResource {

    private final Logger log = LoggerFactory.getLogger(RefMealCategoryResource.class);

    private static final String ENTITY_NAME = "campmanagRefMealCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RefMealCategoryService refMealCategoryService;

    private final RefMealCategoryRepository refMealCategoryRepository;

    private final RefMealCategoryQueryService refMealCategoryQueryService;

    public RefMealCategoryResource(
        RefMealCategoryService refMealCategoryService,
        RefMealCategoryRepository refMealCategoryRepository,
        RefMealCategoryQueryService refMealCategoryQueryService
    ) {
        this.refMealCategoryService = refMealCategoryService;
        this.refMealCategoryRepository = refMealCategoryRepository;
        this.refMealCategoryQueryService = refMealCategoryQueryService;
    }

    /**
     * {@code POST  /ref-meal-categories} : Create a new refMealCategory.
     *
     * @param refMealCategory the refMealCategory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new refMealCategory, or with status {@code 400 (Bad Request)} if the refMealCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ref-meal-categories")
    public ResponseEntity<RefMealCategory> createRefMealCategory(@RequestBody RefMealCategory refMealCategory) throws URISyntaxException {
        log.debug("REST request to save RefMealCategory : {}", refMealCategory);
        if (refMealCategory.getId() != null) {
            throw new BadRequestAlertException("A new refMealCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefMealCategory result = refMealCategoryService.save(refMealCategory);
        return ResponseEntity
            .created(new URI("/api/ref-meal-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ref-meal-categories/:id} : Updates an existing refMealCategory.
     *
     * @param id the id of the refMealCategory to save.
     * @param refMealCategory the refMealCategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refMealCategory,
     * or with status {@code 400 (Bad Request)} if the refMealCategory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the refMealCategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ref-meal-categories/{id}")
    public ResponseEntity<RefMealCategory> updateRefMealCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RefMealCategory refMealCategory
    ) throws URISyntaxException {
        log.debug("REST request to update RefMealCategory : {}, {}", id, refMealCategory);
        if (refMealCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refMealCategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refMealCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RefMealCategory result = refMealCategoryService.update(refMealCategory);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refMealCategory.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ref-meal-categories/:id} : Partial updates given fields of an existing refMealCategory, field will ignore if it is null
     *
     * @param id the id of the refMealCategory to save.
     * @param refMealCategory the refMealCategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refMealCategory,
     * or with status {@code 400 (Bad Request)} if the refMealCategory is not valid,
     * or with status {@code 404 (Not Found)} if the refMealCategory is not found,
     * or with status {@code 500 (Internal Server Error)} if the refMealCategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ref-meal-categories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RefMealCategory> partialUpdateRefMealCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RefMealCategory refMealCategory
    ) throws URISyntaxException {
        log.debug("REST request to partial update RefMealCategory partially : {}, {}", id, refMealCategory);
        if (refMealCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refMealCategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refMealCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RefMealCategory> result = refMealCategoryService.partialUpdate(refMealCategory);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refMealCategory.getId().toString())
        );
    }

    /**
     * {@code GET  /ref-meal-categories} : get all the refMealCategories.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of refMealCategories in body.
     */
    @GetMapping("/ref-meal-categories")
    public ResponseEntity<List<RefMealCategory>> getAllRefMealCategories(
        RefMealCategoryCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RefMealCategories by criteria: {}", criteria);
        Page<RefMealCategory> page = refMealCategoryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ref-meal-categories/count} : count all the refMealCategories.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ref-meal-categories/count")
    public ResponseEntity<Long> countRefMealCategories(RefMealCategoryCriteria criteria) {
        log.debug("REST request to count RefMealCategories by criteria: {}", criteria);
        return ResponseEntity.ok().body(refMealCategoryQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ref-meal-categories/:id} : get the "id" refMealCategory.
     *
     * @param id the id of the refMealCategory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the refMealCategory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ref-meal-categories/{id}")
    public ResponseEntity<RefMealCategory> getRefMealCategory(@PathVariable Long id) {
        log.debug("REST request to get RefMealCategory : {}", id);
        Optional<RefMealCategory> refMealCategory = refMealCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refMealCategory);
    }

    /**
     * {@code DELETE  /ref-meal-categories/:id} : delete the "id" refMealCategory.
     *
     * @param id the id of the refMealCategory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ref-meal-categories/{id}")
    public ResponseEntity<Void> deleteRefMealCategory(@PathVariable Long id) {
        log.debug("REST request to delete RefMealCategory : {}", id);
        refMealCategoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
