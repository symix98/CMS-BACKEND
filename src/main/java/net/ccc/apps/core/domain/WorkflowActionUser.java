package net.ccc.apps.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A WorkflowActionUser.
 */
@Entity
@Table(name = "workflow_action_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WorkflowActionUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "action_time")
    private Instant actionTime;

    @Column(name = "approve")
    private Boolean approve;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JsonIgnoreProperties(value = "workflowActionUsers", allowSetters = true)
    private WorkflowStep workflowStep;

    @ManyToOne
    @JsonIgnoreProperties(value = "workflowActionUsers", allowSetters = true)
    private AppUser actionUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getActionTime() {
        return actionTime;
    }

    public WorkflowActionUser actionTime(Instant actionTime) {
        this.actionTime = actionTime;
        return this;
    }

    public void setActionTime(Instant actionTime) {
        this.actionTime = actionTime;
    }

    public Boolean isApprove() {
        return approve;
    }

    public WorkflowActionUser approve(Boolean approve) {
        this.approve = approve;
        return this;
    }

    public void setApprove(Boolean approve) {
        this.approve = approve;
    }

    public String getComment() {
        return comment;
    }

    public WorkflowActionUser comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public WorkflowStep getWorkflowStep() {
        return workflowStep;
    }

    public WorkflowActionUser workflowStep(WorkflowStep workflowStep) {
        this.workflowStep = workflowStep;
        return this;
    }

    public void setWorkflowStep(WorkflowStep workflowStep) {
        this.workflowStep = workflowStep;
    }

    public AppUser getActionUser() {
        return actionUser;
    }

    public WorkflowActionUser actionUser(AppUser appUser) {
        this.actionUser = appUser;
        return this;
    }

    public void setActionUser(AppUser appUser) {
        this.actionUser = appUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkflowActionUser)) {
            return false;
        }
        return id != null && id.equals(((WorkflowActionUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowActionUser{" +
            "id=" + getId() +
            ", actionTime='" + getActionTime() + "'" +
            ", approve='" + isApprove() + "'" +
            ", comment='" + getComment() + "'" +
            "}";
    }
}

