package net.ccc.apps.campmanage.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.campmanage.domain.*; // for static metamodels
import net.ccc.apps.campmanage.domain.Catering;
import net.ccc.apps.campmanage.repository.CateringRepository;
import net.ccc.apps.campmanage.service.criteria.CateringCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Catering} entities in the database.
 * The main input is a {@link CateringCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Catering} or a {@link Page} of {@link Catering} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CateringQueryService extends QueryService<Catering> {

    private final Logger log = LoggerFactory.getLogger(CateringQueryService.class);

    private final CateringRepository cateringRepository;

    public CateringQueryService(CateringRepository cateringRepository) {
        this.cateringRepository = cateringRepository;
    }

    /**
     * Return a {@link List} of {@link Catering} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Catering> findByCriteria(CateringCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Catering> specification = createSpecification(criteria);
        return cateringRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Catering} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Catering> findByCriteria(CateringCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Catering> specification = createSpecification(criteria);
        return cateringRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CateringCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Catering> specification = createSpecification(criteria);
        return cateringRepository.count(specification);
    }

    /**
     * Function to convert {@link CateringCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Catering> createSpecification(CateringCriteria criteria) {
        Specification<Catering> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Catering_.id));
            }
            if (criteria.getCateringName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCateringName(), Catering_.cateringName));
            }
            if (criteria.getLocation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocation(), Catering_.location));
            }
            if (criteria.getMessCategory() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMessCategory(), Catering_.messCategory));
            }
            if (criteria.getMealType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMealType(), Catering_.mealType));
            }
            if (criteria.getMenuType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMenuType(), Catering_.menuType));
            }
            if (criteria.getRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRate(), Catering_.rate));
            }
            if (criteria.getUpgradedRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUpgradedRate(), Catering_.upgradedRate));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), Catering_.remarks));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Catering_.createdBy));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), Catering_.createdAt));
            }
            if (criteria.getModifyBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyBy(), Catering_.modifyBy));
            }
            if (criteria.getModifyAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyAt(), Catering_.modifyAt));
            }
            if (criteria.getRoomDetailsId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRoomDetailsId(),
                            root -> root.join(Catering_.roomDetails, JoinType.LEFT).get(RoomDetails_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
