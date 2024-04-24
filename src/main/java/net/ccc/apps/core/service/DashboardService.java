package net.ccc.apps.core.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.ccc.apps.core.domain.Dashboard;
import net.ccc.apps.core.repository.DashboardRepository;
import net.ccc.apps.core.service.dto.DashboardDTO;
import net.ccc.apps.core.service.mapper.DashboardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Dashboard}.
 */
@Service
@Transactional
public class DashboardService {

    private final Logger log = LoggerFactory.getLogger(DashboardService.class);

    private final DashboardRepository dashboardRepository;

    private final DashboardMapper dashboardMapper;

    public DashboardService(DashboardRepository dashboardRepository, DashboardMapper dashboardMapper) {
        this.dashboardRepository = dashboardRepository;
        this.dashboardMapper = dashboardMapper;
    }

    /**
     * Save a dashboard.
     *
     * @param dashboardDTO the entity to save.
     * @return the persisted entity.
     */
    public DashboardDTO save(DashboardDTO dashboardDTO) {
        log.debug("Request to save Dashboard : {}", dashboardDTO);
        Dashboard dashboard = dashboardMapper.toEntity(dashboardDTO);
        dashboard = dashboardRepository.save(dashboard);
        return dashboardMapper.toDto(dashboard);
    }

    /**
     * Get all the dashboards.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DashboardDTO> findAll() {
        log.debug("Request to get all Dashboards");
        return dashboardRepository.findAll().stream().map(dashboardMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one dashboard by dashboardId.
     *
     * @param dashboardId the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DashboardDTO> findOne(String dashboardId) {
        log.debug("Request to get Dashboard : {}", dashboardId);
        return dashboardRepository.findById(dashboardId).map(dashboardMapper::toDto);
    }

    /**
     * Delete the dashboard by dashboardId.
     *
     * @param dashboardId the id of the entity.
     */
    public void delete(String dashboardId) {
        log.debug("Request to delete Dashboard : {}", dashboardId);
        dashboardRepository.deleteById(dashboardId);
    }
}
