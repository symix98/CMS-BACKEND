package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.ReportDetails;
import net.ccc.apps.core.repository.ReportDetailsRepository;
import net.ccc.apps.core.service.dto.ReportDetailsDTO;
import net.ccc.apps.core.service.mapper.ReportDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ReportDetails}.
 */
@Service
@Transactional
public class ReportDetailsService {

    private final Logger log = LoggerFactory.getLogger(ReportDetailsService.class);

    private final ReportDetailsRepository reportDetailsRepository;

    private final ReportDetailsMapper reportDetailsMapper;

    public ReportDetailsService(ReportDetailsRepository reportDetailsRepository, ReportDetailsMapper reportDetailsMapper) {
        this.reportDetailsRepository = reportDetailsRepository;
        this.reportDetailsMapper = reportDetailsMapper;
    }

    /**
     * Save a reportDetails.
     *
     * @param reportDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public ReportDetailsDTO save(ReportDetailsDTO reportDetailsDTO) {
        log.debug("Request to save ReportDetails : {}", reportDetailsDTO);
        ReportDetails reportDetails = reportDetailsMapper.toEntity(reportDetailsDTO);
        reportDetails = reportDetailsRepository.save(reportDetails);
        return reportDetailsMapper.toDto(reportDetails);
    }

    /**
     * Partially update a reportDetails.
     *
     * @param reportDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ReportDetailsDTO> partialUpdate(ReportDetailsDTO reportDetailsDTO) {
        log.debug("Request to partially update ReportDetails : {}", reportDetailsDTO);

        return reportDetailsRepository
            .findById(reportDetailsDTO.getId())
            .map(existingReportDetails -> {
                reportDetailsMapper.partialUpdate(existingReportDetails, reportDetailsDTO);

                return existingReportDetails;
            })
            .map(reportDetailsRepository::save)
            .map(reportDetailsMapper::toDto);
    }

    /**
     * Get all the reportDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ReportDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ReportDetails");
        return reportDetailsRepository.findAll(pageable).map(reportDetailsMapper::toDto);
    }

    /**
     * Get one reportDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReportDetailsDTO> findOne(Long id) {
        log.debug("Request to get ReportDetails : {}", id);
        return reportDetailsRepository.findById(id).map(reportDetailsMapper::toDto);
    }

    /**
     * Delete the reportDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ReportDetails : {}", id);
        reportDetailsRepository.deleteById(id);
    }
}
