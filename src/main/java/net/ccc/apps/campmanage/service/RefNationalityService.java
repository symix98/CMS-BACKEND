package net.ccc.apps.campmanage.service;

import java.util.List;
import java.util.Optional;
import net.ccc.apps.campmanage.domain.RefNationality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link RefNationality}.
 */
public interface RefNationalityService {
    /**
     * Save a refNationality.
     *
     * @param refNationality the entity to save.
     * @return the persisted entity.
     */
    RefNationality save(RefNationality refNationality);

    /**
     * Updates a refNationality.
     *
     * @param refNationality the entity to update.
     * @return the persisted entity.
     */
    RefNationality update(RefNationality refNationality);

    /**
     * Partially updates a refNationality.
     *
     * @param refNationality the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RefNationality> partialUpdate(RefNationality refNationality);

    /**
     * Get all the refNationalities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RefNationality> findAll(Pageable pageable);
    /**
     * Get all the RefNationality where EmployeeMaster is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<RefNationality> findAllWhereEmployeeMasterIsNull();

    /**
     * Get the "id" refNationality.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RefNationality> findOne(Long id);

    /**
     * Delete the "id" refNationality.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
