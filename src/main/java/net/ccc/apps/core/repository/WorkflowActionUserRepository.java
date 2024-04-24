package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.WorkflowActionUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkflowActionUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkflowActionUserRepository
    extends JpaRepository<WorkflowActionUser, Long>, JpaSpecificationExecutor<WorkflowActionUser> {}
