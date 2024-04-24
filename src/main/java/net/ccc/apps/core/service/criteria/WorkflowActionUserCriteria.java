package net.ccc.apps.core.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.InstantFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link net.ccc.apps.core.domain.WorkflowActionUser} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.WorkflowActionUserResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /workflow-action-users?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class WorkflowActionUserCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter actionTime;

    private BooleanFilter approve;

    private LongFilter workflowStepId;

    private LongFilter actionUserId;

    public WorkflowActionUserCriteria() {
    }

    public WorkflowActionUserCriteria(WorkflowActionUserCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.actionTime = other.actionTime == null ? null : other.actionTime.copy();
        this.approve = other.approve == null ? null : other.approve.copy();
        this.workflowStepId = other.workflowStepId == null ? null : other.workflowStepId.copy();
        this.actionUserId = other.actionUserId == null ? null : other.actionUserId.copy();
    }

    @Override
    public WorkflowActionUserCriteria copy() {
        return new WorkflowActionUserCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getActionTime() {
        return actionTime;
    }

    public void setActionTime(InstantFilter actionTime) {
        this.actionTime = actionTime;
    }

    public BooleanFilter getApprove() {
        return approve;
    }

    public void setApprove(BooleanFilter approve) {
        this.approve = approve;
    }

    public LongFilter getWorkflowStepId() {
        return workflowStepId;
    }

    public void setWorkflowStepId(LongFilter workflowStepId) {
        this.workflowStepId = workflowStepId;
    }

    public LongFilter getActionUserId() {
        return actionUserId;
    }

    public void setActionUserId(LongFilter actionUserId) {
        this.actionUserId = actionUserId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final WorkflowActionUserCriteria that = (WorkflowActionUserCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(actionTime, that.actionTime) &&
                Objects.equals(approve, that.approve) &&
                Objects.equals(workflowStepId, that.workflowStepId) &&
                Objects.equals(actionUserId, that.actionUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            actionTime,
            approve,
            workflowStepId,
            actionUserId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowActionUserCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (actionTime != null ? "actionTime=" + actionTime + ", " : "") +
            (approve != null ? "approve=" + approve + ", " : "") +
            (actionUserId != null ? "actionUserId=" + actionUserId + ", " : "") +
            (workflowStepId != null ? "workflowStepId=" + workflowStepId + ", " : "") +
            "}";
    }

}
