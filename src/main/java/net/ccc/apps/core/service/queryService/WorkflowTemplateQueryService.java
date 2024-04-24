package net.ccc.apps.core.service.queryService;

import java.util.List;

import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.WorkflowTemplate;
import net.ccc.apps.core.repository.WorkflowTemplateRepository;
import net.ccc.apps.core.service.criteria.WorkflowTemplateCriteria;
import net.ccc.apps.core.service.dto.WorkflowTemplateDTO;
import net.ccc.apps.core.service.mapper.WorkflowTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link WorkflowTemplate} entities in the database.
 * The main input is a {@link WorkflowTemplateCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WorkflowTemplateDTO} or a {@link Page} of {@link WorkflowTemplateDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WorkflowTemplateQueryService extends QueryService<WorkflowTemplate> {

    private final Logger log = LoggerFactory.getLogger(WorkflowTemplateQueryService.class);

    private final WorkflowTemplateRepository workflowTemplateRepository;

    private final WorkflowTemplateMapper workflowTemplateMapper;

    public WorkflowTemplateQueryService(
        WorkflowTemplateRepository workflowTemplateRepository,
        WorkflowTemplateMapper workflowTemplateMapper
    ) {
        this.workflowTemplateRepository = workflowTemplateRepository;
        this.workflowTemplateMapper = workflowTemplateMapper;
    }

    /**
     * Return a {@link List} of {@link WorkflowTemplateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WorkflowTemplateDTO> findByCriteria(WorkflowTemplateCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<WorkflowTemplate> specification = createSpecification(criteria);
        return workflowTemplateMapper.toDto(workflowTemplateRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WorkflowTemplateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkflowTemplateDTO> findByCriteria(WorkflowTemplateCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<WorkflowTemplate> specification = createSpecification(criteria);
        return workflowTemplateRepository.findAll(specification, page).map(workflowTemplateMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(WorkflowTemplateCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<WorkflowTemplate> specification = createSpecification(criteria);
        return workflowTemplateRepository.count(specification);
    }

    /**
     * Function to convert {@link WorkflowTemplateCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    public Specification<WorkflowTemplate> createSpecification(WorkflowTemplateCriteria criteria) {
        Specification<WorkflowTemplate> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), WorkflowTemplate_.id));
            }
            if (criteria.getSequence() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSequence(), WorkflowTemplate_.sequence));
            }
            if (criteria.getFormType() != null) {
                specification = specification.and(buildSpecification(criteria.getFormType(), WorkflowTemplate_.formType));
            }
            if (criteria.getStepName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStepName(), WorkflowTemplate_.stepName));
            }
            if (criteria.getActionDescription() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getActionDescription(), WorkflowTemplate_.actionDescription));
            }
            if (criteria.getRoles() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRoles(), WorkflowTemplate_.roles));
            }
            if (criteria.getInitialStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInitialStatus(), WorkflowTemplate_.initialStatus));
            }
            if (criteria.getSuccessStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSuccessStatus(), WorkflowTemplate_.successStatus));
            }
            if (criteria.getEnabled() != null) {
                specification = specification.and(buildSpecification(criteria.getEnabled(), WorkflowTemplate_.enabled));
            }
            if (criteria.getSignOffRule() != null) {
                specification = specification.and(buildSpecification(criteria.getSignOffRule(), WorkflowTemplate_.signOffRule));
            }
            if (criteria.getMultipleActionUsers() != null) {
                specification =
                    specification.and(buildSpecification(criteria.getMultipleActionUsers(), WorkflowTemplate_.multipleActionUsers));
            }
        }
        return specification;
    }
}
