package net.ccc.apps.core.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import net.ccc.apps.core.domain.enumeration.SignoffOption;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.InstantFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link net.ccc.apps.core.domain.WorkflowStep} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.WorkflowStepResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /workflow-steps?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class WorkflowStepCriteria implements Serializable, Criteria {
    /**
     * Class for filtering SignoffOption
     */
    public static class SignoffOptionFilter extends Filter<SignoffOption> {

        public SignoffOptionFilter() {
        }

        public SignoffOptionFilter(SignoffOptionFilter filter) {
            super(filter);
        }

        @Override
        public SignoffOptionFilter copy() {
            return new SignoffOptionFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter triggerTime;

    private StringFilter actionLabel;

    private StringFilter actionStatus;

    private StringFilter description;

    private SignoffOptionFilter signoffRule;

    private LocalDateFilter dueDate;

    private BooleanFilter complete;

    private InstantFilter completeTime;

    private LongFilter workflowTemplateStepId;

    private StringFilter stepInitiator;

    private LongFilter workflowActionUserId;

    private LongFilter workflowProcessId;

    public WorkflowStepCriteria() {
    }

    public WorkflowStepCriteria(WorkflowStepCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.triggerTime = other.triggerTime == null ? null : other.triggerTime.copy();
        this.actionLabel = other.actionLabel == null ? null : other.actionLabel.copy();
        this.actionStatus = other.actionStatus == null ? null : other.actionStatus.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.signoffRule = other.signoffRule == null ? null : other.signoffRule.copy();
        this.dueDate = other.dueDate == null ? null : other.dueDate.copy();
        this.complete = other.complete == null ? null : other.complete.copy();
        this.completeTime = other.completeTime == null ? null : other.completeTime.copy();
        this.workflowTemplateStepId = other.workflowTemplateStepId == null ? null : other.workflowTemplateStepId.copy();
        this.stepInitiator = other.stepInitiator == null ? null : other.stepInitiator.copy();
        this.workflowActionUserId = other.workflowActionUserId == null ? null : other.workflowActionUserId.copy();
        this.workflowProcessId = other.workflowProcessId == null ? null : other.workflowProcessId.copy();
    }

    @Override
    public WorkflowStepCriteria copy() {
        return new WorkflowStepCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(InstantFilter triggerTime) {
        this.triggerTime = triggerTime;
    }

    public StringFilter getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(StringFilter actionLabel) {
        this.actionLabel = actionLabel;
    }

    public StringFilter getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(StringFilter actionStatus) {
        this.actionStatus = actionStatus;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public SignoffOptionFilter getSignoffRule() {
        return signoffRule;
    }

    public void setSignoffRule(SignoffOptionFilter signoffRule) {
        this.signoffRule = signoffRule;
    }

    public LocalDateFilter getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateFilter dueDate) {
        this.dueDate = dueDate;
    }

    public BooleanFilter getComplete() {
        return complete;
    }

    public void setComplete(BooleanFilter complete) {
        this.complete = complete;
    }

    public InstantFilter getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(InstantFilter completeTime) {
        this.completeTime = completeTime;
    }

    public LongFilter getWorkflowTemplateStepId() {
        return workflowTemplateStepId;
    }

    public void setWorkflowTemplateStepId(LongFilter workflowTemplateStepId) {
        this.workflowTemplateStepId = workflowTemplateStepId;
    }

    public StringFilter getStepInitiator() {
        return stepInitiator;
    }

    public void setStepInitiator(StringFilter stepInitiator) {
        this.stepInitiator = stepInitiator;
    }

    public LongFilter getWorkflowActionUserId() {
        return workflowActionUserId;
    }

    public void setWorkflowActionUserId(LongFilter workflowActionUserId) {
        this.workflowActionUserId = workflowActionUserId;
    }

    public LongFilter getWorkflowProcessId() {
        return workflowProcessId;
    }

    public void setWorkflowProcessId(LongFilter workflowProcessId) {
        this.workflowProcessId = workflowProcessId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final WorkflowStepCriteria that = (WorkflowStepCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(triggerTime, that.triggerTime) &&
                Objects.equals(actionLabel, that.actionLabel) &&
                Objects.equals(actionStatus, that.actionStatus) &&
                Objects.equals(description, that.description) &&
                Objects.equals(signoffRule, that.signoffRule) &&
                Objects.equals(dueDate, that.dueDate) &&
                Objects.equals(complete, that.complete) &&
                Objects.equals(completeTime, that.completeTime) &&
                Objects.equals(workflowTemplateStepId, that.workflowTemplateStepId) &&
                Objects.equals(stepInitiator, that.stepInitiator) &&
                Objects.equals(workflowActionUserId, that.workflowActionUserId) &&
                Objects.equals(workflowProcessId, that.workflowProcessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            triggerTime,
            actionLabel,
            actionStatus,
            description,
            signoffRule,
            dueDate,
            complete,
            completeTime,
            workflowTemplateStepId,
            stepInitiator,
            workflowActionUserId,
            workflowProcessId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowStepCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (triggerTime != null ? "triggerTime=" + triggerTime + ", " : "") +
            (actionLabel != null ? "actionLabel=" + actionLabel + ", " : "") +
            (actionStatus != null ? "actionStatus=" + actionStatus + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (signoffRule != null ? "signoffRule=" + signoffRule + ", " : "") +
            (dueDate != null ? "dueDate=" + dueDate + ", " : "") +
            (complete != null ? "complete=" + complete + ", " : "") +
            (completeTime != null ? "completeTime=" + completeTime + ", " : "") +
            (workflowTemplateStepId != null ? "workflowTemplateStepId=" + workflowTemplateStepId + ", " : "") +
            (stepInitiator != null ? "stepInitiator=" + stepInitiator + ", " : "") +
            (workflowActionUserId != null ? "workflowActionUserId=" + workflowActionUserId + ", " : "") +
            (workflowProcessId != null ? "workflowProcessId=" + workflowProcessId + ", " : "") +
            "}";
    }

}
