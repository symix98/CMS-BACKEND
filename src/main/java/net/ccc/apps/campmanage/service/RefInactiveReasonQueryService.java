package net.ccc.apps.campmanage.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.campmanage.domain.*; // for static metamodels
import net.ccc.apps.campmanage.domain.RefInactiveReason;
import net.ccc.apps.campmanage.repository.RefInactiveReasonRepository;
import net.ccc.apps.campmanage.service.criteria.RefInactiveReasonCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RefInactiveReason} entities in the database.
 * The main input is a {@link RefInactiveReasonCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RefInactiveReason} or a {@link Page} of {@link RefInactiveReason} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RefInactiveReasonQueryService extends QueryService<RefInactiveReason> {

    private final Logger log = LoggerFactory.getLogger(RefInactiveReasonQueryService.class);

    private final RefInactiveReasonRepository refInactiveReasonRepository;

    public RefInactiveReasonQueryService(RefInactiveReasonRepository refInactiveReasonRepository) {
        this.refInactiveReasonRepository = refInactiveReasonRepository;
    }

    /**
     * Return a {@link List} of {@link RefInactiveReason} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RefInactiveReason> findByCriteria(RefInactiveReasonCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RefInactiveReason> specification = createSpecification(criteria);
        return refInactiveReasonRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link RefInactiveReason} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RefInactiveReason> findByCriteria(RefInactiveReasonCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RefInactiveReason> specification = createSpecification(criteria);
        return refInactiveReasonRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RefInactiveReasonCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RefInactiveReason> specification = createSpecification(criteria);
        return refInactiveReasonRepository.count(specification);
    }

    /**
     * Function to convert {@link RefInactiveReasonCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RefInactiveReason> createSpecification(RefInactiveReasonCriteria criteria) {
        Specification<RefInactiveReason> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RefInactiveReason_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), RefInactiveReason_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), RefInactiveReason_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), RefInactiveReason_.description));
            }
            if (criteria.getCategory() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCategory(), RefInactiveReason_.category));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), RefInactiveReason_.createdBy));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), RefInactiveReason_.createdAt));
            }
            if (criteria.getModifyBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyBy(), RefInactiveReason_.modifyBy));
            }
            if (criteria.getModifyAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyAt(), RefInactiveReason_.modifyAt));
            }
            if (criteria.getEmployeeMasterId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getEmployeeMasterId(),
                            root -> root.join(RefInactiveReason_.employeeMaster, JoinType.LEFT).get(EmployeeMaster_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
