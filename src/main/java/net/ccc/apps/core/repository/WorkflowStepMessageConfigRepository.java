package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.WorkflowStepMessageConfig;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkflowStepMessageConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkflowStepMessageConfigRepository
    extends JpaRepository<WorkflowStepMessageConfig, Long>, JpaSpecificationExecutor<WorkflowStepMessageConfig> {}
