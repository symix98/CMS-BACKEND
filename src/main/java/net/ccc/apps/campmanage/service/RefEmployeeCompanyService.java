package net.ccc.apps.campmanage.service;

import java.util.List;
import java.util.Optional;
import net.ccc.apps.campmanage.domain.RefEmployeeCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link RefEmployeeCompany}.
 */
public interface RefEmployeeCompanyService {
    /**
     * Save a refEmployeeCompany.
     *
     * @param refEmployeeCompany the entity to save.
     * @return the persisted entity.
     */
    RefEmployeeCompany save(RefEmployeeCompany refEmployeeCompany);

    /**
     * Updates a refEmployeeCompany.
     *
     * @param refEmployeeCompany the entity to update.
     * @return the persisted entity.
     */
    RefEmployeeCompany update(RefEmployeeCompany refEmployeeCompany);

    /**
     * Partially updates a refEmployeeCompany.
     *
     * @param refEmployeeCompany the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RefEmployeeCompany> partialUpdate(RefEmployeeCompany refEmployeeCompany);

    /**
     * Get all the refEmployeeCompanies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RefEmployeeCompany> findAll(Pageable pageable);
    /**
     * Get all the RefEmployeeCompany where EmployeeMaster is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<RefEmployeeCompany> findAllWhereEmployeeMasterIsNull();

    /**
     * Get the "id" refEmployeeCompany.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RefEmployeeCompany> findOne(Long id);

    /**
     * Delete the "id" refEmployeeCompany.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
