package net.ccc.apps.campmanage.service;

import java.util.Optional;
import net.ccc.apps.campmanage.domain.BedDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link BedDetails}.
 */
public interface BedDetailsService {
    /**
     * Save a bedDetails.
     *
     * @param bedDetails the entity to save.
     * @return the persisted entity.
     */
    BedDetails save(BedDetails bedDetails);

    /**
     * Updates a bedDetails.
     *
     * @param bedDetails the entity to update.
     * @return the persisted entity.
     */
    BedDetails update(BedDetails bedDetails);

    /**
     * Partially updates a bedDetails.
     *
     * @param bedDetails the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BedDetails> partialUpdate(BedDetails bedDetails);

    /**
     * Get all the bedDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BedDetails> findAll(Pageable pageable);

    /**
     * Get the "id" bedDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BedDetails> findOne(Long id);

    /**
     * Delete the "id" bedDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
