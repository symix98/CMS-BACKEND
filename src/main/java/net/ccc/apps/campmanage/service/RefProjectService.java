package net.ccc.apps.campmanage.service;

import java.util.List;
import java.util.Optional;
import net.ccc.apps.campmanage.domain.RefProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link RefProject}.
 */
public interface RefProjectService {
    /**
     * Save a refProject.
     *
     * @param refProject the entity to save.
     * @return the persisted entity.
     */
    RefProject save(RefProject refProject);

    /**
     * Updates a refProject.
     *
     * @param refProject the entity to update.
     * @return the persisted entity.
     */
    RefProject update(RefProject refProject);

    /**
     * Partially updates a refProject.
     *
     * @param refProject the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RefProject> partialUpdate(RefProject refProject);

    /**
     * Get all the refProjects.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RefProject> findAll(Pageable pageable);
    /**
     * Get all the RefProject where EmployeeMaster is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<RefProject> findAllWhereEmployeeMasterIsNull();

    /**
     * Get the "id" refProject.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RefProject> findOne(Long id);

    /**
     * Delete the "id" refProject.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
