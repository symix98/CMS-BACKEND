package net.ccc.apps.campmanage.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.campmanage.domain.*; // for static metamodels
import net.ccc.apps.campmanage.domain.BedDetails;
import net.ccc.apps.campmanage.repository.BedDetailsRepository;
import net.ccc.apps.campmanage.service.criteria.BedDetailsCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link BedDetails} entities in the database.
 * The main input is a {@link BedDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BedDetails} or a {@link Page} of {@link BedDetails} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BedDetailsQueryService extends QueryService<BedDetails> {

    private final Logger log = LoggerFactory.getLogger(BedDetailsQueryService.class);

    private final BedDetailsRepository bedDetailsRepository;

    public BedDetailsQueryService(BedDetailsRepository bedDetailsRepository) {
        this.bedDetailsRepository = bedDetailsRepository;
    }

    /**
     * Return a {@link List} of {@link BedDetails} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BedDetails> findByCriteria(BedDetailsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BedDetails> specification = createSpecification(criteria);
        return bedDetailsRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link BedDetails} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BedDetails> findByCriteria(BedDetailsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BedDetails> specification = createSpecification(criteria);
        return bedDetailsRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BedDetailsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BedDetails> specification = createSpecification(criteria);
        return bedDetailsRepository.count(specification);
    }

    /**
     * Function to convert {@link BedDetailsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<BedDetails> createSpecification(BedDetailsCriteria criteria) {
        Specification<BedDetails> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), BedDetails_.id));
            }
            if (criteria.getBedNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBedNo(), BedDetails_.bedNo));
            }
            if (criteria.getBedStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBedStatus(), BedDetails_.bedStatus));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), BedDetails_.remarks));
            }
            if (criteria.getRoomDetailsId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRoomDetailsId(),
                            root -> root.join(BedDetails_.roomDetails, JoinType.LEFT).get(RoomDetails_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
