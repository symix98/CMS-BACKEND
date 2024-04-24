package net.ccc.apps.campmanage.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import net.ccc.apps.campmanage.domain.Catering;
import net.ccc.apps.campmanage.repository.CateringRepository;
import net.ccc.apps.campmanage.service.CateringQueryService;
import net.ccc.apps.campmanage.service.CateringService;
import net.ccc.apps.campmanage.service.criteria.CateringCriteria;
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
 * REST controller for managing {@link net.ccc.apps.campmanage.domain.Catering}.
 */
@RestController
@RequestMapping("/api")
public class CateringResource {

    private final Logger log = LoggerFactory.getLogger(CateringResource.class);

    private static final String ENTITY_NAME = "campmanagCatering";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CateringService cateringService;

    private final CateringRepository cateringRepository;

    private final CateringQueryService cateringQueryService;

    public CateringResource(
        CateringService cateringService,
        CateringRepository cateringRepository,
        CateringQueryService cateringQueryService
    ) {
        this.cateringService = cateringService;
        this.cateringRepository = cateringRepository;
        this.cateringQueryService = cateringQueryService;
    }

    /**
     * {@code POST  /caterings} : Create a new catering.
     *
     * @param catering the catering to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new catering, or with status {@code 400 (Bad Request)} if the catering has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/caterings")
    public ResponseEntity<Catering> createCatering(@RequestBody Catering catering) throws URISyntaxException {
        log.debug("REST request to save Catering : {}", catering);
        if (catering.getId() != null) {
            throw new BadRequestAlertException("A new catering cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Catering result = cateringService.save(catering);
        return ResponseEntity
            .created(new URI("/api/caterings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /caterings/:id} : Updates an existing catering.
     *
     * @param id the id of the catering to save.
     * @param catering the catering to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated catering,
     * or with status {@code 400 (Bad Request)} if the catering is not valid,
     * or with status {@code 500 (Internal Server Error)} if the catering couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/caterings/{id}")
    public ResponseEntity<Catering> updateCatering(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Catering catering
    ) throws URISyntaxException {
        log.debug("REST request to update Catering : {}, {}", id, catering);
        if (catering.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, catering.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cateringRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Catering result = cateringService.update(catering);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, catering.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /caterings/:id} : Partial updates given fields of an existing catering, field will ignore if it is null
     *
     * @param id the id of the catering to save.
     * @param catering the catering to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated catering,
     * or with status {@code 400 (Bad Request)} if the catering is not valid,
     * or with status {@code 404 (Not Found)} if the catering is not found,
     * or with status {@code 500 (Internal Server Error)} if the catering couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/caterings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Catering> partialUpdateCatering(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Catering catering
    ) throws URISyntaxException {
        log.debug("REST request to partial update Catering partially : {}, {}", id, catering);
        if (catering.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, catering.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cateringRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Catering> result = cateringService.partialUpdate(catering);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, catering.getId().toString())
        );
    }

    /**
     * {@code GET  /caterings} : get all the caterings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caterings in body.
     */
    @GetMapping("/caterings")
    public ResponseEntity<List<Catering>> getAllCaterings(
        CateringCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Caterings by criteria: {}", criteria);
        Page<Catering> page = cateringQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /caterings/count} : count all the caterings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/caterings/count")
    public ResponseEntity<Long> countCaterings(CateringCriteria criteria) {
        log.debug("REST request to count Caterings by criteria: {}", criteria);
        return ResponseEntity.ok().body(cateringQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /caterings/:id} : get the "id" catering.
     *
     * @param id the id of the catering to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the catering, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/caterings/{id}")
    public ResponseEntity<Catering> getCatering(@PathVariable Long id) {
        log.debug("REST request to get Catering : {}", id);
        Optional<Catering> catering = cateringService.findOne(id);
        return ResponseUtil.wrapOrNotFound(catering);
    }

    /**
     * {@code DELETE  /caterings/:id} : delete the "id" catering.
     *
     * @param id the id of the catering to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/caterings/{id}")
    public ResponseEntity<Void> deleteCatering(@PathVariable Long id) {
        log.debug("REST request to delete Catering : {}", id);
        cateringService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
