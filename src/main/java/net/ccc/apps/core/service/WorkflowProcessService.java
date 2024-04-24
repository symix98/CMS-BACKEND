package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.WorkflowProcess;
import net.ccc.apps.core.repository.WorkflowProcessRepository;
import net.ccc.apps.core.service.dto.WorkflowProcessDTO;
import net.ccc.apps.core.service.mapper.WorkflowProcessMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkflowProcess}.
 */
@Service
@Transactional
public class WorkflowProcessService {

    private final Logger log = LoggerFactory.getLogger(WorkflowProcessService.class);



    @Autowired
    protected WorkflowProcessMapper workflowProcessMapper;

    @Autowired
    protected WorkflowProcessRepository workflowProcessRepository;


    /**
     * Save a workflowProcess.
     *
     * @param workflowProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public WorkflowProcessDTO save(WorkflowProcessDTO workflowProcessDTO) {
        log.debug("Request to save WorkflowProcess : {}", workflowProcessDTO);
        WorkflowProcess workflowProcess = workflowProcessMapper.toEntity(workflowProcessDTO);
        workflowProcess = workflowProcessRepository.save(workflowProcess);
        return workflowProcessMapper.toDto(workflowProcess);
    }

    /**
     * Get all the workflowProcesses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkflowProcessDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkflowProcesses");
        return workflowProcessRepository.findAll(pageable).map(workflowProcessMapper :: toDto);
    }

    /**
     * Get one workflowProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WorkflowProcessDTO> findOneDTO(Long id) {
        log.debug("Request to get WorkflowProcess : {}", id);
        return workflowProcessRepository.findById(id).map(workflowProcessMapper :: toDto);
    }

    /**
     * Get one workflowProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WorkflowProcess> findOne(Long id) {
        log.debug("Request to get WorkflowProcess DTO: {}", id);
        return workflowProcessRepository.findById(id);
    }

    /**
     * Delete the workflowProcess by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WorkflowProcess : {}", id);
        workflowProcessRepository.deleteById(id);
    }

}
