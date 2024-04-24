package net.ccc.apps.campmanage.service;

import java.util.List;
import java.util.Optional;
import net.ccc.apps.campmanage.domain.RefMealCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link RefMealCategory}.
 */
public interface RefMealCategoryService {
    /**
     * Save a refMealCategory.
     *
     * @param refMealCategory the entity to save.
     * @return the persisted entity.
     */
    RefMealCategory save(RefMealCategory refMealCategory);

    /**
     * Updates a refMealCategory.
     *
     * @param refMealCategory the entity to update.
     * @return the persisted entity.
     */
    RefMealCategory update(RefMealCategory refMealCategory);

    /**
     * Partially updates a refMealCategory.
     *
     * @param refMealCategory the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RefMealCategory> partialUpdate(RefMealCategory refMealCategory);

    /**
     * Get all the refMealCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RefMealCategory> findAll(Pageable pageable);
    /**
     * Get all the RefMealCategory where EmployeeMaster is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<RefMealCategory> findAllWhereEmployeeMasterIsNull();

    /**
     * Get the "id" refMealCategory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RefMealCategory> findOne(Long id);

    /**
     * Delete the "id" refMealCategory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
