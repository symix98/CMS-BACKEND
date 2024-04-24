package net.ccc.apps.campmanage.service;

import java.util.List;
import java.util.Optional;
import net.ccc.apps.campmanage.domain.Catering;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Catering}.
 */
public interface CateringService {
    /**
     * Save a catering.
     *
     * @param catering the entity to save.
     * @return the persisted entity.
     */
    Catering save(Catering catering);

    /**
     * Updates a catering.
     *
     * @param catering the entity to update.
     * @return the persisted entity.
     */
    Catering update(Catering catering);

    /**
     * Partially updates a catering.
     *
     * @param catering the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Catering> partialUpdate(Catering catering);

    /**
     * Get all the caterings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Catering> findAll(Pageable pageable);
    /**
     * Get all the Catering where RoomDetails is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Catering> findAllWhereRoomDetailsIsNull();

    /**
     * Get the "id" catering.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Catering> findOne(Long id);

    /**
     * Delete the "id" catering.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
