package net.ccc.apps.core.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import net.ccc.apps.core.domain.Attachement;
import net.ccc.apps.core.domain.Report;
import net.ccc.apps.core.repository.AttachementRepository;
import net.ccc.apps.core.repository.ReportRepository;
import net.ccc.apps.core.service.dto.ReportDTO;
import net.ccc.apps.core.service.mapper.ReportMapper;
import net.ccc.apps.core.service.reports.AbstractReport;
import net.ccc.apps.core.service.reports.ReportParameters;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.*;
import java.sql.Connection;

/**
 * Service Implementation for managing {@link Report}.
 */
@Service
@Transactional
public class ReportService extends AbstractReport {

    private final Logger log = LoggerFactory.getLogger(ReportService.class);

    private final ReportRepository reportRepository;

    private final ReportMapper reportMapper;

    @Autowired
    AttachementRepository attachementRepository;



    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager em;


    public ReportService(ReportRepository reportRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    /**
     * Save a report.
     *
     * @param reportDTO the entity to save.
     * @return the persisted entity.
     */
    public ReportDTO save(ReportDTO reportDTO) {
        log.debug("Request to save Report : {}", reportDTO);
        Report report = reportMapper.toEntity(reportDTO);
        report = reportRepository.save(report);
        return reportMapper.toDto(report);
    }

    /**
     * Partially update a report.
     *
     * @param reportDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ReportDTO> partialUpdate(ReportDTO reportDTO) {
        log.debug("Request to partially update Report : {}", reportDTO);

        return reportRepository
            .findById(reportDTO.getId())
            .map(existingReport -> {
                reportMapper.partialUpdate(existingReport, reportDTO);

                return existingReport;
            })
            .map(reportRepository::save)
            .map(reportMapper::toDto);
    }

    /**
     * Get all the reports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Reports");
        return reportRepository.findAll(pageable).map(reportMapper::toDto);
    }

    /**
     * Get one report by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReportDTO> findOne(Long id) {
        log.debug("Request to get Report : {}", id);
        return reportRepository.findById(id).map(reportMapper::toDto);
    }

    /**
     * Delete the report by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Report : {}", id);
        reportRepository.deleteById(id);
    }


    @Autowired
    DataSource dataSource;

    public JasperPrint exportReport(String reportName, String reportFormat, ReportParameters reportParameters, HttpServletResponse response) throws IOException, JRException {

        Optional<Report> reportRowData = reportRepository.findByName(reportName);

        List<Attachement> logosList = attachementRepository.findAllByProjectInfoId(1L);

        Connection conn = null;
        try {

            conn=  dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.init(reportFormat,logosList,reportRowData,reportParameters,conn,response);





        return null;

    }




}

