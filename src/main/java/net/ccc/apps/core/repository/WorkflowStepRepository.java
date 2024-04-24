package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.WorkflowStep;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkflowStep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkflowStepRepository extends JpaRepository<WorkflowStep, Long>, JpaSpecificationExecutor<WorkflowStep> {}
