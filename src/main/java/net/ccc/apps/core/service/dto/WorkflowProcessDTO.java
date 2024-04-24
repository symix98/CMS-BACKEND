package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;
import net.ccc.apps.core.domain.enumeration.FormType;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.WorkflowProcess} entity.
 */
public class WorkflowProcessDTO implements Serializable {

    private Long id;

    @NotNull
    private Long formId;

    private FormType formType;

    @NotNull
    private Instant initiationTime;

    private String description;

    private String status;

    private String initiatedByUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public FormType getFormType() {
        return formType;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public Instant getInitiationTime() {
        return initiationTime;
    }

    public void setInitiationTime(Instant initiationTime) {
        this.initiationTime = initiationTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInitiatedByUserId() {
        return initiatedByUserId;
    }

    public void setInitiatedByUserId(String appUserId) {
        this.initiatedByUserId = appUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkflowProcessDTO)) {
            return false;
        }

        return id != null && id.equals(((WorkflowProcessDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowProcessDTO{" +
            "id=" + getId() +
            ", formId='" + getFormId() + "'" +
            ", formType='" + getFormType() + "'" +
            ", initiationTime='" + getInitiationTime() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", initiatedById=" + getInitiatedByUserId() +
            "}";
    }
}
