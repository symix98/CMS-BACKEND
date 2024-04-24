package net.ccc.apps.campmanage.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.campmanage.domain.*; // for static metamodels
import net.ccc.apps.campmanage.domain.RoomDetails;
import net.ccc.apps.campmanage.repository.RoomDetailsRepository;
import net.ccc.apps.campmanage.service.criteria.RoomDetailsCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link RoomDetails} entities in the database.
 * The main input is a {@link RoomDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RoomDetails} or a {@link Page} of {@link RoomDetails} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RoomDetailsQueryService extends QueryService<RoomDetails> {

    private final Logger log = LoggerFactory.getLogger(RoomDetailsQueryService.class);

    private final RoomDetailsRepository roomDetailsRepository;

    public RoomDetailsQueryService(RoomDetailsRepository roomDetailsRepository) {
        this.roomDetailsRepository = roomDetailsRepository;
    }

    /**
     * Return a {@link List} of {@link RoomDetails} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RoomDetails> findByCriteria(RoomDetailsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RoomDetails> specification = createSpecification(criteria);
        return roomDetailsRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link RoomDetails} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RoomDetails> findByCriteria(RoomDetailsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RoomDetails> specification = createSpecification(criteria);
        return roomDetailsRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RoomDetailsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RoomDetails> specification = createSpecification(criteria);
        return roomDetailsRepository.count(specification);
    }

    /**
     * Function to convert {@link RoomDetailsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<RoomDetails> createSpecification(RoomDetailsCriteria criteria) {
        Specification<RoomDetails> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), RoomDetails_.id));
            }
            if (criteria.getRoomDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRoomDescription(), RoomDetails_.roomDescription));
            }
            if (criteria.getBlock() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBlock(), RoomDetails_.block));
            }
            if (criteria.getFloor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFloor(), RoomDetails_.floor));
            }
            if (criteria.getRoomCategory() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRoomCategory(), RoomDetails_.roomCategory));
            }
            if (criteria.getBedCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBedCount(), RoomDetails_.bedCount));
            }
            if (criteria.getRoomNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRoomNo(), RoomDetails_.roomNo));
            }
            if (criteria.getRoomAllocation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRoomAllocation(), RoomDetails_.roomAllocation));
            }
            if (criteria.getRoomConfiguration() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getRoomConfiguration(), RoomDetails_.roomConfiguration));
            }
            if (criteria.getAvailableFrom() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAvailableFrom(), RoomDetails_.availableFrom));
            }
            if (criteria.getRoomStatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRoomStatus(), RoomDetails_.roomStatus));
            }
            if (criteria.getBedOnly() != null) {
                specification = specification.and(buildSpecification(criteria.getBedOnly(), RoomDetails_.bedOnly));
            }
            if (criteria.getMonthlyRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonthlyRate(), RoomDetails_.monthlyRate));
            }
            if (criteria.getDailyRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDailyRate(), RoomDetails_.dailyRate));
            }
            if (criteria.getBedRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBedRate(), RoomDetails_.bedRate));
            }
            if (criteria.getReservationRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReservationRate(), RoomDetails_.reservationRate));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), RoomDetails_.remarks));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), RoomDetails_.createdBy));
            }
            if (criteria.getCreatedAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedAt(), RoomDetails_.createdAt));
            }
            if (criteria.getModifyBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyBy(), RoomDetails_.modifyBy));
            }
            if (criteria.getModifyAt() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyAt(), RoomDetails_.modifyAt));
            }
            if (criteria.getCateringId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getCateringId(),
                            root -> root.join(RoomDetails_.catering, JoinType.LEFT).get(Catering_.id)
                        )
                    );
            }
            if (criteria.getBedDetailsId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getBedDetailsId(),
                            root -> root.join(RoomDetails_.bedDetails, JoinType.LEFT).get(BedDetails_.id)
                        )
                    );
            }
            if (criteria.getRoomAdvanceBookingId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRoomAdvanceBookingId(),
                            root -> root.join(RoomDetails_.roomAdvanceBookings, JoinType.LEFT).get(RoomAdvanceBooking_.id)
                        )
                    );
            }
            if (criteria.getBookingId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getBookingId(),
                            root -> root.join(RoomDetails_.bookings, JoinType.LEFT).get(Booking_.id)
                        )
                    );
            }
            if (criteria.getCampId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCampId(), root -> root.join(RoomDetails_.camp, JoinType.LEFT).get(Camp_.id))
                    );
            }
        }
        return specification;
    }
}
