package net.ccc.apps.campmanage.repository;

import net.ccc.apps.campmanage.domain.RefInactiveReason;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RefInactiveReason entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefInactiveReasonRepository extends JpaRepository<RefInactiveReason, Long>, JpaSpecificationExecutor<RefInactiveReason> {}
