package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.ProjectInfo;
import net.ccc.apps.core.repository.ProjectInfoRepository;
import net.ccc.apps.core.service.dto.ProjectInfoDTO;
import net.ccc.apps.core.service.mapper.ProjectInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProjectInfo}.
 */
@Service
@Transactional
public class ProjectInfoService {

    private final Logger log = LoggerFactory.getLogger(ProjectInfoService.class);

    private final ProjectInfoRepository projectInfoRepository;

    private final ProjectInfoMapper projectInfoMapper;

    public ProjectInfoService(ProjectInfoRepository projectInfoRepository, ProjectInfoMapper projectInfoMapper) {
        this.projectInfoRepository = projectInfoRepository;
        this.projectInfoMapper = projectInfoMapper;
    }

    /**
     * Save a projectInfo.
     *
     * @param projectInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public ProjectInfoDTO save(ProjectInfoDTO projectInfoDTO) {
        log.debug("Request to save ProjectInfo : {}", projectInfoDTO);
        ProjectInfo projectInfo = projectInfoMapper.toEntity(projectInfoDTO);
        projectInfo = projectInfoRepository.save(projectInfo);
        return projectInfoMapper.toDto(projectInfo);
    }

    /**
     * Partially update a projectInfo.
     *
     * @param projectInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProjectInfoDTO> partialUpdate(ProjectInfoDTO projectInfoDTO) {
        log.debug("Request to partially update ProjectInfo : {}", projectInfoDTO);

        return projectInfoRepository
            .findById(projectInfoDTO.getId())
            .map(existingProjectInfo -> {
                projectInfoMapper.partialUpdate(existingProjectInfo, projectInfoDTO);

                return existingProjectInfo;
            })
            .map(projectInfoRepository::save)
            .map(projectInfoMapper::toDto);
    }

    /**
     * Get all the projectInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProjectInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProjectInfos");
        return projectInfoRepository.findAll(pageable).map(projectInfoMapper::toDto);
    }

    /**
     * Get one projectInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProjectInfoDTO> findOne(Long id) {
        log.debug("Request to get ProjectInfo : {}", id);
        return projectInfoRepository.findById(id).map(projectInfoMapper::toDto);
    }

    /**
     * Delete the projectInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProjectInfo : {}", id);
        projectInfoRepository.deleteById(id);
    }
}
