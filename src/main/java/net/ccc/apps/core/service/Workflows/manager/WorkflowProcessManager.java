package net.ccc.apps.core.service.Workflows.manager;

import net.ccc.apps.core.domain.enumeration.FormType;
import net.ccc.apps.core.service.Workflows.workflows.IWorkflowProcess;
//import net.ccc.apps.core.service.Workflows.workflows.MaterialRequisitionWorkflowProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WorkflowProcessManager {

//    @Autowired
//    private MaterialRequisitionWorkflowProcess materialRequisitionWorkflowProcess;
//
//       public IWorkflowProcess getWorkflowProccessInstance(FormType formType){
//           if(formType == null){
//               return null;
//           }
//           else if (formType == FormType.MaterialRequisition){
//               return materialRequisitionWorkflowProcess;
//           }
//           return null;
//       } ;
}
