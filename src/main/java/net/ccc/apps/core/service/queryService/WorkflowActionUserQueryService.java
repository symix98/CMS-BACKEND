package net.ccc.apps.core.service.queryService;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.WorkflowActionUser;
import net.ccc.apps.core.repository.WorkflowActionUserRepository;
import net.ccc.apps.core.service.criteria.WorkflowActionUserCriteria;
import net.ccc.apps.core.service.dto.WorkflowActionUserDTO;
import net.ccc.apps.core.service.mapper.WorkflowActionUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link WorkflowActionUser} entities in the database.
 * The main input is a {@link WorkflowActionUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WorkflowActionUserDTO} or a {@link Page} of {@link WorkflowActionUserDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WorkflowActionUserQueryService extends QueryService<WorkflowActionUser> {

    private final Logger log = LoggerFactory.getLogger(WorkflowActionUserQueryService.class);

    private final WorkflowActionUserRepository workflowActionUserRepository;

    private final WorkflowActionUserMapper workflowActionUserMapper;

    public WorkflowActionUserQueryService(
        WorkflowActionUserRepository workflowActionUserRepository,
        WorkflowActionUserMapper workflowActionUserMapper
    ) {
        this.workflowActionUserRepository = workflowActionUserRepository;
        this.workflowActionUserMapper = workflowActionUserMapper;
    }

    /**
     * Return a {@link List} of {@link WorkflowActionUserDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WorkflowActionUserDTO> findByCriteria(WorkflowActionUserCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<WorkflowActionUser> specification = createSpecification(criteria);
        return workflowActionUserMapper.toDto(workflowActionUserRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WorkflowActionUserDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkflowActionUserDTO> findByCriteria(WorkflowActionUserCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<WorkflowActionUser> specification = createSpecification(criteria);
        return workflowActionUserRepository.findAll(specification, page).map(workflowActionUserMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(WorkflowActionUserCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<WorkflowActionUser> specification = createSpecification(criteria);
        return workflowActionUserRepository.count(specification);
    }

    /**
     * Function to convert {@link WorkflowActionUserCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<WorkflowActionUser> createSpecification(WorkflowActionUserCriteria criteria) {
        Specification<WorkflowActionUser> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), WorkflowActionUser_.id));
            }
            if (criteria.getActionTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getActionTime(), WorkflowActionUser_.actionTime));
            }
            if (criteria.getApprove() != null) {
                specification = specification.and(buildSpecification(criteria.getApprove(), WorkflowActionUser_.approve));
            }
            if (criteria.getActionUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getActionUserId(), root -> root.join(WorkflowActionUser_.actionUser, JoinType.LEFT).get(AppUser_.USER_ID)));
            }
            if (criteria.getWorkflowStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getWorkflowStepId(), root -> root.join(WorkflowActionUser_.workflowStep, JoinType.LEFT).get(WorkflowStep_.id)));
            }
        }
        return specification;
    }
}
