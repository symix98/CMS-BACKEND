package net.ccc.apps.core.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.ccc.apps.core.domain.enumeration.FormType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A WorkflowProcess.
 */
@Entity
@Table(name = "workflow_process")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkflowProcess implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "form_id", nullable = false)
    private Long formId;

    @Enumerated(EnumType.STRING)
    @Column(name = "form_type")
    private FormType formType;

    @NotNull
    @Column(name = "initiation_time", nullable = false)
    private Instant initiationTime;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "workflowProcess", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<WorkflowStep> workflowSteps = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "workflowProcesses", allowSetters = true)
    private AppUser initiatedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFormId() {
        return formId;
    }

    public WorkflowProcess formId(Long formId) {
        this.formId = formId;
        return this;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public FormType getFormType() {
        return formType;
    }

    public WorkflowProcess formType(FormType formType) {
        this.formType = formType;
        return this;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public Instant getInitiationTime() {
        return initiationTime;
    }

    public WorkflowProcess initiationTime(Instant initiationTime) {
        this.initiationTime = initiationTime;
        return this;
    }

    public void setInitiationTime(Instant initiationTime) {
        this.initiationTime = initiationTime;
    }

    public String getDescription() {
        return description;
    }

    public WorkflowProcess description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public WorkflowProcess status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<WorkflowStep> getWorkflowSteps() {
        return workflowSteps;
    }

    public WorkflowProcess workflowSteps(Set<WorkflowStep> workflowSteps) {
        this.workflowSteps = workflowSteps;
        return this;
    }

    public WorkflowProcess addWorkflowStep(WorkflowStep workflowStep) {
        this.workflowSteps.add(workflowStep);
        workflowStep.setWorkflowProcess(this);
        return this;
    }

    public WorkflowProcess removeWorkflowStep(WorkflowStep workflowStep) {
        this.workflowSteps.remove(workflowStep);
        workflowStep.setWorkflowProcess(null);
        return this;
    }

    public void setWorkflowSteps(Set<WorkflowStep> workflowSteps) {
        this.workflowSteps = workflowSteps;
    }

    public AppUser getInitiatedBy() {
        return initiatedBy;
    }

    public WorkflowProcess initiatedBy(AppUser appUser) {
        this.initiatedBy = appUser;
        return this;
    }

    public void setInitiatedBy(AppUser appUser) {
        this.initiatedBy = appUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkflowProcess)) {
            return false;
        }
        return id != null && id.equals(((WorkflowProcess) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowProcess{" +
            "id=" + getId() +
            ", formId='" + getFormId() + "'" +
            ", formType='" + getFormType() + "'" +
            ", initiationTime='" + getInitiationTime() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
