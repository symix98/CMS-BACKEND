package net.ccc.apps.core.web.rest;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.List;
import java.util.Optional;

import net.ccc.apps.core.repository.ReportRepository;
import net.ccc.apps.core.service.queryService.ReportQueryService;
import net.ccc.apps.core.service.ReportService;
import net.ccc.apps.core.service.criteria.ReportCriteria;
import net.ccc.apps.core.service.dto.ReportDTO;
import net.ccc.apps.core.service.reports.ReportParameters;
import net.ccc.apps.errors.BadRequestAlertException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
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

import javax.servlet.http.HttpServletResponse;


/**
 * REST controller for managing {@link net.ccc.apps.core.domain.Report}.
 */
@RestController
@RequestMapping("/api")
public class ReportResource {

    private final Logger log = LoggerFactory.getLogger(ReportResource.class);

    private static final String ENTITY_NAME = "report";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportService reportService;

    private final ReportRepository reportRepository;

    private final ReportQueryService reportQueryService;

    public ReportResource(ReportService reportService, ReportRepository reportRepository, ReportQueryService reportQueryService) {
        this.reportService = reportService;
        this.reportRepository = reportRepository;
        this.reportQueryService = reportQueryService;
    }

    /**
     * {@code POST  /reports} : Create a new report.
     *
     * @param reportDTO the reportDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportDTO, or with status {@code 400 (Bad Request)} if the report has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reports")
    public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO reportDTO) throws URISyntaxException {
        log.debug("REST request to save Report : {}", reportDTO);
        if (reportDTO.getId() != null) {
            throw new BadRequestAlertException("A new report cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportDTO result = reportService.save(reportDTO);
        return ResponseEntity
            .created(new URI("/api/reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reports/:id} : Updates an existing report.
     *
     * @param id the id of the reportDTO to save.
     * @param reportDTO the reportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportDTO,
     * or with status {@code 400 (Bad Request)} if the reportDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reports/{id}")
    public ResponseEntity<ReportDTO> updateReport(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ReportDTO reportDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Report : {}, {}", id, reportDTO);
        if (reportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReportDTO result = reportService.save(reportDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, reportDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /reports/:id} : Partial updates given fields of an existing report, field will ignore if it is null
     *
     * @param id the id of the reportDTO to save.
     * @param reportDTO the reportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportDTO,
     * or with status {@code 400 (Bad Request)} if the reportDTO is not valid,
     * or with status {@code 404 (Not Found)} if the reportDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the reportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/reports/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ReportDTO> partialUpdateReport(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ReportDTO reportDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Report partially : {}, {}", id, reportDTO);
        if (reportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reportDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ReportDTO> result = reportService.partialUpdate(reportDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, reportDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /reports} : get all the reports.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reports in body.
     */
    @GetMapping("/reports")
    public ResponseEntity<List<ReportDTO>> getAllReports(
        ReportCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Reports by criteria: {}", criteria);
        Page<ReportDTO> page = reportQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reports/count} : count all the reports.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/reports/count")
    public ResponseEntity<Long> countReports(ReportCriteria criteria) {
        log.debug("REST request to count Reports by criteria: {}", criteria);
        return ResponseEntity.ok().body(reportQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /reports/:id} : get the "id" report.
     *
     * @param id the id of the reportDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reports/{id}")
    public ResponseEntity<ReportDTO> getReport(@PathVariable Long id) {
        log.debug("REST request to get Report : {}", id);
        Optional<ReportDTO> reportDTO = reportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportDTO);
    }

    /**
     * {@code DELETE  /reports/:id} : delete the "id" report.
     *
     * @param id the id of the reportDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reports/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        log.debug("REST request to delete Report : {}", id);
        reportService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /dwr-tests} : get all the dwrTests.
     *
     * @param reportName the pagination information.
     * @param reportFormat the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dwrTests in body.
     */
    @PostMapping("/report/{reportName}/{reportFormat}")
    public ResponseEntity<String> getReport(@PathVariable("reportName") String reportName, @PathVariable("reportFormat") String reportFormat, @RequestBody ReportParameters reportParameters, HttpServletResponse response) throws  JRException, IOException {
        log.debug("REST request to get report: {}, with format {}", reportName,reportFormat);
        JasperPrint jasperPrint = reportService.exportReport(reportName,reportFormat,reportParameters ,response);
        //  response.setHeader("","");


        return ResponseEntity.ok().body("");
    }

}
