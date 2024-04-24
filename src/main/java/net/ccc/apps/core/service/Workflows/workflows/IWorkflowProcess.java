package net.ccc.apps.core.service.Workflows.workflows;


import net.ccc.apps.core.service.dto.WorkflowProcessFormDTO;

public interface IWorkflowProcess<S extends WorkflowProcessFormDTO> {
      void onSuccess(S wfProcessDTO);
      void onFail(S wfProcessDTO);
      String getCompletedStatus();
}
