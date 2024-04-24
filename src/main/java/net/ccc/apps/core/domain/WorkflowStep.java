package net.ccc.apps.core.domain;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.ccc.apps.core.domain.enumeration.SignoffOption;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A WorkflowStep.
 */
@Entity
@Table(name = "workflow_step")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkflowStep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "trigger_time", nullable = false)
    private Instant triggerTime;

    @NotNull
    @Column(name = "action_label", nullable = false)
    private String actionLabel;

    @NotNull
    @Column(name = "action_status", nullable = false)
    private String actionStatus;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "signoff_rule")
    private SignoffOption signoffRule;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "complete")
    private Boolean complete;

    @Column(name = "complete_time")
    private Instant completeTime;

    @Column(name = "workflow_template_step_id")
    private Long workflowTemplateStepId;

    @Column(name = "step_initiator")
    private String stepInitiator;

    @OneToMany(mappedBy = "workflowStep")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<WorkflowActionUser> workflowActionUsers = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    private WorkflowProcess workflowProcess;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTriggerTime() {
        return triggerTime;
    }

    public WorkflowStep triggerTime(Instant triggerTime) {
        this.triggerTime = triggerTime;
        return this;
    }

    public void setTriggerTime(Instant triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    public WorkflowStep actionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
        return this;
    }

    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public WorkflowStep actionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
        return this;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getDescription() {
        return description;
    }

    public WorkflowStep description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SignoffOption getSignoffRule() {
        return signoffRule;
    }

    public WorkflowStep signoffRule(SignoffOption signoffRule) {
        this.signoffRule = signoffRule;
        return this;
    }

    public void setSignoffRule(SignoffOption signoffRule) {
        this.signoffRule = signoffRule;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public WorkflowStep dueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean isComplete() {
        return complete;
    }

    public WorkflowStep complete(Boolean complete) {
        this.complete = complete;
        return this;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Instant getCompleteTime() {
        return completeTime;
    }

    public WorkflowStep completeTime(Instant completeTime) {
        this.completeTime = completeTime;
        return this;
    }

    public void setCompleteTime(Instant completeTime) {
        this.completeTime = completeTime;
    }

    public Long getWorkflowTemplateStepId() {
        return workflowTemplateStepId;
    }

    public WorkflowStep workflowTemplateStepId(Long workflowTemplateStepId) {
        this.workflowTemplateStepId = workflowTemplateStepId;
        return this;
    }

    public void setWorkflowTemplateStepId(Long workflowTemplateStepId) {
        this.workflowTemplateStepId = workflowTemplateStepId;
    }

    public String getStepInitiator() {
        return stepInitiator;
    }

    public WorkflowStep stepInitiator(String stepInitiator) {
        this.stepInitiator = stepInitiator;
        return this;
    }

    public void setStepInitiator(String stepInitiator) {
        this.stepInitiator = stepInitiator;
    }

    public Set<WorkflowActionUser> getWorkflowActionUsers() {
        return workflowActionUsers;
    }

    public WorkflowStep workflowActionUsers(Set<WorkflowActionUser> workflowActionUsers) {
        this.workflowActionUsers = workflowActionUsers;
        return this;
    }

    public WorkflowStep addWorkflowActionUser(WorkflowActionUser workflowActionUser) {
        this.workflowActionUsers.add(workflowActionUser);
        workflowActionUser.setWorkflowStep(this);
        return this;
    }

    public WorkflowStep removeWorkflowActionUser(WorkflowActionUser workflowActionUser) {
        this.workflowActionUsers.remove(workflowActionUser);
        workflowActionUser.setWorkflowStep(null);
        return this;
    }

    public void setWorkflowActionUsers(Set<WorkflowActionUser> workflowActionUsers) {
        this.workflowActionUsers = workflowActionUsers;
    }

    public WorkflowProcess getWorkflowProcess() {
        return workflowProcess;
    }

    public WorkflowStep workflowProcess(WorkflowProcess workflowProcess) {
        this.workflowProcess = workflowProcess;
        return this;
    }

    public void setWorkflowProcess(WorkflowProcess workflowProcess) {
        this.workflowProcess = workflowProcess;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkflowStep)) {
            return false;
        }
        return id != null && id.equals(((WorkflowStep) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowStep{" +
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
            "}";
    }
}
