package net.ccc.apps.core.service.criteria;

import java.io.Serializable;
import java.util.Objects;

import net.ccc.apps.core.domain.enumeration.FormStatus;
import net.ccc.apps.core.domain.enumeration.FormType;
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
 * Criteria class for the {@link net.ccc.apps.core.domain.WorkflowProcess} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.WorkflowProcessResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /workflow-processes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class WorkflowProcessCriteria implements Serializable, Criteria {
    public static class FormTypeFilter extends Filter<FormType> {

        public FormTypeFilter() {
        }

        public FormTypeFilter(FormTypeFilter filter) {
            super(filter);
        }

        @Override
        public FormTypeFilter copy() {
            return new FormTypeFilter(this);
        }
    }

    /**
     * Class for filtering FormStatus
     */
    public static class FormStatusFilter extends Filter<FormStatus> {

        public FormStatusFilter() {
        }

        public FormStatusFilter(FormStatusFilter filter) {
            super(filter);
        }

        @Override
        public FormStatusFilter copy() {
            return new FormStatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter formId;

    private FormTypeFilter formType;

    private InstantFilter initiationTime;

    private StringFilter description;

    private StringFilter status;

    private LongFilter workflowStepId;

    private StringFilter initiatedById;

    public WorkflowProcessCriteria() {
    }

    public WorkflowProcessCriteria(WorkflowProcessCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.formId = other.formId == null ? null : other.formId.copy();
        this.formType = other.formType == null ? null : other.formType.copy();
        this.initiationTime = other.initiationTime == null ? null : other.initiationTime.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.workflowStepId = other.workflowStepId == null ? null : other.workflowStepId.copy();
        this.initiatedById = other.initiatedById == null ? null : other.initiatedById.copy();
    }

    @Override
    public WorkflowProcessCriteria copy() {
        return new WorkflowProcessCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getFormId() {
        return formId;
    }

    public void setFormId(LongFilter formId) {
        this.formId = formId;
    }

    public FormTypeFilter getFormType() {
        return formType;
    }

    public void setFormType(FormTypeFilter formType) {
        this.formType = formType;
    }

    public InstantFilter getInitiationTime() {
        return initiationTime;
    }

    public void setInitiationTime(InstantFilter initiationTime) {
        this.initiationTime = initiationTime;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getStatus() {
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public LongFilter getWorkflowStepId() {
        return workflowStepId;
    }

    public void setWorkflowStepId(LongFilter workflowStepId) {
        this.workflowStepId = workflowStepId;
    }

    public StringFilter getInitiatedById() {
        return initiatedById;
    }

    public void setInitiatedById(StringFilter initiatedById) {
        this.initiatedById = initiatedById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final WorkflowProcessCriteria that = (WorkflowProcessCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(formId, that.formId) && Objects.equals(formType, that.formType) && Objects.equals(initiationTime, that.initiationTime) && Objects
            .equals(description, that.description) && Objects.equals(status, that.status) && Objects.equals(workflowStepId, that.workflowStepId) && Objects.equals(initiatedById, that.initiatedById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, formId, formType, initiationTime, description, status, workflowStepId, initiatedById);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowProcessCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (formId != null ? "formId=" + formId + ", " : "") +
            (formType != null ? "formType=" + formType + ", " : "") +
            (initiationTime != null ? "initiationTime=" + initiationTime + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (workflowStepId != null ? "workflowStepId=" + workflowStepId + ", " : "") +
            (initiatedById != null ? "initiatedById=" + initiatedById + ", " : "") +
            "}";
    }

}
