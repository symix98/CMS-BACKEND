package net.ccc.apps.campmanage.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.ccc.apps.campmanage.domain.BedDetails;
import net.ccc.apps.campmanage.repository.BedDetailsRepository;
import net.ccc.apps.campmanage.service.BedDetailsQueryService;
import net.ccc.apps.campmanage.service.BedDetailsService;
import net.ccc.apps.campmanage.service.criteria.BedDetailsCriteria;
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
 * REST controller for managing {@link net.ccc.apps.campmanage.domain.BedDetails}.
 */
@RestController
@RequestMapping("/api")
public class BedDetailsResource {

    private final Logger log = LoggerFactory.getLogger(BedDetailsResource.class);

    private static final String ENTITY_NAME = "campmanagBedDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BedDetailsService bedDetailsService;

    private final BedDetailsRepository bedDetailsRepository;

    private final BedDetailsQueryService bedDetailsQueryService;

    public BedDetailsResource(
        BedDetailsService bedDetailsService,
        BedDetailsRepository bedDetailsRepository,
        BedDetailsQueryService bedDetailsQueryService
    ) {
        this.bedDetailsService = bedDetailsService;
        this.bedDetailsRepository = bedDetailsRepository;
        this.bedDetailsQueryService = bedDetailsQueryService;
    }

    /**
     * {@code POST  /bed-details} : Create a new bedDetails.
     *
     * @param bedDetails the bedDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bedDetails, or with status {@code 400 (Bad Request)} if the bedDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bed-details")
    public ResponseEntity<BedDetails> createBedDetails(@RequestBody BedDetails bedDetails) throws URISyntaxException {
        log.debug("REST request to save BedDetails : {}", bedDetails);
        if (bedDetails.getId() != null) {
            throw new BadRequestAlertException("A new bedDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BedDetails result = bedDetailsService.save(bedDetails);
        return ResponseEntity
            .created(new URI("/api/bed-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bed-details/:id} : Updates an existing bedDetails.
     *
     * @param id the id of the bedDetails to save.
     * @param bedDetails the bedDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bedDetails,
     * or with status {@code 400 (Bad Request)} if the bedDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bedDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bed-details/{id}")
    public ResponseEntity<BedDetails> updateBedDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BedDetails bedDetails
    ) throws URISyntaxException {
        log.debug("REST request to update BedDetails : {}, {}", id, bedDetails);
        if (bedDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bedDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bedDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BedDetails result = bedDetailsService.update(bedDetails);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bedDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /bed-details/:id} : Partial updates given fields of an existing bedDetails, field will ignore if it is null
     *
     * @param id the id of the bedDetails to save.
     * @param bedDetails the bedDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bedDetails,
     * or with status {@code 400 (Bad Request)} if the bedDetails is not valid,
     * or with status {@code 404 (Not Found)} if the bedDetails is not found,
     * or with status {@code 500 (Internal Server Error)} if the bedDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bed-details/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BedDetails> partialUpdateBedDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BedDetails bedDetails
    ) throws URISyntaxException {
        log.debug("REST request to partial update BedDetails partially : {}, {}", id, bedDetails);
        if (bedDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bedDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bedDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BedDetails> result = bedDetailsService.partialUpdate(bedDetails);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bedDetails.getId().toString())
        );
    }

    /**
     * {@code GET  /bed-details} : get all the bedDetails.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bedDetails in body.
     */
    @GetMapping("/bed-details")
    public ResponseEntity<List<BedDetails>> getAllBedDetails(
        BedDetailsCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get BedDetails by criteria: {}", criteria);
        Page<BedDetails> page = bedDetailsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bed-details/count} : count all the bedDetails.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/bed-details/count")
    public ResponseEntity<Long> countBedDetails(BedDetailsCriteria criteria) {
        log.debug("REST request to count BedDetails by criteria: {}", criteria);
        return ResponseEntity.ok().body(bedDetailsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /bed-details/:id} : get the "id" bedDetails.
     *
     * @param id the id of the bedDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bedDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bed-details/{id}")
    public ResponseEntity<BedDetails> getBedDetails(@PathVariable Long id) {
        log.debug("REST request to get BedDetails : {}", id);
        Optional<BedDetails> bedDetails = bedDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bedDetails);
    }

    /**
     * {@code DELETE  /bed-details/:id} : delete the "id" bedDetails.
     *
     * @param id the id of the bedDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bed-details/{id}")
    public ResponseEntity<Void> deleteBedDetails(@PathVariable Long id) {
        log.debug("REST request to delete BedDetails : {}", id);
        bedDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
