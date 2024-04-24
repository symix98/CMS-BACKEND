package net.ccc.apps.campmanage.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.campmanage.domain.*; // for static metamodels
import net.ccc.apps.campmanage.domain.RefTrade;
import net.ccc.apps.campmanage.repository.RefTradeRepository;
import net.ccc.apps.campmanage.service.criteria.RefTradeCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RefTrade} entities in the database.
 * The main input is a {@link RefTradeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RefTrade} or a {@link Page} of {@link RefTrade} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RefTradeQueryService extends QueryService<RefTrade> {

    private final Logger log = LoggerFactory.getLogger(RefTradeQueryService.class);

    private final RefTradeRepository refTradeRepository;

    public RefTradeQueryService(RefTradeRepository refTradeRepository) {
        this.refTradeRepository = refTradeRepository;
    }

    /**
     * Return a {@link List} of {@link RefTrade} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RefTrade> findByCriteria(RefTradeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RefTrade> specification = createSpecification(criteria);
        return refTradeRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link RefTrade} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RefTrade> findByCriteria(RefTradeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RefTrade> specification = createSpecification(criteria);
        return refTradeRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RefTradeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RefTrade> specification = createSpecification(criteria);
        return refTradeRepository.count(specification);
    }

    /**
     * Function to convert {@link RefTradeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RefTrade> createSpecification(RefTradeCriteria criteria) {
        Specification<RefTrade> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RefTrade_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), RefTrade_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), RefTrade_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), RefTrade_.description));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), RefTrade_.createdBy));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), RefTrade_.createdAt));
            }
            if (criteria.getModifyBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyBy(), RefTrade_.modifyBy));
            }
            if (criteria.getModifyAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyAt(), RefTrade_.modifyAt));
            }
            if (criteria.getEmployeeMasterId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getEmployeeMasterId(),
                            root -> root.join(RefTrade_.employeeMaster, JoinType.LEFT).get(EmployeeMaster_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
