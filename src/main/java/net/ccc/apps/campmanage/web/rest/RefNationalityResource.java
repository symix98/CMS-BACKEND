package net.ccc.apps.campmanage.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import net.ccc.apps.campmanage.domain.RefNationality;
import net.ccc.apps.campmanage.repository.RefNationalityRepository;
import net.ccc.apps.campmanage.service.RefNationalityQueryService;
import net.ccc.apps.campmanage.service.RefNationalityService;
import net.ccc.apps.campmanage.service.criteria.RefNationalityCriteria;
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
 * REST controller for managing {@link net.ccc.apps.campmanage.domain.RefNationality}.
 */
@RestController
@RequestMapping("/api")
public class RefNationalityResource {

    private final Logger log = LoggerFactory.getLogger(RefNationalityResource.class);

    private static final String ENTITY_NAME = "campmanagRefNationality";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RefNationalityService refNationalityService;

    private final RefNationalityRepository refNationalityRepository;

    private final RefNationalityQueryService refNationalityQueryService;

    public RefNationalityResource(
        RefNationalityService refNationalityService,
        RefNationalityRepository refNationalityRepository,
        RefNationalityQueryService refNationalityQueryService
    ) {
        this.refNationalityService = refNationalityService;
        this.refNationalityRepository = refNationalityRepository;
        this.refNationalityQueryService = refNationalityQueryService;
    }

    /**
     * {@code POST  /ref-nationalities} : Create a new refNationality.
     *
     * @param refNationality the refNationality to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new refNationality, or with status {@code 400 (Bad Request)} if the refNationality has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ref-nationalities")
    public ResponseEntity<RefNationality> createRefNationality(@RequestBody RefNationality refNationality) throws URISyntaxException {
        log.debug("REST request to save RefNationality : {}", refNationality);
        if (refNationality.getId() != null) {
            throw new BadRequestAlertException("A new refNationality cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefNationality result = refNationalityService.save(refNationality);
        return ResponseEntity
            .created(new URI("/api/ref-nationalities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ref-nationalities/:id} : Updates an existing refNationality.
     *
     * @param id the id of the refNationality to save.
     * @param refNationality the refNationality to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refNationality,
     * or with status {@code 400 (Bad Request)} if the refNationality is not valid,
     * or with status {@code 500 (Internal Server Error)} if the refNationality couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ref-nationalities/{id}")
    public ResponseEntity<RefNationality> updateRefNationality(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RefNationality refNationality
    ) throws URISyntaxException {
        log.debug("REST request to update RefNationality : {}, {}", id, refNationality);
        if (refNationality.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refNationality.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refNationalityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RefNationality result = refNationalityService.update(refNationality);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refNationality.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ref-nationalities/:id} : Partial updates given fields of an existing refNationality, field will ignore if it is null
     *
     * @param id the id of the refNationality to save.
     * @param refNationality the refNationality to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refNationality,
     * or with status {@code 400 (Bad Request)} if the refNationality is not valid,
     * or with status {@code 404 (Not Found)} if the refNationality is not found,
     * or with status {@code 500 (Internal Server Error)} if the refNationality couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ref-nationalities/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RefNationality> partialUpdateRefNationality(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RefNationality refNationality
    ) throws URISyntaxException {
        log.debug("REST request to partial update RefNationality partially : {}, {}", id, refNationality);
        if (refNationality.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refNationality.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refNationalityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RefNationality> result = refNationalityService.partialUpdate(refNationality);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refNationality.getId().toString())
        );
    }

    /**
     * {@code GET  /ref-nationalities} : get all the refNationalities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of refNationalities in body.
     */
    @GetMapping("/ref-nationalities")
    public ResponseEntity<List<RefNationality>> getAllRefNationalities(
        RefNationalityCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RefNationalities by criteria: {}", criteria);
        Page<RefNationality> page = refNationalityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ref-nationalities/count} : count all the refNationalities.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ref-nationalities/count")
    public ResponseEntity<Long> countRefNationalities(RefNationalityCriteria criteria) {
        log.debug("REST request to count RefNationalities by criteria: {}", criteria);
        return ResponseEntity.ok().body(refNationalityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ref-nationalities/:id} : get the "id" refNationality.
     *
     * @param id the id of the refNationality to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the refNationality, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ref-nationalities/{id}")
    public ResponseEntity<RefNationality> getRefNationality(@PathVariable Long id) {
        log.debug("REST request to get RefNationality : {}", id);
        Optional<RefNationality> refNationality = refNationalityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refNationality);
    }

    /**
     * {@code DELETE  /ref-nationalities/:id} : delete the "id" refNationality.
     *
     * @param id the id of the refNationality to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ref-nationalities/{id}")
    public ResponseEntity<Void> deleteRefNationality(@PathVariable Long id) {
        log.debug("REST request to delete RefNationality : {}", id);
        refNationalityService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
