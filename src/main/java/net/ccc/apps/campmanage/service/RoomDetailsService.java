package net.ccc.apps.campmanage.service;

import java.util.Optional;
import net.ccc.apps.campmanage.domain.RoomDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link RoomDetails}.
 */
public interface RoomDetailsService {
    /**
     * Save a roomDetails.
     *
     * @param roomDetails the entity to save.
     * @return the persisted entity.
     */
    RoomDetails save(RoomDetails roomDetails);

    /**
     * Updates a roomDetails.
     *
     * @param roomDetails the entity to update.
     * @return the persisted entity.
     */
    RoomDetails update(RoomDetails roomDetails);

    /**
     * Partially updates a roomDetails.
     *
     * @param roomDetails the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RoomDetails> partialUpdate(RoomDetails roomDetails);

    /**
     * Get all the roomDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RoomDetails> findAll(Pageable pageable);

    /**
     * Get the "id" roomDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RoomDetails> findOne(Long id);

    /**
     * Delete the "id" roomDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
