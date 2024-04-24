package net.ccc.apps.campmanage.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.campmanage.domain.*; // for static metamodels
import net.ccc.apps.campmanage.domain.EmployeeMaster;
import net.ccc.apps.campmanage.repository.EmployeeMasterRepository;
import net.ccc.apps.campmanage.service.criteria.EmployeeMasterCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link EmployeeMaster} entities in the database.
 * The main input is a {@link EmployeeMasterCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EmployeeMaster} or a {@link Page} of {@link EmployeeMaster} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EmployeeMasterQueryService extends QueryService<EmployeeMaster> {

    private final Logger log = LoggerFactory.getLogger(EmployeeMasterQueryService.class);

    private final EmployeeMasterRepository employeeMasterRepository;

    public EmployeeMasterQueryService(EmployeeMasterRepository employeeMasterRepository) {
        this.employeeMasterRepository = employeeMasterRepository;
    }

    /**
     * Return a {@link List} of {@link EmployeeMaster} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EmployeeMaster> findByCriteria(EmployeeMasterCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EmployeeMaster> specification = createSpecification(criteria);
        return employeeMasterRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link EmployeeMaster} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EmployeeMaster> findByCriteria(EmployeeMasterCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EmployeeMaster> specification = createSpecification(criteria);
        return employeeMasterRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EmployeeMasterCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EmployeeMaster> specification = createSpecification(criteria);
        return employeeMasterRepository.count(specification);
    }

    /**
     * Function to convert {@link EmployeeMasterCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EmployeeMaster> createSpecification(EmployeeMasterCriteria criteria) {
        Specification<EmployeeMaster> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EmployeeMaster_.id));
            }
            if (criteria.getBadgeNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBadgeNo(), EmployeeMaster_.badgeNo));
            }
            if (criteria.getEmployeeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeeName(), EmployeeMaster_.employeeName));
            }
            if (criteria.getJobTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJobTitle(), EmployeeMaster_.jobTitle));
            }
            if (criteria.getDepartment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDepartment(), EmployeeMaster_.department));
            }
            if (criteria.getNationality() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNationality(), EmployeeMaster_.nationality));
            }
            if (criteria.getCategory() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCategory(), EmployeeMaster_.category));
            }
            if (criteria.getContractBase() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContractBase(), EmployeeMaster_.contractBase));
            }
            if (criteria.getBand() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBand(), EmployeeMaster_.band));
            }
            if (criteria.getEqvBand() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEqvBand(), EmployeeMaster_.eqvBand));
            }
            if (criteria.getProject() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProject(), EmployeeMaster_.project));
            }
            if (criteria.getIsCcc() != null) {
                specification = specification.and(buildSpecification(criteria.getIsCcc(), EmployeeMaster_.isCcc));
            }
            if (criteria.getCompany() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompany(), EmployeeMaster_.company));
            }
            if (criteria.getWorkLocation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWorkLocation(), EmployeeMaster_.workLocation));
            }
            if (criteria.getMessEntitlment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMessEntitlment(), EmployeeMaster_.messEntitlment));
            }
            if (criteria.getMealCategory() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMealCategory(), EmployeeMaster_.mealCategory));
            }
            if (criteria.getMealType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMealType(), EmployeeMaster_.mealType));
            }
            if (criteria.getReligion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReligion(), EmployeeMaster_.religion));
            }
            if (criteria.getEmployeeActive() != null) {
                specification = specification.and(buildSpecification(criteria.getEmployeeActive(), EmployeeMaster_.employeeActive));
            }
            if (criteria.getInactiveReason() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInactiveReason(), EmployeeMaster_.inactiveReason));
            }
            if (criteria.getMobileNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobileNo(), EmployeeMaster_.mobileNo));
            }
            if (criteria.getPassportNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPassportNo(), EmployeeMaster_.passportNo));
            }
            if (criteria.getQidNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getQidNo(), EmployeeMaster_.qidNo));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), EmployeeMaster_.email));
            }
            if (criteria.getMessCard() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMessCard(), EmployeeMaster_.messCard));
            }
            if (criteria.getMilkCard() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMilkCard(), EmployeeMaster_.milkCard));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), EmployeeMaster_.createdBy));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), EmployeeMaster_.createdAt));
            }
            if (criteria.getModifyBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyBy(), EmployeeMaster_.modifyBy));
            }
            if (criteria.getModifyAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyAt(), EmployeeMaster_.modifyAt));
            }
            if (criteria.getRefTradeId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRefTradeId(),
                            root -> root.join(EmployeeMaster_.refTrade, JoinType.LEFT).get(RefTrade_.id)
                        )
                    );
            }
            if (criteria.getRefNationalityId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRefNationalityId(),
                            root -> root.join(EmployeeMaster_.refNationality, JoinType.LEFT).get(RefNationality_.id)
                        )
                    );
            }
            if (criteria.getRefProjectId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRefProjectId(),
                            root -> root.join(EmployeeMaster_.refProject, JoinType.LEFT).get(RefProject_.id)
                        )
                    );
            }
            if (criteria.getRefEmployeeCompanyId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRefEmployeeCompanyId(),
                            root -> root.join(EmployeeMaster_.refEmployeeCompany, JoinType.LEFT).get(RefEmployeeCompany_.id)
                        )
                    );
            }
            if (criteria.getRefMealCategoryId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRefMealCategoryId(),
                            root -> root.join(EmployeeMaster_.refMealCategory, JoinType.LEFT).get(RefMealCategory_.id)
                        )
                    );
            }
            if (criteria.getRefInactiveReasonId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRefInactiveReasonId(),
                            root -> root.join(EmployeeMaster_.refInactiveReason, JoinType.LEFT).get(RefInactiveReason_.id)
                        )
                    );
            }
            if (criteria.getBookingId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getBookingId(),
                            root -> root.join(EmployeeMaster_.bookings, JoinType.LEFT).get(Booking_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
