package net.ccc.apps.core.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import net.ccc.apps.core.domain.enumeration.FormType;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link net.ccc.apps.core.domain.WorkflowStepMessageConfig} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.WorkflowStepMessageConfigResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /workflow-step-message-configs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class WorkflowStepMessageConfigCriteria implements Serializable, Criteria {

    /**
     * Class for filtering FormType
     */
    public static class FormTypeFilter extends Filter<FormType> {

        public FormTypeFilter() {}

        public FormTypeFilter(FormTypeFilter filter) {
            super(filter);
        }

        @Override
        public FormTypeFilter copy() {
            return new FormTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FormTypeFilter formType;

    private StringFilter stepName;

    private StringFilter action;

    private StringFilter messageId;

    private Boolean distinct;

    public WorkflowStepMessageConfigCriteria() {}

    public WorkflowStepMessageConfigCriteria(WorkflowStepMessageConfigCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.formType = other.formType == null ? null : other.formType.copy();
        this.stepName = other.stepName == null ? null : other.stepName.copy();
        this.action = other.action == null ? null : other.action.copy();
        this.messageId = other.messageId == null ? null : other.messageId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public WorkflowStepMessageConfigCriteria copy() {
        return new WorkflowStepMessageConfigCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public FormTypeFilter getFormType() {
        return formType;
    }

    public FormTypeFilter formType() {
        if (formType == null) {
            formType = new FormTypeFilter();
        }
        return formType;
    }

    public void setFormType(FormTypeFilter formType) {
        this.formType = formType;
    }

    public StringFilter getStepName() {
        return stepName;
    }

    public StringFilter stepName() {
        if (stepName == null) {
            stepName = new StringFilter();
        }
        return stepName;
    }

    public void setStepName(StringFilter stepName) {
        this.stepName = stepName;
    }

    public StringFilter getAction() {
        return action;
    }

    public StringFilter action() {
        if (action == null) {
            action = new StringFilter();
        }
        return action;
    }

    public void setAction(StringFilter action) {
        this.action = action;
    }

    public StringFilter getMessageId() {
        return messageId;
    }

    public StringFilter messageId() {
        if (messageId == null) {
            messageId = new StringFilter();
        }
        return messageId;
    }

    public void setMessageId(StringFilter messageId) {
        this.messageId = messageId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final WorkflowStepMessageConfigCriteria that = (WorkflowStepMessageConfigCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(formType, that.formType) &&
            Objects.equals(stepName, that.stepName) &&
            Objects.equals(action, that.action) &&
            Objects.equals(messageId, that.messageId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, formType, stepName, action, messageId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowStepMessageConfigCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (formType != null ? "formType=" + formType + ", " : "") +
            (stepName != null ? "stepName=" + stepName + ", " : "") +
            (action != null ? "action=" + action + ", " : "") +
            (messageId != null ? "messageId=" + messageId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
