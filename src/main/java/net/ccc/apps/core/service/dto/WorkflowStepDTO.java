package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;
import net.ccc.apps.core.domain.enumeration.SignoffOption;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.WorkflowStep} entity.
 */
public class WorkflowStepDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant triggerTime;

    @NotNull
    private String actionLabel;

    @NotNull
    private String actionStatus;

    private String description;

    private SignoffOption signoffRule;

    private LocalDate dueDate;

    private Boolean complete;

    private Instant completeTime;

    private Long workflowTemplateStepId;

    private String stepInitiator;

    private Long workflowProcessId;

    private List<WorkflowActionUserDTO> workflowActionUserDTOList;

    public Boolean getComplete() {
        return complete;
    }

    public List<WorkflowActionUserDTO> getWorkflowActionUserDTOList() {
        return workflowActionUserDTOList;
    }

    public void setWorkflowActionUserDTOList(List<WorkflowActionUserDTO> workflowActionUserDTOList) {
        this.workflowActionUserDTOList = workflowActionUserDTOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Instant triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SignoffOption getSignoffRule() {
        return signoffRule;
    }

    public void setSignoffRule(SignoffOption signoffRule) {
        this.signoffRule = signoffRule;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean isComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Instant getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Instant completeTime) {
        this.completeTime = completeTime;
    }

    public Long getWorkflowTemplateStepId() {
        return workflowTemplateStepId;
    }

    public void setWorkflowTemplateStepId(Long workflowTemplateStepId) {
        this.workflowTemplateStepId = workflowTemplateStepId;
    }

    public String getStepInitiator() {
        return stepInitiator;
    }

    public void setStepInitiator(String stepInitiator) {
        this.stepInitiator = stepInitiator;
    }

    public Long getWorkflowProcessId() {
        return workflowProcessId;
    }

    public void setWorkflowProcessId(Long workflowProcessId) {
        this.workflowProcessId = workflowProcessId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkflowStepDTO)) {
            return false;
        }

        return id != null && id.equals(((WorkflowStepDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowStepDTO{" +
            "id=" + getId() +
            ", triggerTime='" + getTriggerTime() + "'" +
            ", actionLabel='" + getActionLabel() + "'" +
            ", actionStatus='" + getActionStatus() + "'" +
            ", description='" + getDescription() + "'" +
            ", signoffRule='" + getSignoffRule() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", complete='" + isComplete() + "'" +
            ", completeTime='" + getCompleteTime() + "'" +
            ", workflowTemplateStepId=" + getWorkflowTemplateStepId() +
            ", stepInitiator='" + getStepInitiator() + "'" +
            ", workflowProcessId=" + getWorkflowProcessId() +
            "}";
    }
}
