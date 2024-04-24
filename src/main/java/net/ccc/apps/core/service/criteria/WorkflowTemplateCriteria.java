package net.ccc.apps.core.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import net.ccc.apps.core.domain.enumeration.FormType;
import net.ccc.apps.core.domain.enumeration.SignoffOption;
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
 * Criteria class for the {@link net.ccc.apps.core.domain.WorkflowTemplate} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.WorkflowTemplateResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /workflow-templates?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class WorkflowTemplateCriteria implements Serializable, Criteria {

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

    /**
     * Class for filtering SignoffOption
     */
    public static class SignoffOptionFilter extends Filter<SignoffOption> {

        public SignoffOptionFilter() {}

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

    private IntegerFilter sequence;

    private FormTypeFilter formType;

    private StringFilter stepName;

    private StringFilter actionDescription;

    private StringFilter roles;

    private StringFilter initialStatus;

    private StringFilter successStatus;

    private BooleanFilter enabled;

    private SignoffOptionFilter signOffRule;

    private BooleanFilter multipleActionUsers;

    private Boolean distinct;

    public WorkflowTemplateCriteria() {}

    public WorkflowTemplateCriteria(WorkflowTemplateCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.sequence = other.sequence == null ? null : other.sequence.copy();
        this.formType = other.formType == null ? null : other.formType.copy();
        this.stepName = other.stepName == null ? null : other.stepName.copy();
        this.actionDescription = other.actionDescription == null ? null : other.actionDescription.copy();
        this.roles = other.roles == null ? null : other.roles.copy();
        this.initialStatus = other.initialStatus == null ? null : other.initialStatus.copy();
        this.successStatus = other.successStatus == null ? null : other.successStatus.copy();
        this.enabled = other.enabled == null ? null : other.enabled.copy();
        this.signOffRule = other.signOffRule == null ? null : other.signOffRule.copy();
        this.multipleActionUsers = other.multipleActionUsers == null ? null : other.multipleActionUsers.copy();
        this.distinct = other.distinct;
    }

    @Override
    public WorkflowTemplateCriteria copy() {
        return new WorkflowTemplateCriteria(this);
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

    public IntegerFilter getSequence() {
        return sequence;
    }

    public IntegerFilter sequence() {
        if (sequence == null) {
            sequence = new IntegerFilter();
        }
        return sequence;
    }

    public void setSequence(IntegerFilter sequence) {
        this.sequence = sequence;
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

    public StringFilter getActionDescription() {
        return actionDescription;
    }

    public StringFilter actionDescription() {
        if (actionDescription == null) {
            actionDescription = new StringFilter();
        }
        return actionDescription;
    }

    public void setActionDescription(StringFilter actionDescription) {
        this.actionDescription = actionDescription;
    }

    public StringFilter getRoles() {
        return roles;
    }

    public StringFilter roles() {
        if (roles == null) {
            roles = new StringFilter();
        }
        return roles;
    }

    public void setRoles(StringFilter roles) {
        this.roles = roles;
    }

    public StringFilter getInitialStatus() {
        return initialStatus;
    }

    public StringFilter initialStatus() {
        if (initialStatus == null) {
            initialStatus = new StringFilter();
        }
        return initialStatus;
    }

    public void setInitialStatus(StringFilter initialStatus) {
        this.initialStatus = initialStatus;
    }

    public StringFilter getSuccessStatus() {
        return successStatus;
    }

    public StringFilter successStatus() {
        if (successStatus == null) {
            successStatus = new StringFilter();
        }
        return successStatus;
    }

    public void setSuccessStatus(StringFilter successStatus) {
        this.successStatus = successStatus;
    }

    public BooleanFilter getEnabled() {
        return enabled;
    }

    public BooleanFilter enabled() {
        if (enabled == null) {
            enabled = new BooleanFilter();
        }
        return enabled;
    }

    public void setEnabled(BooleanFilter enabled) {
        this.enabled = enabled;
    }

    public SignoffOptionFilter getSignOffRule() {
        return signOffRule;
    }

    public SignoffOptionFilter signOffRule() {
        if (signOffRule == null) {
            signOffRule = new SignoffOptionFilter();
        }
        return signOffRule;
    }

    public void setSignOffRule(SignoffOptionFilter signOffRule) {
        this.signOffRule = signOffRule;
    }

    public BooleanFilter getMultipleActionUsers() {
        return multipleActionUsers;
    }

    public BooleanFilter multipleActionUsers() {
        if (multipleActionUsers == null) {
            multipleActionUsers = new BooleanFilter();
        }
        return multipleActionUsers;
    }

    public void setMultipleActionUsers(BooleanFilter multipleActionUsers) {
        this.multipleActionUsers = multipleActionUsers;
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
        final WorkflowTemplateCriteria that = (WorkflowTemplateCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(sequence, that.sequence) &&
            Objects.equals(formType, that.formType) &&
            Objects.equals(stepName, that.stepName) &&
            Objects.equals(actionDescription, that.actionDescription) &&
            Objects.equals(roles, that.roles) &&
            Objects.equals(initialStatus, that.initialStatus) &&
            Objects.equals(successStatus, that.successStatus) &&
            Objects.equals(enabled, that.enabled) &&
            Objects.equals(signOffRule, that.signOffRule) &&
            Objects.equals(multipleActionUsers, that.multipleActionUsers) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            sequence,
            formType,
            stepName,
            actionDescription,
            roles,
            initialStatus,
            successStatus,
            enabled,
            signOffRule,
            multipleActionUsers,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkflowTemplateCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (sequence != null ? "sequence=" + sequence + ", " : "") +
            (formType != null ? "formType=" + formType + ", " : "") +
            (stepName != null ? "stepName=" + stepName + ", " : "") +
            (actionDescription != null ? "actionDescription=" + actionDescription + ", " : "") +
            (roles != null ? "roles=" + roles + ", " : "") +
            (initialStatus != null ? "initialStatus=" + initialStatus + ", " : "") +
            (successStatus != null ? "successStatus=" + successStatus + ", " : "") +
            (enabled != null ? "enabled=" + enabled + ", " : "") +
            (signOffRule != null ? "signOffRule=" + signOffRule + ", " : "") +
            (multipleActionUsers != null ? "multipleActionUsers=" + multipleActionUsers + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
