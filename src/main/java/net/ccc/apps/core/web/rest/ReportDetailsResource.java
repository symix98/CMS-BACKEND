package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.ccc.apps.core.repository.ReportDetailsRepository;
import net.ccc.apps.core.service.queryService.ReportDetailsQueryService;
import net.ccc.apps.core.service.ReportDetailsService;
import net.ccc.apps.core.service.criteria.ReportDetailsCriteria;
import net.ccc.apps.core.service.dto.ReportDetailsDTO;
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
 * REST controller for managing {@link net.ccc.apps.core.domain.ReportDetails}.
 */
@RestController
@RequestMapping("/api")
public class ReportDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ReportDetailsResource.class);

    private static final String ENTITY_NAME = "reportDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportDetailsService reportDetailsService;

    private final ReportDetailsRepository reportDetailsRepository;

    private final ReportDetailsQueryService reportDetailsQueryService;

    public ReportDetailsResource(
        ReportDetailsService reportDetailsService,
        ReportDetailsRepository reportDetailsRepository,
        ReportDetailsQueryService reportDetailsQueryService
    ) {
        this.reportDetailsService = reportDetailsService;
        this.reportDetailsRepository = reportDetailsRepository;
        this.reportDetailsQueryService = reportDetailsQueryService;
    }

    /**
     * {@code POST  /report-details} : Create a new reportDetails.
     *
     * @param reportDetailsDTO the reportDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportDetailsDTO, or with status {@code 400 (Bad Request)} if the reportDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/report-details")
    public ResponseEntity<ReportDetailsDTO> createReportDetails(@RequestBody ReportDetailsDTO reportDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save ReportDetails : {}", reportDetailsDTO);
        if (reportDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new reportDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportDetailsDTO result = reportDetailsService.save(reportDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/report-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /report-details/:id} : Updates an existing reportDetails.
     *
     * @param id the id of the reportDetailsDTO to save.
     * @param reportDetailsDTO the reportDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the reportDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/report-details/{id}")
    public ResponseEntity<ReportDetailsDTO> updateReportDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ReportDetailsDTO reportDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ReportDetails : {}, {}", id, reportDetailsDTO);
        if (reportDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReportDetailsDTO result = reportDetailsService.save(reportDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, reportDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /report-details/:id} : Partial updates given fields of an existing reportDetails, field will ignore if it is null
     *
     * @param id the id of the reportDetailsDTO to save.
     * @param reportDetailsDTO the reportDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the reportDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the reportDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the reportDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/report-details/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReportDetailsDTO> partialUpdateReportDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ReportDetailsDTO reportDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ReportDetails partially : {}, {}", id, reportDetailsDTO);
        if (reportDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReportDetailsDTO> result = reportDetailsService.partialUpdate(reportDetailsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, reportDetailsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /report-details} : get all the reportDetails.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reportDetails in body.
     */
    @GetMapping("/report-details")
    public ResponseEntity<List<ReportDetailsDTO>> getAllReportDetails(
        ReportDetailsCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get ReportDetails by criteria: {}", criteria);
        Page<ReportDetailsDTO> page = reportDetailsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /report-details/count} : count all the reportDetails.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/report-details/count")
    public ResponseEntity<Long> countReportDetails(ReportDetailsCriteria criteria) {
        log.debug("REST request to count ReportDetails by criteria: {}", criteria);
        return ResponseEntity.ok().body(reportDetailsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /report-details/:id} : get the "id" reportDetails.
     *
     * @param id the id of the reportDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/report-details/{id}")
    public ResponseEntity<ReportDetailsDTO> getReportDetails(@PathVariable Long id) {
        log.debug("REST request to get ReportDetails : {}", id);
        Optional<ReportDetailsDTO> reportDetailsDTO = reportDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportDetailsDTO);
    }

    /**
     * {@code DELETE  /report-details/:id} : delete the "id" reportDetails.
     *
     * @param id the id of the reportDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/report-details/{id}")
    public ResponseEntity<Void> deleteReportDetails(@PathVariable Long id) {
        log.debug("REST request to delete ReportDetails : {}", id);
        reportDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
