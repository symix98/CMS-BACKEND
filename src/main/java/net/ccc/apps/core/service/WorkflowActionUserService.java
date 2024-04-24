package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.WorkflowActionUser;
import net.ccc.apps.core.repository.WorkflowActionUserRepository;
import net.ccc.apps.core.service.dto.WorkflowActionUserDTO;
import net.ccc.apps.core.service.mapper.WorkflowActionUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkflowActionUser}.
 */
@Service
@Transactional
public class WorkflowActionUserService {

    private final Logger log = LoggerFactory.getLogger(WorkflowActionUserService.class);

    private final WorkflowActionUserRepository workflowActionUserRepository;

    private final WorkflowActionUserMapper workflowActionUserMapper;

    public WorkflowActionUserService(
        WorkflowActionUserRepository workflowActionUserRepository,
        WorkflowActionUserMapper workflowActionUserMapper
    ) {
        this.workflowActionUserRepository = workflowActionUserRepository;
        this.workflowActionUserMapper = workflowActionUserMapper;
    }

    /**
     * Save a workflowActionUser.
     *
     * @param workflowActionUserDTO the entity to save.
     * @return the persisted entity.
     */
    public WorkflowActionUserDTO save(WorkflowActionUserDTO workflowActionUserDTO) {
        log.debug("Request to save WorkflowActionUser : {}", workflowActionUserDTO);
        WorkflowActionUser workflowActionUser = workflowActionUserMapper.toEntity(workflowActionUserDTO);
        workflowActionUser = workflowActionUserRepository.save(workflowActionUser);
        return workflowActionUserMapper.toDto(workflowActionUser);
    }

    /**
     * Partially update a workflowActionUser.
     *
     * @param workflowActionUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<WorkflowActionUserDTO> partialUpdate(WorkflowActionUserDTO workflowActionUserDTO) {
        log.debug("Request to partially update WorkflowActionUser : {}", workflowActionUserDTO);

        return workflowActionUserRepository
            .findById(workflowActionUserDTO.getId())
            .map(existingWorkflowActionUser -> {
                workflowActionUserMapper.partialUpdate(existingWorkflowActionUser, workflowActionUserDTO);

                return existingWorkflowActionUser;
            })
            .map(workflowActionUserRepository::save)
            .map(workflowActionUserMapper::toDto);
    }

    /**
     * Get all the workflowActionUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkflowActionUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkflowActionUsers");
        return workflowActionUserRepository.findAll(pageable).map(workflowActionUserMapper::toDto);
    }

    /**
     * Get one workflowActionUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WorkflowActionUserDTO> findOne(Long id) {
        log.debug("Request to get WorkflowActionUser : {}", id);
        return workflowActionUserRepository.findById(id).map(workflowActionUserMapper::toDto);
    }

    /**
     * Delete the workflowActionUser by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WorkflowActionUser : {}", id);
        workflowActionUserRepository.deleteById(id);
    }
}
