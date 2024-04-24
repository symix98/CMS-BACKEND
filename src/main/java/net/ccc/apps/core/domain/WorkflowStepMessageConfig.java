package net.ccc.apps.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.ccc.apps.core.domain.enumeration.FormType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A WorkflowStepMessageConfig.
 */
@Entity
@Table(name = "workflow_step_message_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkflowStepMessageConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "form_type", nullable = false)
    private FormType formType;

    @NotNull
    @Column(name = "step_name", nullable = false)
    private String stepName;

    @Column(name = "action")
    private String action;

    @Column(name = "message_id")
    private String messageId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public WorkflowStepMessageConfig id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FormType getFormType() {
        return this.formType;
    }

    public WorkflowStepMessageConfig formType(FormType formType) {
        this.setFormType(formType);
        return this;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public String getStepName() {
        return this.stepName;
    }

    public WorkflowStepMessageConfig stepName(String stepName) {
        this.setStepName(stepName);
        return this;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getAction() {
        return this.action;
    }

    public WorkflowStepMessageConfig action(String action) {
        this.setAction(action);
        return this;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public WorkflowStepMessageConfig messageId(String messageId) {
        this.setMessageId(messageId);
        return this;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkflowStepMessageConfig)) {
            return false;
        }
        return id != null && id.equals(((WorkflowStepMessageConfig) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowStepMessageConfig{" +
            "id=" + getId() +
            ", formType='" + getFormType() + "'" +
            ", stepName='" + getStepName() + "'" +
            ", action='" + getAction() + "'" +
            ", messageId='" + getMessageId() + "'" +
            "}";
    }
}
