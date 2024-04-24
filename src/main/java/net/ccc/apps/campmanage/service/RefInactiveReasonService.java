package net.ccc.apps.campmanage.service;

import java.util.List;
import java.util.Optional;
import net.ccc.apps.campmanage.domain.RefInactiveReason;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link RefInactiveReason}.
 */
public interface RefInactiveReasonService {
    /**
     * Save a refInactiveReason.
     *
     * @param refInactiveReason the entity to save.
     * @return the persisted entity.
     */
    RefInactiveReason save(RefInactiveReason refInactiveReason);

    /**
     * Updates a refInactiveReason.
     *
     * @param refInactiveReason the entity to update.
     * @return the persisted entity.
     */
    RefInactiveReason update(RefInactiveReason refInactiveReason);

    /**
     * Partially updates a refInactiveReason.
     *
     * @param refInactiveReason the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RefInactiveReason> partialUpdate(RefInactiveReason refInactiveReason);

    /**
     * Get all the refInactiveReasons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RefInactiveReason> findAll(Pageable pageable);
    /**
     * Get all the RefInactiveReason where EmployeeMaster is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<RefInactiveReason> findAllWhereEmployeeMasterIsNull();

    /**
     * Get the "id" refInactiveReason.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RefInactiveReason> findOne(Long id);

    /**
     * Delete the "id" refInactiveReason.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
