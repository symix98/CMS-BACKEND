package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.WorkflowActionUser} entity.
 */
public class WorkflowActionUserDTO implements Serializable {

    private Long id;

    private Instant actionTime;

    private Boolean approve;

    @Lob
    private String comment;

    private AppUserDTO actionUser;

    private Long workflowStepId;

    private String actionUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getActionTime() {
        return actionTime;
    }

    public void setActionTime(Instant actionTime) {
        this.actionTime = actionTime;
    }

    public Boolean isApprove() {
        return approve;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AppUserDTO getActionUser() {
        return actionUser;
    }

    public void setActionUser(AppUserDTO actionUser) {
        this.actionUser = actionUser;
    }

    public Long getWorkflowStepId() {
        return workflowStepId;
    }

    public void setWorkflowStepId(Long workflowStepId) {
        this.workflowStepId = workflowStepId;
    }

    public String getActionUserId() {
        return actionUserId;
    }

    public void setActionUserId(String tUserId) {
        this.actionUserId = tUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkflowActionUserDTO)) {
            return false;
        }

        return id != null && id.equals(((WorkflowActionUserDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowActionUserDTO{" +
            "id=" + getId() +
            ", actionTime='" + getActionTime() + "'" +
            ", approve='" + isApprove() + "'" +
            ", comment='" + getComment() + "'" +
            ", actionUser='" + getActionUser() + "'" +
            ", workflowStepId=" + getWorkflowStepId() +
            ", actionUserId=" + getActionUserId() +
            "}";
    }
}
