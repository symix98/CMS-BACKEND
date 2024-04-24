package net.ccc.apps.campmanage.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import net.ccc.apps.campmanage.domain.RefInactiveReason;
import net.ccc.apps.campmanage.repository.RefInactiveReasonRepository;
import net.ccc.apps.campmanage.service.RefInactiveReasonQueryService;
import net.ccc.apps.campmanage.service.RefInactiveReasonService;
import net.ccc.apps.campmanage.service.criteria.RefInactiveReasonCriteria;
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
 * REST controller for managing {@link net.ccc.apps.campmanage.domain.RefInactiveReason}.
 */
@RestController
@RequestMapping("/api")
public class RefInactiveReasonResource {

    private final Logger log = LoggerFactory.getLogger(RefInactiveReasonResource.class);

    private static final String ENTITY_NAME = "campmanagRefInactiveReason";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RefInactiveReasonService refInactiveReasonService;

    private final RefInactiveReasonRepository refInactiveReasonRepository;

    private final RefInactiveReasonQueryService refInactiveReasonQueryService;

    public RefInactiveReasonResource(
        RefInactiveReasonService refInactiveReasonService,
        RefInactiveReasonRepository refInactiveReasonRepository,
        RefInactiveReasonQueryService refInactiveReasonQueryService
    ) {
        this.refInactiveReasonService = refInactiveReasonService;
        this.refInactiveReasonRepository = refInactiveReasonRepository;
        this.refInactiveReasonQueryService = refInactiveReasonQueryService;
    }

    /**
     * {@code POST  /ref-inactive-reasons} : Create a new refInactiveReason.
     *
     * @param refInactiveReason the refInactiveReason to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new refInactiveReason, or with status {@code 400 (Bad Request)} if the refInactiveReason has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ref-inactive-reasons")
    public ResponseEntity<RefInactiveReason> createRefInactiveReason(@RequestBody RefInactiveReason refInactiveReason)
        throws URISyntaxException {
        log.debug("REST request to save RefInactiveReason : {}", refInactiveReason);
        if (refInactiveReason.getId() != null) {
            throw new BadRequestAlertException("A new refInactiveReason cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefInactiveReason result = refInactiveReasonService.save(refInactiveReason);
        return ResponseEntity
            .created(new URI("/api/ref-inactive-reasons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ref-inactive-reasons/:id} : Updates an existing refInactiveReason.
     *
     * @param id the id of the refInactiveReason to save.
     * @param refInactiveReason the refInactiveReason to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refInactiveReason,
     * or with status {@code 400 (Bad Request)} if the refInactiveReason is not valid,
     * or with status {@code 500 (Internal Server Error)} if the refInactiveReason couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ref-inactive-reasons/{id}")
    public ResponseEntity<RefInactiveReason> updateRefInactiveReason(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RefInactiveReason refInactiveReason
    ) throws URISyntaxException {
        log.debug("REST request to update RefInactiveReason : {}, {}", id, refInactiveReason);
        if (refInactiveReason.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refInactiveReason.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refInactiveReasonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RefInactiveReason result = refInactiveReasonService.update(refInactiveReason);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refInactiveReason.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ref-inactive-reasons/:id} : Partial updates given fields of an existing refInactiveReason, field will ignore if it is null
     *
     * @param id the id of the refInactiveReason to save.
     * @param refInactiveReason the refInactiveReason to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refInactiveReason,
     * or with status {@code 400 (Bad Request)} if the refInactiveReason is not valid,
     * or with status {@code 404 (Not Found)} if the refInactiveReason is not found,
     * or with status {@code 500 (Internal Server Error)} if the refInactiveReason couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ref-inactive-reasons/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RefInactiveReason> partialUpdateRefInactiveReason(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RefInactiveReason refInactiveReason
    ) throws URISyntaxException {
        log.debug("REST request to partial update RefInactiveReason partially : {}, {}", id, refInactiveReason);
        if (refInactiveReason.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refInactiveReason.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refInactiveReasonRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RefInactiveReason> result = refInactiveReasonService.partialUpdate(refInactiveReason);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refInactiveReason.getId().toString())
        );
    }

    /**
     * {@code GET  /ref-inactive-reasons} : get all the refInactiveReasons.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of refInactiveReasons in body.
     */
    @GetMapping("/ref-inactive-reasons")
    public ResponseEntity<List<RefInactiveReason>> getAllRefInactiveReasons(
        RefInactiveReasonCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RefInactiveReasons by criteria: {}", criteria);
        Page<RefInactiveReason> page = refInactiveReasonQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ref-inactive-reasons/count} : count all the refInactiveReasons.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ref-inactive-reasons/count")
    public ResponseEntity<Long> countRefInactiveReasons(RefInactiveReasonCriteria criteria) {
        log.debug("REST request to count RefInactiveReasons by criteria: {}", criteria);
        return ResponseEntity.ok().body(refInactiveReasonQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ref-inactive-reasons/:id} : get the "id" refInactiveReason.
     *
     * @param id the id of the refInactiveReason to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the refInactiveReason, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ref-inactive-reasons/{id}")
    public ResponseEntity<RefInactiveReason> getRefInactiveReason(@PathVariable Long id) {
        log.debug("REST request to get RefInactiveReason : {}", id);
        Optional<RefInactiveReason> refInactiveReason = refInactiveReasonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refInactiveReason);
    }

    /**
     * {@code DELETE  /ref-inactive-reasons/:id} : delete the "id" refInactiveReason.
     *
     * @param id the id of the refInactiveReason to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ref-inactive-reasons/{id}")
    public ResponseEntity<Void> deleteRefInactiveReason(@PathVariable Long id) {
        log.debug("REST request to delete RefInactiveReason : {}", id);
        refInactiveReasonService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
