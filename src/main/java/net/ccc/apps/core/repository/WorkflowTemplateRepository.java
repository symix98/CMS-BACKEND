package net.ccc.apps.core.repository;

import java.util.List;
import java.util.Optional;
import net.ccc.apps.core.domain.AppUser;
import net.ccc.apps.core.domain.WorkflowTemplate;
import net.ccc.apps.core.domain.enumeration.FormType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WorkflowTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkflowTemplateRepository extends JpaRepository<WorkflowTemplate, Long>, JpaSpecificationExecutor<WorkflowTemplate> {
    @Query("select wf from WorkflowTemplate wf where wf.sequence =:wfSequence and wf.formType =:formType")
    Optional<WorkflowTemplate> findWorkflowTemplateBySequenceandType(
        @Param("wfSequence") Long wfSequence,
        @Param("formType") String formType
    );

    @Query("select wf from WorkflowTemplate wf where wf.sequence =:sequence and wf.formType =:formType")
    List<WorkflowTemplate> findWorkFlowBySeqandType(@Param("sequence") Integer sequence ,@Param("formType") FormType formType);
}
