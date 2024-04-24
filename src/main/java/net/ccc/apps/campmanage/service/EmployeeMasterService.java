package net.ccc.apps.campmanage.service;

import java.util.Optional;
import net.ccc.apps.campmanage.domain.EmployeeMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link EmployeeMaster}.
 */
public interface EmployeeMasterService {
    /**
     * Save a employeeMaster.
     *
     * @param employeeMaster the entity to save.
     * @return the persisted entity.
     */
    EmployeeMaster save(EmployeeMaster employeeMaster);

    /**
     * Updates a employeeMaster.
     *
     * @param employeeMaster the entity to update.
     * @return the persisted entity.
     */
    EmployeeMaster update(EmployeeMaster employeeMaster);

    /**
     * Partially updates a employeeMaster.
     *
     * @param employeeMaster the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EmployeeMaster> partialUpdate(EmployeeMaster employeeMaster);

    /**
     * Get all the employeeMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EmployeeMaster> findAll(Pageable pageable);

    /**
     * Get the "id" employeeMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmployeeMaster> findOne(Long id);

    /**
     * Delete the "id" employeeMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
