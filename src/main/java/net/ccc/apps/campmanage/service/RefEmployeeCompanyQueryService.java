package net.ccc.apps.campmanage.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.campmanage.domain.*; // for static metamodels
import net.ccc.apps.campmanage.domain.RefEmployeeCompany;
import net.ccc.apps.campmanage.repository.RefEmployeeCompanyRepository;
import net.ccc.apps.campmanage.service.criteria.RefEmployeeCompanyCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RefEmployeeCompany} entities in the database.
 * The main input is a {@link RefEmployeeCompanyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RefEmployeeCompany} or a {@link Page} of {@link RefEmployeeCompany} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RefEmployeeCompanyQueryService extends QueryService<RefEmployeeCompany> {

    private final Logger log = LoggerFactory.getLogger(RefEmployeeCompanyQueryService.class);

    private final RefEmployeeCompanyRepository refEmployeeCompanyRepository;

    public RefEmployeeCompanyQueryService(RefEmployeeCompanyRepository refEmployeeCompanyRepository) {
        this.refEmployeeCompanyRepository = refEmployeeCompanyRepository;
    }

    /**
     * Return a {@link List} of {@link RefEmployeeCompany} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RefEmployeeCompany> findByCriteria(RefEmployeeCompanyCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RefEmployeeCompany> specification = createSpecification(criteria);
        return refEmployeeCompanyRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link RefEmployeeCompany} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RefEmployeeCompany> findByCriteria(RefEmployeeCompanyCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RefEmployeeCompany> specification = createSpecification(criteria);
        return refEmployeeCompanyRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RefEmployeeCompanyCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RefEmployeeCompany> specification = createSpecification(criteria);
        return refEmployeeCompanyRepository.count(specification);
    }

    /**
     * Function to convert {@link RefEmployeeCompanyCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RefEmployeeCompany> createSpecification(RefEmployeeCompanyCriteria criteria) {
        Specification<RefEmployeeCompany> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RefEmployeeCompany_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), RefEmployeeCompany_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), RefEmployeeCompany_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), RefEmployeeCompany_.description));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), RefEmployeeCompany_.createdBy));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), RefEmployeeCompany_.createdAt));
            }
            if (criteria.getModifyBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyBy(), RefEmployeeCompany_.modifyBy));
            }
            if (criteria.getModifyAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyAt(), RefEmployeeCompany_.modifyAt));
            }
            if (criteria.getEmployeeMasterId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getEmployeeMasterId(),
                            root -> root.join(RefEmployeeCompany_.employeeMaster, JoinType.LEFT).get(EmployeeMaster_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
