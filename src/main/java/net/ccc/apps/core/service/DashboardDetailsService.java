package net.ccc.apps.core.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.ccc.apps.core.domain.DashboardDetails;
import net.ccc.apps.core.repository.DashboardDetailsRepository;
import net.ccc.apps.core.service.dto.DashboardDetailsDTO;
import net.ccc.apps.core.service.mapper.DashboardDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DashboardDetails}.
 */
@Service
@Transactional
public class DashboardDetailsService {

    private final Logger log = LoggerFactory.getLogger(DashboardDetailsService.class);

    private final DashboardDetailsRepository dashboardDetailsRepository;

    private final DashboardDetailsMapper dashboardDetailsMapper;

    public DashboardDetailsService(DashboardDetailsRepository dashboardDetailsRepository, DashboardDetailsMapper dashboardDetailsMapper) {
        this.dashboardDetailsRepository = dashboardDetailsRepository;
        this.dashboardDetailsMapper = dashboardDetailsMapper;
    }

    /**
     * Save a dashboardDetails.
     *
     * @param dashboardDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public DashboardDetailsDTO save(DashboardDetailsDTO dashboardDetailsDTO) {
        log.debug("Request to save DashboardDetails : {}", dashboardDetailsDTO);
        DashboardDetails dashboardDetails = dashboardDetailsMapper.toEntity(dashboardDetailsDTO);
        dashboardDetails = dashboardDetailsRepository.save(dashboardDetails);
        return dashboardDetailsMapper.toDto(dashboardDetails);
    }

    /**
     * Get all the dashboardDetails.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DashboardDetailsDTO> findAll() {
        log.debug("Request to get all DashboardDetails");
        return dashboardDetailsRepository
            .findAll()
            .stream()
            .map(dashboardDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the dashboardDetails.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DashboardDetailsDTO> findDetailsByDashBoardId(String dashboardId) {
        log.debug("Request to get all DashboardDetails");
        return dashboardDetailsRepository
            .findByDashBoardId(dashboardId)
            .stream()
            .map(dashboardDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one dashboardDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DashboardDetailsDTO> findOne(Long id) {
        log.debug("Request to get DashboardDetails : {}", id);
        return dashboardDetailsRepository.findById(id).map(dashboardDetailsMapper::toDto);
    }

    /**
     * Delete the dashboardDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DashboardDetails : {}", id);
        dashboardDetailsRepository.deleteById(id);
    }
}
