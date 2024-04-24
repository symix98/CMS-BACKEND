package net.ccc.apps.campmanage.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import net.ccc.apps.campmanage.domain.RefEmployeeCompany;
import net.ccc.apps.campmanage.repository.RefEmployeeCompanyRepository;
import net.ccc.apps.campmanage.service.RefEmployeeCompanyQueryService;
import net.ccc.apps.campmanage.service.RefEmployeeCompanyService;
import net.ccc.apps.campmanage.service.criteria.RefEmployeeCompanyCriteria;
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
 * REST controller for managing {@link net.ccc.apps.campmanage.domain.RefEmployeeCompany}.
 */
@RestController
@RequestMapping("/api")
public class RefEmployeeCompanyResource {

    private final Logger log = LoggerFactory.getLogger(RefEmployeeCompanyResource.class);

    private static final String ENTITY_NAME = "campmanagRefEmployeeCompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RefEmployeeCompanyService refEmployeeCompanyService;

    private final RefEmployeeCompanyRepository refEmployeeCompanyRepository;

    private final RefEmployeeCompanyQueryService refEmployeeCompanyQueryService;

    public RefEmployeeCompanyResource(
        RefEmployeeCompanyService refEmployeeCompanyService,
        RefEmployeeCompanyRepository refEmployeeCompanyRepository,
        RefEmployeeCompanyQueryService refEmployeeCompanyQueryService
    ) {
        this.refEmployeeCompanyService = refEmployeeCompanyService;
        this.refEmployeeCompanyRepository = refEmployeeCompanyRepository;
        this.refEmployeeCompanyQueryService = refEmployeeCompanyQueryService;
    }

    /**
     * {@code POST  /ref-employee-companies} : Create a new refEmployeeCompany.
     *
     * @param refEmployeeCompany the refEmployeeCompany to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new refEmployeeCompany, or with status {@code 400 (Bad Request)} if the refEmployeeCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ref-employee-companies")
    public ResponseEntity<RefEmployeeCompany> createRefEmployeeCompany(@RequestBody RefEmployeeCompany refEmployeeCompany)
        throws URISyntaxException {
        log.debug("REST request to save RefEmployeeCompany : {}", refEmployeeCompany);
        if (refEmployeeCompany.getId() != null) {
            throw new BadRequestAlertException("A new refEmployeeCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefEmployeeCompany result = refEmployeeCompanyService.save(refEmployeeCompany);
        return ResponseEntity
            .created(new URI("/api/ref-employee-companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ref-employee-companies/:id} : Updates an existing refEmployeeCompany.
     *
     * @param id the id of the refEmployeeCompany to save.
     * @param refEmployeeCompany the refEmployeeCompany to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refEmployeeCompany,
     * or with status {@code 400 (Bad Request)} if the refEmployeeCompany is not valid,
     * or with status {@code 500 (Internal Server Error)} if the refEmployeeCompany couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ref-employee-companies/{id}")
    public ResponseEntity<RefEmployeeCompany> updateRefEmployeeCompany(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RefEmployeeCompany refEmployeeCompany
    ) throws URISyntaxException {
        log.debug("REST request to update RefEmployeeCompany : {}, {}", id, refEmployeeCompany);
        if (refEmployeeCompany.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refEmployeeCompany.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refEmployeeCompanyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RefEmployeeCompany result = refEmployeeCompanyService.update(refEmployeeCompany);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refEmployeeCompany.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ref-employee-companies/:id} : Partial updates given fields of an existing refEmployeeCompany, field will ignore if it is null
     *
     * @param id the id of the refEmployeeCompany to save.
     * @param refEmployeeCompany the refEmployeeCompany to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refEmployeeCompany,
     * or with status {@code 400 (Bad Request)} if the refEmployeeCompany is not valid,
     * or with status {@code 404 (Not Found)} if the refEmployeeCompany is not found,
     * or with status {@code 500 (Internal Server Error)} if the refEmployeeCompany couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ref-employee-companies/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RefEmployeeCompany> partialUpdateRefEmployeeCompany(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RefEmployeeCompany refEmployeeCompany
    ) throws URISyntaxException {
        log.debug("REST request to partial update RefEmployeeCompany partially : {}, {}", id, refEmployeeCompany);
        if (refEmployeeCompany.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refEmployeeCompany.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refEmployeeCompanyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RefEmployeeCompany> result = refEmployeeCompanyService.partialUpdate(refEmployeeCompany);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refEmployeeCompany.getId().toString())
        );
    }

    /**
     * {@code GET  /ref-employee-companies} : get all the refEmployeeCompanies.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of refEmployeeCompanies in body.
     */
    @GetMapping("/ref-employee-companies")
    public ResponseEntity<List<RefEmployeeCompany>> getAllRefEmployeeCompanies(
        RefEmployeeCompanyCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RefEmployeeCompanies by criteria: {}", criteria);
        Page<RefEmployeeCompany> page = refEmployeeCompanyQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ref-employee-companies/count} : count all the refEmployeeCompanies.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ref-employee-companies/count")
    public ResponseEntity<Long> countRefEmployeeCompanies(RefEmployeeCompanyCriteria criteria) {
        log.debug("REST request to count RefEmployeeCompanies by criteria: {}", criteria);
        return ResponseEntity.ok().body(refEmployeeCompanyQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ref-employee-companies/:id} : get the "id" refEmployeeCompany.
     *
     * @param id the id of the refEmployeeCompany to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the refEmployeeCompany, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ref-employee-companies/{id}")
    public ResponseEntity<RefEmployeeCompany> getRefEmployeeCompany(@PathVariable Long id) {
        log.debug("REST request to get RefEmployeeCompany : {}", id);
        Optional<RefEmployeeCompany> refEmployeeCompany = refEmployeeCompanyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refEmployeeCompany);
    }

    /**
     * {@code DELETE  /ref-employee-companies/:id} : delete the "id" refEmployeeCompany.
     *
     * @param id the id of the refEmployeeCompany to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ref-employee-companies/{id}")
    public ResponseEntity<Void> deleteRefEmployeeCompany(@PathVariable Long id) {
        log.debug("REST request to delete RefEmployeeCompany : {}", id);
        refEmployeeCompanyService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
