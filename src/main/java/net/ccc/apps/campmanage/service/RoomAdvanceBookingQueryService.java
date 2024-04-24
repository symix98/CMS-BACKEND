package net.ccc.apps.campmanage.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.campmanage.domain.*; // for static metamodels
import net.ccc.apps.campmanage.domain.RoomAdvanceBooking;
import net.ccc.apps.campmanage.repository.RoomAdvanceBookingRepository;
import net.ccc.apps.campmanage.service.criteria.RoomAdvanceBookingCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RoomAdvanceBooking} entities in the database.
 * The main input is a {@link RoomAdvanceBookingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RoomAdvanceBooking} or a {@link Page} of {@link RoomAdvanceBooking} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RoomAdvanceBookingQueryService extends QueryService<RoomAdvanceBooking> {

    private final Logger log = LoggerFactory.getLogger(RoomAdvanceBookingQueryService.class);

    private final RoomAdvanceBookingRepository roomAdvanceBookingRepository;

    public RoomAdvanceBookingQueryService(RoomAdvanceBookingRepository roomAdvanceBookingRepository) {
        this.roomAdvanceBookingRepository = roomAdvanceBookingRepository;
    }

    /**
     * Return a {@link List} of {@link RoomAdvanceBooking} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RoomAdvanceBooking> findByCriteria(RoomAdvanceBookingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RoomAdvanceBooking> specification = createSpecification(criteria);
        return roomAdvanceBookingRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link RoomAdvanceBooking} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RoomAdvanceBooking> findByCriteria(RoomAdvanceBookingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RoomAdvanceBooking> specification = createSpecification(criteria);
        return roomAdvanceBookingRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RoomAdvanceBookingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RoomAdvanceBooking> specification = createSpecification(criteria);
        return roomAdvanceBookingRepository.count(specification);
    }

    /**
     * Function to convert {@link RoomAdvanceBookingCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RoomAdvanceBooking> createSpecification(RoomAdvanceBookingCriteria criteria) {
        Specification<RoomAdvanceBooking> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RoomAdvanceBooking_.id));
            }
            if (criteria.getBookingStartDate() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getBookingStartDate(), RoomAdvanceBooking_.bookingStartDate));
            }
            if (criteria.getBookingEndDate() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getBookingEndDate(), RoomAdvanceBooking_.bookingEndDate));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), RoomAdvanceBooking_.remarks));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), RoomAdvanceBooking_.createdBy));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), RoomAdvanceBooking_.createdAt));
            }
            if (criteria.getModifyBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyBy(), RoomAdvanceBooking_.modifyBy));
            }
            if (criteria.getModifyAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyAt(), RoomAdvanceBooking_.modifyAt));
            }
            if (criteria.getRoomDetailsId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRoomDetailsId(),
                            root -> root.join(RoomAdvanceBooking_.roomDetails, JoinType.LEFT).get(RoomDetails_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
