package net.ccc.apps.campmanage.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import net.ccc.apps.campmanage.domain.RefTrade;
import net.ccc.apps.campmanage.repository.RefTradeRepository;
import net.ccc.apps.campmanage.service.RefTradeQueryService;
import net.ccc.apps.campmanage.service.RefTradeService;
import net.ccc.apps.campmanage.service.criteria.RefTradeCriteria;
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
 * REST controller for managing {@link net.ccc.apps.campmanage.domain.RefTrade}.
 */
@RestController
@RequestMapping("/api")
public class RefTradeResource {

    private final Logger log = LoggerFactory.getLogger(RefTradeResource.class);

    private static final String ENTITY_NAME = "campmanagRefTrade";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RefTradeService refTradeService;

    private final RefTradeRepository refTradeRepository;

    private final RefTradeQueryService refTradeQueryService;

    public RefTradeResource(
        RefTradeService refTradeService,
        RefTradeRepository refTradeRepository,
        RefTradeQueryService refTradeQueryService
    ) {
        this.refTradeService = refTradeService;
        this.refTradeRepository = refTradeRepository;
        this.refTradeQueryService = refTradeQueryService;
    }

    /**
     * {@code POST  /ref-trades} : Create a new refTrade.
     *
     * @param refTrade the refTrade to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new refTrade, or with status {@code 400 (Bad Request)} if the refTrade has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ref-trades")
    public ResponseEntity<RefTrade> createRefTrade(@RequestBody RefTrade refTrade) throws URISyntaxException {
        log.debug("REST request to save RefTrade : {}", refTrade);
        if (refTrade.getId() != null) {
            throw new BadRequestAlertException("A new refTrade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefTrade result = refTradeService.save(refTrade);
        return ResponseEntity
            .created(new URI("/api/ref-trades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ref-trades/:id} : Updates an existing refTrade.
     *
     * @param id the id of the refTrade to save.
     * @param refTrade the refTrade to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refTrade,
     * or with status {@code 400 (Bad Request)} if the refTrade is not valid,
     * or with status {@code 500 (Internal Server Error)} if the refTrade couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ref-trades/{id}")
    public ResponseEntity<RefTrade> updateRefTrade(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RefTrade refTrade
    ) throws URISyntaxException {
        log.debug("REST request to update RefTrade : {}, {}", id, refTrade);
        if (refTrade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refTrade.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refTradeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RefTrade result = refTradeService.update(refTrade);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refTrade.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ref-trades/:id} : Partial updates given fields of an existing refTrade, field will ignore if it is null
     *
     * @param id the id of the refTrade to save.
     * @param refTrade the refTrade to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated refTrade,
     * or with status {@code 400 (Bad Request)} if the refTrade is not valid,
     * or with status {@code 404 (Not Found)} if the refTrade is not found,
     * or with status {@code 500 (Internal Server Error)} if the refTrade couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ref-trades/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RefTrade> partialUpdateRefTrade(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RefTrade refTrade
    ) throws URISyntaxException {
        log.debug("REST request to partial update RefTrade partially : {}, {}", id, refTrade);
        if (refTrade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, refTrade.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!refTradeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RefTrade> result = refTradeService.partialUpdate(refTrade);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, refTrade.getId().toString())
        );
    }

    /**
     * {@code GET  /ref-trades} : get all the refTrades.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of refTrades in body.
     */
    @GetMapping("/ref-trades")
    public ResponseEntity<List<RefTrade>> getAllRefTrades(
        RefTradeCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RefTrades by criteria: {}", criteria);
        Page<RefTrade> page = refTradeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ref-trades/count} : count all the refTrades.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ref-trades/count")
    public ResponseEntity<Long> countRefTrades(RefTradeCriteria criteria) {
        log.debug("REST request to count RefTrades by criteria: {}", criteria);
        return ResponseEntity.ok().body(refTradeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ref-trades/:id} : get the "id" refTrade.
     *
     * @param id the id of the refTrade to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the refTrade, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ref-trades/{id}")
    public ResponseEntity<RefTrade> getRefTrade(@PathVariable Long id) {
        log.debug("REST request to get RefTrade : {}", id);
        Optional<RefTrade> refTrade = refTradeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(refTrade);
    }

    /**
     * {@code DELETE  /ref-trades/:id} : delete the "id" refTrade.
     *
     * @param id the id of the refTrade to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ref-trades/{id}")
    public ResponseEntity<Void> deleteRefTrade(@PathVariable Long id) {
        log.debug("REST request to delete RefTrade : {}", id);
        refTradeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
