package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.WorkflowProcess;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkflowProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkflowProcessRepository extends JpaRepository<WorkflowProcess, Long>, JpaSpecificationExecutor<WorkflowProcess> {
    WorkflowProcess findByFormId(Long formId);
}
