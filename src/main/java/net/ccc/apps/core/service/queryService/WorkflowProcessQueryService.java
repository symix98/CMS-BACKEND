package net.ccc.apps.core.service.queryService;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.WorkflowProcess;
import net.ccc.apps.core.repository.WorkflowProcessRepository;
import net.ccc.apps.core.service.criteria.WorkflowProcessCriteria;
import net.ccc.apps.core.service.dto.WorkflowProcessDTO;
import net.ccc.apps.core.service.mapper.WorkflowProcessMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link WorkflowProcess} entities in the database.
 * The main input is a {@link WorkflowProcessCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WorkflowProcessDTO} or a {@link Page} of {@link WorkflowProcessDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WorkflowProcessQueryService extends QueryService<WorkflowProcess> {

    private final Logger log = LoggerFactory.getLogger(WorkflowProcessQueryService.class);

    @Autowired
    private WorkflowProcessRepository workflowProcessRepository;

    @Autowired
    private WorkflowProcessMapper workflowProcessMapper;

    /**
     * Return a {@link List} of {@link WorkflowProcessDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WorkflowProcessDTO> findByCriteria(WorkflowProcessCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<WorkflowProcess> specification = createSpecification(criteria);
        return workflowProcessMapper.toDto(workflowProcessRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WorkflowProcessDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkflowProcessDTO> findByCriteria(WorkflowProcessCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<WorkflowProcess> specification = createSpecification(criteria);
        return workflowProcessRepository.findAll(specification, page).map(workflowProcessMapper :: toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(WorkflowProcessCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<WorkflowProcess> specification = createSpecification(criteria);
        return workflowProcessRepository.count(specification);
    }

    /**
     * Function to convert {@link WorkflowProcessCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<WorkflowProcess> createSpecification(WorkflowProcessCriteria criteria) {
        Specification<WorkflowProcess> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), WorkflowProcess_.id));
            }
            if (criteria.getFormId() != null) {
                specification = specification.and(buildSpecification(criteria.getFormId(), WorkflowProcess_.formId));
            }
            if (criteria.getFormType() != null) {
                specification = specification.and(buildSpecification(criteria.getFormType(), WorkflowProcess_.formType));
            }
            if (criteria.getInitiationTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInitiationTime(), WorkflowProcess_.initiationTime));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), WorkflowProcess_.description));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStatus(), WorkflowProcess_.status));
            }
            if (criteria.getWorkflowStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getWorkflowStepId(), root -> root.join(WorkflowProcess_.workflowSteps, JoinType.LEFT).get(WorkflowStep_.id)));
            }
            if (criteria.getInitiatedById() != null) {
                specification = specification.and(buildSpecification(criteria.getInitiatedById(), root -> root.join(WorkflowProcess_.initiatedBy, JoinType.LEFT).get(AppUser_.userId)));
            }
        }
        return specification;
    }
}
