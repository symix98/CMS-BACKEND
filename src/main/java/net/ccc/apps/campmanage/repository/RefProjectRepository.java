package net.ccc.apps.campmanage.repository;

import net.ccc.apps.campmanage.domain.RefProject;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RefProject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefProjectRepository extends JpaRepository<RefProject, Long>, JpaSpecificationExecutor<RefProject> {}
