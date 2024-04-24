package net.ccc.apps.campmanage.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.ccc.apps.campmanage.domain.Camp;
import net.ccc.apps.campmanage.repository.CampRepository;
import net.ccc.apps.campmanage.service.CampQueryService;
import net.ccc.apps.campmanage.service.CampService;
import net.ccc.apps.campmanage.service.criteria.CampCriteria;
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
 * REST controller for managing {@link net.ccc.apps.campmanage.domain.Camp}.
 */
@RestController
@RequestMapping("/api")
public class CampResource {

    private final Logger log = LoggerFactory.getLogger(CampResource.class);

    private static final String ENTITY_NAME = "campmanagCamp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CampService campService;

    private final CampRepository campRepository;

    private final CampQueryService campQueryService;

    public CampResource(CampService campService, CampRepository campRepository, CampQueryService campQueryService) {
        this.campService = campService;
        this.campRepository = campRepository;
        this.campQueryService = campQueryService;
    }

    /**
     * {@code POST  /camps} : Create a new camp.
     *
     * @param camp the camp to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new camp, or with status {@code 400 (Bad Request)} if the camp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/camps")
    public ResponseEntity<Camp> createCamp(@RequestBody Camp camp) throws URISyntaxException {
        log.debug("REST request to save Camp : {}", camp);
        if (camp.getId() != null) {
            throw new BadRequestAlertException("A new camp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Camp result = campService.save(camp);
        return ResponseEntity
            .created(new URI("/api/camps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /camps/:id} : Updates an existing camp.
     *
     * @param id the id of the camp to save.
     * @param camp the camp to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated camp,
     * or with status {@code 400 (Bad Request)} if the camp is not valid,
     * or with status {@code 500 (Internal Server Error)} if the camp couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/camps/{id}")
    public ResponseEntity<Camp> updateCamp(@PathVariable(value = "id", required = false) final Long id, @RequestBody Camp camp)
        throws URISyntaxException {
        log.debug("REST request to update Camp : {}, {}", id, camp);
        if (camp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, camp.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!campRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Camp result = campService.update(camp);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, camp.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /camps/:id} : Partial updates given fields of an existing camp, field will ignore if it is null
     *
     * @param id the id of the camp to save.
     * @param camp the camp to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated camp,
     * or with status {@code 400 (Bad Request)} if the camp is not valid,
     * or with status {@code 404 (Not Found)} if the camp is not found,
     * or with status {@code 500 (Internal Server Error)} if the camp couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/camps/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Camp> partialUpdateCamp(@PathVariable(value = "id", required = false) final Long id, @RequestBody Camp camp)
        throws URISyntaxException {
        log.debug("REST request to partial update Camp partially : {}, {}", id, camp);
        if (camp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, camp.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!campRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Camp> result = campService.partialUpdate(camp);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, camp.getId().toString())
        );
    }

    /**
     * {@code GET  /camps} : get all the camps.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of camps in body.
     */
    @GetMapping("/camps")
    public ResponseEntity<List<Camp>> getAllCamps(CampCriteria criteria, @org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get Camps by criteria: {}", criteria);
        Page<Camp> page = campQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /camps/count} : count all the camps.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/camps/count")
    public ResponseEntity<Long> countCamps(CampCriteria criteria) {
        log.debug("REST request to count Camps by criteria: {}", criteria);
        return ResponseEntity.ok().body(campQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /camps/:id} : get the "id" camp.
     *
     * @param id the id of the camp to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the camp, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/camps/{id}")
    public ResponseEntity<Camp> getCamp(@PathVariable Long id) {
        log.debug("REST request to get Camp : {}", id);
        Optional<Camp> camp = campService.findOne(id);
        return ResponseUtil.wrapOrNotFound(camp);
    }

    /**
     * {@code DELETE  /camps/:id} : delete the "id" camp.
     *
     * @param id the id of the camp to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/camps/{id}")
    public ResponseEntity<Void> deleteCamp(@PathVariable Long id) {
        log.debug("REST request to delete Camp : {}", id);
        campService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
