package net.ccc.apps.core.service.dto;


import net.ccc.apps.core.domain.enumeration.WorkflowAction;

import java.util.Set;

public class WorkflowProcessFormDTO {

    private WorkflowStepDTO workflowStep;

    private WorkflowStepDTO nextStep;

    private String comment;

    protected Set<AppUserDTO> targetUsers;

    protected WorkflowAction workflowAction;

    protected Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkflowAction getWorkflowAction() {
        return workflowAction;
    }

    public void setWorkflowAction(WorkflowAction workflowAction) {
        this.workflowAction = workflowAction;
    }

    public WorkflowStepDTO getWorkflowStep() {
        return workflowStep;
    }

    public void setWorkflowStep(WorkflowStepDTO workflowStep) {
        this.workflowStep = workflowStep;
    }

    public WorkflowStepDTO getNextStep() {
        return nextStep;
    }

    public void setNextStep(WorkflowStepDTO nextStep) {
        this.nextStep = nextStep;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<AppUserDTO> getTargetUsers() {
        return targetUsers;
    }

    public void setTargetUsers(Set<AppUserDTO> targetUsers) {
        this.targetUsers = targetUsers;
    }

}
