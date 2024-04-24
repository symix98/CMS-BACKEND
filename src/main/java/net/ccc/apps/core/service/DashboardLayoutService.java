package net.ccc.apps.core.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.ccc.apps.core.domain.DashboardLayout;
import net.ccc.apps.core.repository.DashboardLayoutRepository;
import net.ccc.apps.core.service.dto.DashboardLayoutDTO;
import net.ccc.apps.core.service.mapper.DashboardLayoutMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DashboardLayout}.
 */
@Service
@Transactional
public class DashboardLayoutService {

    private final Logger log = LoggerFactory.getLogger(DashboardLayoutService.class);

    private final DashboardLayoutRepository dashboardLayoutRepository;

    private final DashboardLayoutMapper dashboardLayoutMapper;

    public DashboardLayoutService(DashboardLayoutRepository dashboardLayoutRepository, DashboardLayoutMapper dashboardLayoutMapper) {
        this.dashboardLayoutRepository = dashboardLayoutRepository;
        this.dashboardLayoutMapper = dashboardLayoutMapper;
    }

    /**
     * Save a dashboardLayout.
     *
     * @param dashboardLayoutDTO the entity to save.
     * @return the persisted entity.
     */
    public DashboardLayoutDTO save(DashboardLayoutDTO dashboardLayoutDTO) {
        log.debug("Request to save DashboardLayout : {}", dashboardLayoutDTO);
        DashboardLayout dashboardLayout = dashboardLayoutMapper.toEntity(dashboardLayoutDTO);
        dashboardLayout = dashboardLayoutRepository.save(dashboardLayout);
        return dashboardLayoutMapper.toDto(dashboardLayout);
    }

    /**
     * Get all the dashboardLayouts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DashboardLayoutDTO> findAll() {
        log.debug("Request to get all DashboardLayouts");
        return dashboardLayoutRepository
            .findAll()
            .stream()
            .map(dashboardLayoutMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one dashboardLayout by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DashboardLayoutDTO> findOne(Long id) {
        log.debug("Request to get DashboardLayout : {}", id);
        return dashboardLayoutRepository.findById(id).map(dashboardLayoutMapper::toDto);
    }

    /**
     * Delete the dashboardLayout by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DashboardLayout : {}", id);
        dashboardLayoutRepository.deleteById(id);
    }

    /**
     * Get one dashboardLayout by id.
     *
     * @param dashboardLayoutId the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public List<DashboardLayoutDTO> findByDashboardLayoutId(String dashboardLayoutId) {
        log.debug("Request to get DashboardLayout : {}", dashboardLayoutId);
        return dashboardLayoutRepository
            .findByDashboardLayoutId(dashboardLayoutId)
            .stream()
            .map(dashboardLayoutMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
