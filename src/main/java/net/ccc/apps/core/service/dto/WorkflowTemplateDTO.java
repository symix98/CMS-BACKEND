package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import net.ccc.apps.core.domain.enumeration.FormType;
import net.ccc.apps.core.domain.enumeration.SignoffOption;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.WorkflowTemplate} entity.
 */
public class WorkflowTemplateDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer sequence;

    @NotNull
    private FormType formType;

    @NotNull
    private String stepName;

    private String actionDescription;

    private String roles;

    @NotNull
    private String initialStatus;

    @NotNull
    private String successStatus;

    @NotNull
    private Boolean enabled;

    @NotNull
    private SignoffOption signOffRule;

    private Boolean multipleActionUsers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getInitialStatus() {
        return initialStatus;
    }

    public void setInitialStatus(String initialStatus) {
        this.initialStatus = initialStatus;
    }

    public String getSuccessStatus() {
        return successStatus;
    }

    public void setSuccessStatus(String successStatus) {
        this.successStatus = successStatus;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public SignoffOption getSignOffRule() {
        return signOffRule;
    }

    public void setSignOffRule(SignoffOption signOffRule) {
        this.signOffRule = signOffRule;
    }

    public Boolean getMultipleActionUsers() {
        return multipleActionUsers;
    }

    public void setMultipleActionUsers(Boolean multipleActionUsers) {
        this.multipleActionUsers = multipleActionUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkflowTemplateDTO)) {
            return false;
        }

        WorkflowTemplateDTO workflowTemplateDTO = (WorkflowTemplateDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, workflowTemplateDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowTemplateDTO{" +
            "id=" + getId() +
            ", sequence=" + getSequence() +
            ", formType='" + getFormType() + "'" +
            ", stepName='" + getStepName() + "'" +
            ", actionDescription='" + getActionDescription() + "'" +
            ", roles='" + getRoles() + "'" +
            ", initialStatus='" + getInitialStatus() + "'" +
            ", successStatus='" + getSuccessStatus() + "'" +
            ", enabled='" + getEnabled() + "'" +
            ", signOffRule='" + getSignOffRule() + "'" +
            ", multipleActionUsers='" + getMultipleActionUsers() + "'" +
            "}";
    }
}
