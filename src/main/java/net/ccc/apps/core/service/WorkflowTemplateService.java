package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.WorkflowTemplate;
import net.ccc.apps.core.repository.WorkflowTemplateRepository;
import net.ccc.apps.core.service.dto.WorkflowTemplateDTO;
import net.ccc.apps.core.service.mapper.WorkflowTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WorkflowTemplate}.
 */
@Service
@Transactional
public class WorkflowTemplateService {

    private final Logger log = LoggerFactory.getLogger(WorkflowTemplateService.class);

    private final WorkflowTemplateRepository workflowTemplateRepository;

    private final WorkflowTemplateMapper workflowTemplateMapper;

    public WorkflowTemplateService(WorkflowTemplateRepository workflowTemplateRepository, WorkflowTemplateMapper workflowTemplateMapper) {
        this.workflowTemplateRepository = workflowTemplateRepository;
        this.workflowTemplateMapper = workflowTemplateMapper;
    }

    /**
     * Save a workflowTemplate.
     *
     * @param workflowTemplateDTO the entity to save.
     * @return the persisted entity.
     */
    public WorkflowTemplateDTO save(WorkflowTemplateDTO workflowTemplateDTO) {
        log.debug("Request to save WorkflowTemplate : {}", workflowTemplateDTO);
        WorkflowTemplate workflowTemplate = workflowTemplateMapper.toEntity(workflowTemplateDTO);
        workflowTemplate = workflowTemplateRepository.save(workflowTemplate);
        return workflowTemplateMapper.toDto(workflowTemplate);
    }

    /**
     * Partially update a workflowTemplate.
     *
     * @param workflowTemplateDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<WorkflowTemplateDTO> partialUpdate(WorkflowTemplateDTO workflowTemplateDTO) {
        log.debug("Request to partially update WorkflowTemplate : {}", workflowTemplateDTO);

        return workflowTemplateRepository
            .findById(workflowTemplateDTO.getId())
            .map(existingWorkflowTemplate -> {
                workflowTemplateMapper.partialUpdate(existingWorkflowTemplate, workflowTemplateDTO);

                return existingWorkflowTemplate;
            })
            .map(workflowTemplateRepository::save)
            .map(workflowTemplateMapper::toDto);
    }

    /**
     * Get all the workflowTemplates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkflowTemplateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkflowTemplates");
        return workflowTemplateRepository.findAll(pageable).map(workflowTemplateMapper::toDto);
    }

    /**
     * Get one workflowTemplate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WorkflowTemplateDTO> findOne(Long id) {
        log.debug("Request to get WorkflowTemplate : {}", id);
        return workflowTemplateRepository.findById(id).map(workflowTemplateMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<WorkflowTemplateDTO> findWfTemplateBySequenceandType(Long sequence, String formType) {
        log.debug("Request to get WorkflowTemplate : {}", sequence, formType);
        return workflowTemplateRepository.findWorkflowTemplateBySequenceandType(sequence, formType).map(workflowTemplateMapper::toDto);
    }

    /**
     * Delete the workflowTemplate by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete WorkflowTemplate : {}", id);
        workflowTemplateRepository.deleteById(id);
    }
}
