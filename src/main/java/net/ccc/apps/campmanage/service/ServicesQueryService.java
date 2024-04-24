package net.ccc.apps.campmanage.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.campmanage.domain.*; // for static metamodels
import net.ccc.apps.campmanage.domain.Services;
import net.ccc.apps.campmanage.repository.ServicesRepository;
import net.ccc.apps.campmanage.service.criteria.ServicesCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Services} entities in the database.
 * The main input is a {@link ServicesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Services} or a {@link Page} of {@link Services} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ServicesQueryService extends QueryService<Services> {

    private final Logger log = LoggerFactory.getLogger(ServicesQueryService.class);

    private final ServicesRepository servicesRepository;

    public ServicesQueryService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    /**
     * Return a {@link List} of {@link Services} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Services> findByCriteria(ServicesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Services> specification = createSpecification(criteria);
        return servicesRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Services} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Services> findByCriteria(ServicesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Services> specification = createSpecification(criteria);
        return servicesRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ServicesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Services> specification = createSpecification(criteria);
        return servicesRepository.count(specification);
    }

    /**
     * Function to convert {@link ServicesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Services> createSpecification(ServicesCriteria criteria) {
        Specification<Services> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Services_.id));
            }
            if (criteria.getServiceName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getServiceName(), Services_.serviceName));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Services_.description));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Services_.createdBy));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), Services_.createdAt));
            }
            if (criteria.getModifyBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyBy(), Services_.modifyBy));
            }
            if (criteria.getModifyAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyAt(), Services_.modifyAt));
            }
            if (criteria.getCompanyId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCompanyId(), root -> root.join(Services_.company, JoinType.LEFT).get(Company_.id))
                    );
            }
        }
        return specification;
    }
}
