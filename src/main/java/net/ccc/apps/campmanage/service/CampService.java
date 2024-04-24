package net.ccc.apps.campmanage.service;

import java.util.Optional;
import net.ccc.apps.campmanage.domain.Camp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Camp}.
 */
public interface CampService {
    /**
     * Save a camp.
     *
     * @param camp the entity to save.
     * @return the persisted entity.
     */
    Camp save(Camp camp);

    /**
     * Updates a camp.
     *
     * @param camp the entity to update.
     * @return the persisted entity.
     */
    Camp update(Camp camp);

    /**
     * Partially updates a camp.
     *
     * @param camp the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Camp> partialUpdate(Camp camp);

    /**
     * Get all the camps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Camp> findAll(Pageable pageable);

    /**
     * Get the "id" camp.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Camp> findOne(Long id);

    /**
     * Delete the "id" camp.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
