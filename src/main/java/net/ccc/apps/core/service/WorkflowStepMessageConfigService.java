package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.WorkflowStepMessageConfig;
import net.ccc.apps.core.repository.WorkflowStepMessageConfigRepository;
import net.ccc.apps.core.service.dto.WorkflowStepMessageConfigDTO;
import net.ccc.apps.core.service.mapper.WorkflowStepMessageConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkflowStepMessageConfig}.
 */
@Service
@Transactional
public class WorkflowStepMessageConfigService {

    private final Logger log = LoggerFactory.getLogger(WorkflowStepMessageConfigService.class);

    private final WorkflowStepMessageConfigRepository workflowStepMessageConfigRepository;

    private final WorkflowStepMessageConfigMapper workflowStepMessageConfigMapper;

    public WorkflowStepMessageConfigService(
        WorkflowStepMessageConfigRepository workflowStepMessageConfigRepository,
        WorkflowStepMessageConfigMapper workflowStepMessageConfigMapper
    ) {
        this.workflowStepMessageConfigRepository = workflowStepMessageConfigRepository;
        this.workflowStepMessageConfigMapper = workflowStepMessageConfigMapper;
    }

    /**
     * Save a workflowStepMessageConfig.
     *
     * @param workflowStepMessageConfigDTO the entity to save.
     * @return the persisted entity.
     */
    public WorkflowStepMessageConfigDTO save(WorkflowStepMessageConfigDTO workflowStepMessageConfigDTO) {
        log.debug("Request to save WorkflowStepMessageConfig : {}", workflowStepMessageConfigDTO);
        WorkflowStepMessageConfig workflowStepMessageConfig = workflowStepMessageConfigMapper.toEntity(workflowStepMessageConfigDTO);
        workflowStepMessageConfig = workflowStepMessageConfigRepository.save(workflowStepMessageConfig);
        return workflowStepMessageConfigMapper.toDto(workflowStepMessageConfig);
    }

    /**
     * Partially update a workflowStepMessageConfig.
     *
     * @param workflowStepMessageConfigDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<WorkflowStepMessageConfigDTO> partialUpdate(WorkflowStepMessageConfigDTO workflowStepMessageConfigDTO) {
        log.debug("Request to partially update WorkflowStepMessageConfig : {}", workflowStepMessageConfigDTO);

        return workflowStepMessageConfigRepository
            .findById(workflowStepMessageConfigDTO.getId())
            .map(existingWorkflowStepMessageConfig -> {
                workflowStepMessageConfigMapper.partialUpdate(existingWorkflowStepMessageConfig, workflowStepMessageConfigDTO);

                return existingWorkflowStepMessageConfig;
            })
            .map(workflowStepMessageConfigRepository::save)
            .map(workflowStepMessageConfigMapper::toDto);
    }

    /**
     * Get all the workflowStepMessageConfigs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkflowStepMessageConfigDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkflowStepMessageConfigs");
        return workflowStepMessageConfigRepository.findAll(pageable).map(workflowStepMessageConfigMapper::toDto);
    }

    /**
     * Get one workflowStepMessageConfig by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WorkflowStepMessageConfigDTO> findOne(Long id) {
        log.debug("Request to get WorkflowStepMessageConfig : {}", id);
        return workflowStepMessageConfigRepository.findById(id).map(workflowStepMessageConfigMapper::toDto);
    }

    /**
     * Delete the workflowStepMessageConfig by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WorkflowStepMessageConfig : {}", id);
        workflowStepMessageConfigRepository.deleteById(id);
    }
}
