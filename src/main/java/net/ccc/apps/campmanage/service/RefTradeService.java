package net.ccc.apps.campmanage.service;

import java.util.List;
import java.util.Optional;
import net.ccc.apps.campmanage.domain.RefTrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link RefTrade}.
 */
public interface RefTradeService {
    /**
     * Save a refTrade.
     *
     * @param refTrade the entity to save.
     * @return the persisted entity.
     */
    RefTrade save(RefTrade refTrade);

    /**
     * Updates a refTrade.
     *
     * @param refTrade the entity to update.
     * @return the persisted entity.
     */
    RefTrade update(RefTrade refTrade);

    /**
     * Partially updates a refTrade.
     *
     * @param refTrade the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RefTrade> partialUpdate(RefTrade refTrade);

    /**
     * Get all the refTrades.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RefTrade> findAll(Pageable pageable);
    /**
     * Get all the RefTrade where EmployeeMaster is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<RefTrade> findAllWhereEmployeeMasterIsNull();

    /**
     * Get the "id" refTrade.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RefTrade> findOne(Long id);

    /**
     * Delete the "id" refTrade.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
