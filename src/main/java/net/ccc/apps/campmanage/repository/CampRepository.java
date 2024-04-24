package net.ccc.apps.campmanage.repository;

import net.ccc.apps.campmanage.domain.Camp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Camp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampRepository extends JpaRepository<Camp, Long>, JpaSpecificationExecutor<Camp> {}
