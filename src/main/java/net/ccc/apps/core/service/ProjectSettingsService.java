package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.ProjectSettings;
import net.ccc.apps.core.repository.ProjectSettingsRepository;
import net.ccc.apps.core.service.dto.ProjectSettingsDTO;
import net.ccc.apps.core.service.mapper.ProjectSettingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProjectSettings}.
 */
@Service
@Transactional
public class ProjectSettingsService {

    private final Logger log = LoggerFactory.getLogger(ProjectSettingsService.class);

    private final ProjectSettingsRepository projectSettingsRepository;

    private final ProjectSettingsMapper projectSettingsMapper;

    public ProjectSettingsService(ProjectSettingsRepository projectSettingsRepository, ProjectSettingsMapper projectSettingsMapper) {
        this.projectSettingsRepository = projectSettingsRepository;
        this.projectSettingsMapper = projectSettingsMapper;
    }

    /**
     * Save a projectSettings.
     *
     * @param projectSettingsDTO the entity to save.
     * @return the persisted entity.
     */
    public ProjectSettingsDTO save(ProjectSettingsDTO projectSettingsDTO) {
        log.debug("Request to save ProjectSettings : {}", projectSettingsDTO);
        ProjectSettings projectSettings = projectSettingsMapper.toEntity(projectSettingsDTO);
        projectSettings = projectSettingsRepository.save(projectSettings);
        return projectSettingsMapper.toDto(projectSettings);
    }

    /**
     * Partially update a projectSettings.
     *
     * @param projectSettingsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProjectSettingsDTO> partialUpdate(ProjectSettingsDTO projectSettingsDTO) {
        log.debug("Request to partially update ProjectSettings : {}", projectSettingsDTO);

        return projectSettingsRepository
            .findById(projectSettingsDTO.getId())
            .map(existingProjectSettings -> {
                projectSettingsMapper.partialUpdate(existingProjectSettings, projectSettingsDTO);

                return existingProjectSettings;
            })
            .map(projectSettingsRepository::save)
            .map(projectSettingsMapper::toDto);
    }

    /**
     * Get all the projectSettings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProjectSettingsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProjectSettings");
        return projectSettingsRepository.findAll(pageable).map(projectSettingsMapper::toDto);
    }

    /**
     * Get one projectSettings by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProjectSettingsDTO> findOne(Long id) {
        log.debug("Request to get ProjectSettings : {}", id);
        return projectSettingsRepository.findById(id).map(projectSettingsMapper::toDto);
    }

    /**
     * Delete the projectSettings by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProjectSettings : {}", id);
        projectSettingsRepository.deleteById(id);
    }
}
