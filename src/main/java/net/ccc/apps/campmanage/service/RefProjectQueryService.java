package net.ccc.apps.campmanage.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.campmanage.domain.*; // for static metamodels
import net.ccc.apps.campmanage.domain.RefProject;
import net.ccc.apps.campmanage.repository.RefProjectRepository;
import net.ccc.apps.campmanage.service.criteria.RefProjectCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RefProject} entities in the database.
 * The main input is a {@link RefProjectCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RefProject} or a {@link Page} of {@link RefProject} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RefProjectQueryService extends QueryService<RefProject> {

    private final Logger log = LoggerFactory.getLogger(RefProjectQueryService.class);

    private final RefProjectRepository refProjectRepository;

    public RefProjectQueryService(RefProjectRepository refProjectRepository) {
        this.refProjectRepository = refProjectRepository;
    }

    /**
     * Return a {@link List} of {@link RefProject} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RefProject> findByCriteria(RefProjectCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RefProject> specification = createSpecification(criteria);
        return refProjectRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link RefProject} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RefProject> findByCriteria(RefProjectCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RefProject> specification = createSpecification(criteria);
        return refProjectRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RefProjectCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RefProject> specification = createSpecification(criteria);
        return refProjectRepository.count(specification);
    }

    /**
     * Function to convert {@link RefProjectCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RefProject> createSpecification(RefProjectCriteria criteria) {
        Specification<RefProject> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RefProject_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), RefProject_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), RefProject_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), RefProject_.description));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), RefProject_.createdBy));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), RefProject_.createdAt));
            }
            if (criteria.getModifyBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyBy(), RefProject_.modifyBy));
            }
            if (criteria.getModifyAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyAt(), RefProject_.modifyAt));
            }
            if (criteria.getEmployeeMasterId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getEmployeeMasterId(),
                            root -> root.join(RefProject_.employeeMaster, JoinType.LEFT).get(EmployeeMaster_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
