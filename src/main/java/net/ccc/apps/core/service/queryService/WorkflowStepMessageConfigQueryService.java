package net.ccc.apps.core.service.queryService;

import java.util.List;

import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.WorkflowStepMessageConfig;
import net.ccc.apps.core.repository.WorkflowStepMessageConfigRepository;
import net.ccc.apps.core.service.criteria.WorkflowStepMessageConfigCriteria;
import net.ccc.apps.core.service.dto.WorkflowStepMessageConfigDTO;
import net.ccc.apps.core.service.mapper.WorkflowStepMessageConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link WorkflowStepMessageConfig} entities in the database.
 * The main input is a {@link WorkflowStepMessageConfigCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WorkflowStepMessageConfigDTO} or a {@link Page} of {@link WorkflowStepMessageConfigDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WorkflowStepMessageConfigQueryService extends QueryService<WorkflowStepMessageConfig> {

    private final Logger log = LoggerFactory.getLogger(WorkflowStepMessageConfigQueryService.class);

    private final WorkflowStepMessageConfigRepository workflowStepMessageConfigRepository;

    private final WorkflowStepMessageConfigMapper workflowStepMessageConfigMapper;

    public WorkflowStepMessageConfigQueryService(
        WorkflowStepMessageConfigRepository workflowStepMessageConfigRepository,
        WorkflowStepMessageConfigMapper workflowStepMessageConfigMapper
    ) {
        this.workflowStepMessageConfigRepository = workflowStepMessageConfigRepository;
        this.workflowStepMessageConfigMapper = workflowStepMessageConfigMapper;
    }

    /**
     * Return a {@link List} of {@link WorkflowStepMessageConfigDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WorkflowStepMessageConfigDTO> findByCriteria(WorkflowStepMessageConfigCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<WorkflowStepMessageConfig> specification = createSpecification(criteria);
        return workflowStepMessageConfigMapper.toDto(workflowStepMessageConfigRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WorkflowStepMessageConfigDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkflowStepMessageConfigDTO> findByCriteria(WorkflowStepMessageConfigCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<WorkflowStepMessageConfig> specification = createSpecification(criteria);
        return workflowStepMessageConfigRepository.findAll(specification, page).map(workflowStepMessageConfigMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(WorkflowStepMessageConfigCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<WorkflowStepMessageConfig> specification = createSpecification(criteria);
        return workflowStepMessageConfigRepository.count(specification);
    }

    /**
     * Function to convert {@link WorkflowStepMessageConfigCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<WorkflowStepMessageConfig> createSpecification(WorkflowStepMessageConfigCriteria criteria) {
        Specification<WorkflowStepMessageConfig> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), WorkflowStepMessageConfig_.id));
            }
            if (criteria.getFormType() != null) {
                specification = specification.and(buildSpecification(criteria.getFormType(), WorkflowStepMessageConfig_.formType));
            }
            if (criteria.getStepName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStepName(), WorkflowStepMessageConfig_.stepName));
            }
            if (criteria.getAction() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAction(), WorkflowStepMessageConfig_.action));
            }
            if (criteria.getMessageId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMessageId(), WorkflowStepMessageConfig_.messageId));
            }
        }
        return specification;
    }
}
