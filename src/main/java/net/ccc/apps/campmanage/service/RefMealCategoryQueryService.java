package net.ccc.apps.campmanage.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.campmanage.domain.*; // for static metamodels
import net.ccc.apps.campmanage.domain.RefMealCategory;
import net.ccc.apps.campmanage.repository.RefMealCategoryRepository;
import net.ccc.apps.campmanage.service.criteria.RefMealCategoryCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RefMealCategory} entities in the database.
 * The main input is a {@link RefMealCategoryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RefMealCategory} or a {@link Page} of {@link RefMealCategory} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RefMealCategoryQueryService extends QueryService<RefMealCategory> {

    private final Logger log = LoggerFactory.getLogger(RefMealCategoryQueryService.class);

    private final RefMealCategoryRepository refMealCategoryRepository;

    public RefMealCategoryQueryService(RefMealCategoryRepository refMealCategoryRepository) {
        this.refMealCategoryRepository = refMealCategoryRepository;
    }

    /**
     * Return a {@link List} of {@link RefMealCategory} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RefMealCategory> findByCriteria(RefMealCategoryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RefMealCategory> specification = createSpecification(criteria);
        return refMealCategoryRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link RefMealCategory} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RefMealCategory> findByCriteria(RefMealCategoryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RefMealCategory> specification = createSpecification(criteria);
        return refMealCategoryRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RefMealCategoryCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RefMealCategory> specification = createSpecification(criteria);
        return refMealCategoryRepository.count(specification);
    }

    /**
     * Function to convert {@link RefMealCategoryCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RefMealCategory> createSpecification(RefMealCategoryCriteria criteria) {
        Specification<RefMealCategory> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RefMealCategory_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), RefMealCategory_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), RefMealCategory_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), RefMealCategory_.description));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), RefMealCategory_.createdBy));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), RefMealCategory_.createdAt));
            }
            if (criteria.getModifyBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyBy(), RefMealCategory_.modifyBy));
            }
            if (criteria.getModifyAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyAt(), RefMealCategory_.modifyAt));
            }
            if (criteria.getEmployeeMasterId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getEmployeeMasterId(),
                            root -> root.join(RefMealCategory_.employeeMaster, JoinType.LEFT).get(EmployeeMaster_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
