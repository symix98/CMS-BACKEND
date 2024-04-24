package net.ccc.apps.campmanage.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.campmanage.domain.*; // for static metamodels
import net.ccc.apps.campmanage.domain.Camp;
import net.ccc.apps.campmanage.repository.CampRepository;
import net.ccc.apps.campmanage.service.criteria.CampCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Camp} entities in the database.
 * The main input is a {@link CampCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Camp} or a {@link Page} of {@link Camp} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CampQueryService extends QueryService<Camp> {

    private final Logger log = LoggerFactory.getLogger(CampQueryService.class);

    private final CampRepository campRepository;

    public CampQueryService(CampRepository campRepository) {
        this.campRepository = campRepository;
    }

    /**
     * Return a {@link List} of {@link Camp} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Camp> findByCriteria(CampCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Camp> specification = createSpecification(criteria);
        return campRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Camp} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Camp> findByCriteria(CampCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Camp> specification = createSpecification(criteria);
        return campRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CampCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Camp> specification = createSpecification(criteria);
        return campRepository.count(specification);
    }

    /**
     * Function to convert {@link CampCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Camp> createSpecification(CampCriteria criteria) {
        Specification<Camp> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Camp_.id));
            }
            if (criteria.getCampName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCampName(), Camp_.campName));
            }
            if (criteria.getLocation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocation(), Camp_.location));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), Camp_.remarks));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Camp_.createdBy));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), Camp_.createdAt));
            }
            if (criteria.getModifyBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyBy(), Camp_.modifyBy));
            }
            if (criteria.getModifyAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyAt(), Camp_.modifyAt));
            }
            if (criteria.getRoomDetailsId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRoomDetailsId(),
                            root -> root.join(Camp_.roomDetails, JoinType.LEFT).get(RoomDetails_.id)
                        )
                    );
            }
            if (criteria.getCompanyId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCompanyId(), root -> root.join(Camp_.company, JoinType.LEFT).get(Company_.id))
                    );
            }
        }
        return specification;
    }
}
