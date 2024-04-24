package net.ccc.apps.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.ccc.apps.core.domain.enumeration.FormType;
import net.ccc.apps.core.domain.enumeration.SignoffOption;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A WorkflowTemplate.
 */
@Entity
@Table(name = "workflow_template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkflowTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    @Enumerated(EnumType.STRING)
    @Column(name = "form_type")
    private FormType formType;

    @Column(name = "step_name")
    private String stepName;

    @Column(name = "action_description")
    private String actionDescription;

    @Column(name = "roles")
    private String roles;

    @Column(name = "initial_status")
    private String initialStatus;

    @Column(name = "success_status")
    private String successStatus;

    @Column(name = "enabled")
    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_off_rule")
    private SignoffOption signOffRule;

    @Column(name = "multiple_action_users")
    private Boolean multipleActionUsers;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public WorkflowTemplate sequence(Integer sequence) {
        this.sequence = sequence;
        return this;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public FormType getFormType() {
        return formType;
    }

    public WorkflowTemplate formType(FormType formType) {
        this.formType = formType;
        return this;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public String getStepName() {
        return stepName;
    }

    public WorkflowTemplate stepName(String stepName) {
        this.stepName = stepName;
        return this;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public WorkflowTemplate actionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
        return this;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public String getRoles() {
        return roles;
    }

    public WorkflowTemplate roles(String roles) {
        this.roles = roles;
        return this;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getInitialStatus() {
        return initialStatus;
    }

    public WorkflowTemplate initialStatus(String initialStatus) {
        this.initialStatus = initialStatus;
        return this;
    }

    public void setInitialStatus(String initialStatus) {
        this.initialStatus = initialStatus;
    }

    public String getSuccessStatus() {
        return successStatus;
    }

    public WorkflowTemplate successStatus(String successStatus) {
        this.successStatus = successStatus;
        return this;
    }

    public void setSuccessStatus(String successStatus) {
        this.successStatus = successStatus;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public WorkflowTemplate enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public SignoffOption getSignOffRule() {
        return signOffRule;
    }

    public WorkflowTemplate signOffRule(SignoffOption signOffRule) {
        this.signOffRule = signOffRule;
        return this;
    }

    public void setSignOffRule(SignoffOption signOffRule) {
        this.signOffRule = signOffRule;
    }

    public Boolean isMultipleActionUsers() {
        return multipleActionUsers;
    }

    public WorkflowTemplate multipleActionUsers(Boolean multipleActionUsers) {
        this.multipleActionUsers = multipleActionUsers;
        return this;
    }

    public void setMultipleActionUsers(Boolean multipleActionUsers) {
        this.multipleActionUsers = multipleActionUsers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkflowTemplate)) {
            return false;
        }
        return id != null && id.equals(((WorkflowTemplate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowTemplate{" +
            "id=" + getId() +
            ", sequence=" + getSequence() +
            ", formType='" + getFormType() + "'" +
            ", stepName='" + getStepName() + "'" +
            ", actionDescription='" + getActionDescription() + "'" +
            ", roles='" + getRoles() + "'" +
            ", initialStatus='" + getInitialStatus() + "'" +
            ", successStatus='" + getSuccessStatus() + "'" +
            ", enabled='" + isEnabled() + "'" +
            ", signOffRule='" + getSignOffRule() + "'" +
            ", multipleActionUsers='" + isMultipleActionUsers() + "'" +
            "}";
    }
}
