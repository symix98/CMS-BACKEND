package net.ccc.apps.campmanage.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.campmanage.domain.*; // for static metamodels
import net.ccc.apps.campmanage.domain.RefNationality;
import net.ccc.apps.campmanage.repository.RefNationalityRepository;
import net.ccc.apps.campmanage.service.criteria.RefNationalityCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RefNationality} entities in the database.
 * The main input is a {@link RefNationalityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RefNationality} or a {@link Page} of {@link RefNationality} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RefNationalityQueryService extends QueryService<RefNationality> {

    private final Logger log = LoggerFactory.getLogger(RefNationalityQueryService.class);

    private final RefNationalityRepository refNationalityRepository;

    public RefNationalityQueryService(RefNationalityRepository refNationalityRepository) {
        this.refNationalityRepository = refNationalityRepository;
    }

    /**
     * Return a {@link List} of {@link RefNationality} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RefNationality> findByCriteria(RefNationalityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RefNationality> specification = createSpecification(criteria);
        return refNationalityRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link RefNationality} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RefNationality> findByCriteria(RefNationalityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RefNationality> specification = createSpecification(criteria);
        return refNationalityRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RefNationalityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RefNationality> specification = createSpecification(criteria);
        return refNationalityRepository.count(specification);
    }

    /**
     * Function to convert {@link RefNationalityCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RefNationality> createSpecification(RefNationalityCriteria criteria) {
        Specification<RefNationality> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RefNationality_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), RefNationality_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), RefNationality_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), RefNationality_.description));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), RefNationality_.createdBy));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), RefNationality_.createdAt));
            }
            if (criteria.getModifyBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyBy(), RefNationality_.modifyBy));
            }
            if (criteria.getModifyAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyAt(), RefNationality_.modifyAt));
            }
            if (criteria.getEmployeeMasterId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getEmployeeMasterId(),
                            root -> root.join(RefNationality_.employeeMaster, JoinType.LEFT).get(EmployeeMaster_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
