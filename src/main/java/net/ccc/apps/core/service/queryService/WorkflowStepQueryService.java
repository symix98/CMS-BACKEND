package net.ccc.apps.core.service.queryService;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.WorkflowStep;
import net.ccc.apps.core.repository.WorkflowStepRepository;
import net.ccc.apps.core.service.criteria.WorkflowStepCriteria;
import net.ccc.apps.core.service.dto.WorkflowStepDTO;
import net.ccc.apps.core.service.mapper.WorkflowStepMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link WorkflowStep} entities in the database.
 * The main input is a {@link WorkflowStepCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WorkflowStepDTO} or a {@link Page} of {@link WorkflowStepDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WorkflowStepQueryService extends QueryService<WorkflowStep> {

    private final Logger log = LoggerFactory.getLogger(WorkflowStepQueryService.class);

    private final WorkflowStepRepository workflowStepRepository;

    private final WorkflowStepMapper workflowStepMapper;

    public WorkflowStepQueryService(WorkflowStepRepository workflowStepRepository, WorkflowStepMapper workflowStepMapper) {
        this.workflowStepRepository = workflowStepRepository;
        this.workflowStepMapper = workflowStepMapper;
    }

    /**
     * Return a {@link List} of {@link WorkflowStepDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WorkflowStepDTO> findByCriteria(WorkflowStepCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<WorkflowStep> specification = createSpecification(criteria);
        return workflowStepMapper.toDto(workflowStepRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WorkflowStepDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkflowStepDTO> findByCriteria(WorkflowStepCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<WorkflowStep> specification = createSpecification(criteria);
        return workflowStepRepository.findAll(specification, page)
            .map(workflowStepMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(WorkflowStepCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<WorkflowStep> specification = createSpecification(criteria);
        return workflowStepRepository.count(specification);
    }

    /**
     * Function to convert {@link WorkflowStepCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    public Specification<WorkflowStep> createSpecification(WorkflowStepCriteria criteria) {
        Specification<WorkflowStep> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), WorkflowStep_.id));
            }
            if (criteria.getTriggerTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTriggerTime(), WorkflowStep_.triggerTime));
            }
            if (criteria.getActionLabel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getActionLabel(), WorkflowStep_.actionLabel));
            }
            if (criteria.getActionStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getActionStatus(), WorkflowStep_.actionStatus));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), WorkflowStep_.description));
            }
            if (criteria.getSignoffRule() != null) {
                specification = specification.and(buildSpecification(criteria.getSignoffRule(), WorkflowStep_.signoffRule));
            }
            if (criteria.getDueDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDueDate(), WorkflowStep_.dueDate));
            }
            if (criteria.getComplete() != null) {
                specification = specification.and(buildSpecification(criteria.getComplete(), WorkflowStep_.complete));
            }
            if (criteria.getCompleteTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCompleteTime(), WorkflowStep_.completeTime));
            }
            if (criteria.getWorkflowTemplateStepId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWorkflowTemplateStepId(), WorkflowStep_.workflowTemplateStepId));
            }
            if (criteria.getStepInitiator() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStepInitiator(), WorkflowStep_.stepInitiator));
            }
            if (criteria.getWorkflowActionUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getWorkflowActionUserId(),
                    root -> root.join(WorkflowStep_.workflowActionUsers, JoinType.LEFT).get(WorkflowActionUser_.id)));
            }
            if (criteria.getWorkflowProcessId() != null) {
                specification = specification.and(buildSpecification(criteria.getWorkflowProcessId(),
                    root -> root.join(WorkflowStep_.workflowProcess, JoinType.LEFT).get(WorkflowProcess_.id)));
            }
        }
        return specification;
    }
}
