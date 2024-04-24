package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.WorkflowStep;
import net.ccc.apps.core.repository.WorkflowStepRepository;
import net.ccc.apps.core.service.dto.WorkflowStepDTO;
import net.ccc.apps.core.service.mapper.WorkflowStepMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkflowStep}.
 */
@Service
@Transactional
public class WorkflowStepService {

    private final Logger log = LoggerFactory.getLogger(WorkflowStepService.class);

    private final WorkflowStepRepository workflowStepRepository;

    private final WorkflowStepMapper workflowStepMapper;

    public WorkflowStepService(WorkflowStepRepository workflowStepRepository, WorkflowStepMapper workflowStepMapper) {
        this.workflowStepRepository = workflowStepRepository;
        this.workflowStepMapper = workflowStepMapper;
    }

    /**
     * Save a workflowStep.
     *
     * @param workflowStepDTO the entity to save.
     * @return the persisted entity.
     */
    public WorkflowStepDTO save(WorkflowStepDTO workflowStepDTO) {
        log.debug("Request to save WorkflowStep : {}", workflowStepDTO);
        WorkflowStep workflowStep = workflowStepMapper.toEntity(workflowStepDTO);
        workflowStep = workflowStepRepository.save(workflowStep);
        return workflowStepMapper.toDto(workflowStep);
    }

    /**
     * Partially update a workflowStep.
     *
     * @param workflowStepDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<WorkflowStepDTO> partialUpdate(WorkflowStepDTO workflowStepDTO) {
        log.debug("Request to partially update WorkflowStep : {}", workflowStepDTO);

        return workflowStepRepository
            .findById(workflowStepDTO.getId())
            .map(existingWorkflowStep -> {
                workflowStepMapper.partialUpdate(existingWorkflowStep, workflowStepDTO);

                return existingWorkflowStep;
            })
            .map(workflowStepRepository::save)
            .map(workflowStepMapper::toDto);
    }

    /**
     * Get all the workflowSteps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkflowStepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkflowSteps");
        return workflowStepRepository.findAll(pageable).map(workflowStepMapper::toDto);
    }

    /**
     * Get one workflowStep by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WorkflowStepDTO> findOne(Long id) {
        log.debug("Request to get WorkflowStep : {}", id);
        return workflowStepRepository.findById(id).map(workflowStepMapper::toDto);
    }

    /**
     * Delete the workflowStep by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WorkflowStep : {}", id);
        workflowStepRepository.deleteById(id);
    }
}
