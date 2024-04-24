package net.ccc.apps.campmanage.service;

import java.util.Optional;
import net.ccc.apps.campmanage.domain.RoomAdvanceBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link RoomAdvanceBooking}.
 */
public interface RoomAdvanceBookingService {
    /**
     * Save a roomAdvanceBooking.
     *
     * @param roomAdvanceBooking the entity to save.
     * @return the persisted entity.
     */
    RoomAdvanceBooking save(RoomAdvanceBooking roomAdvanceBooking);

    /**
     * Updates a roomAdvanceBooking.
     *
     * @param roomAdvanceBooking the entity to update.
     * @return the persisted entity.
     */
    RoomAdvanceBooking update(RoomAdvanceBooking roomAdvanceBooking);

    /**
     * Partially updates a roomAdvanceBooking.
     *
     * @param roomAdvanceBooking the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RoomAdvanceBooking> partialUpdate(RoomAdvanceBooking roomAdvanceBooking);

    /**
     * Get all the roomAdvanceBookings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RoomAdvanceBooking> findAll(Pageable pageable);

    /**
     * Get the "id" roomAdvanceBooking.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RoomAdvanceBooking> findOne(Long id);

    /**
     * Delete the "id" roomAdvanceBooking.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
