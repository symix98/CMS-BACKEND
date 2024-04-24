package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import net.ccc.apps.core.domain.enumeration.FormType;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.WorkflowStepMessageConfig} entity.
 */
public class WorkflowStepMessageConfigDTO implements Serializable {

    private Long id;

    @NotNull
    private FormType formType;

    @NotNull
    private String stepName;

    private String action;

    private String messageId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FormType getFormType() {
        return formType;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkflowStepMessageConfigDTO)) {
            return false;
        }

        WorkflowStepMessageConfigDTO workflowStepMessageConfigDTO = (WorkflowStepMessageConfigDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, workflowStepMessageConfigDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowStepMessageConfigDTO{" +
            "id=" + getId() +
            ", formType='" + getFormType() + "'" +
            ", stepName='" + getStepName() + "'" +
            ", action='" + getAction() + "'" +
            ", messageId='" + getMessageId() + "'" +
            "}";
    }
}
