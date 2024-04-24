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
import tech.jhipster.service.filter.InstantFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link net.ccc.apps.core.domain.Inbox} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.InboxResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /inboxes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class InboxCriteria implements Serializable, Criteria {

    /**
     * Class for filtering FormType
     */
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

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter dateTime;

    private LongFilter formId;

    private FormTypeFilter formType;

    private StringFilter title;

    private StringFilter message;

    private BooleanFilter unread;

    private InstantFilter readTime;

    private StringFilter description;

    private StringFilter assignedToId;

    private StringFilter assignedById;

    public InboxCriteria() {
    }

    public InboxCriteria(InboxCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.dateTime = other.dateTime == null ? null : other.dateTime.copy();
        this.formId = other.formId == null ? null : other.formId.copy();
        this.formType = other.formType == null ? null : other.formType.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.message = other.message == null ? null : other.message.copy();
        this.unread = other.unread == null ? null : other.unread.copy();
        this.readTime = other.readTime == null ? null : other.readTime.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.assignedToId = other.assignedToId == null ? null : other.assignedToId.copy();
        this.assignedById = other.assignedById == null ? null : other.assignedById.copy();
    }

    @Override
    public InboxCriteria copy() {
        return new InboxCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getDateTime() {
        return dateTime;
    }

    public void setDateTime(InstantFilter dateTime) {
        this.dateTime = dateTime;
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

    public StringFilter getTitle() {
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getMessage() {
        return message;
    }

    public void setMessage(StringFilter message) {
        this.message = message;
    }

    public BooleanFilter getUnread() {
        return unread;
    }

    public void setUnread(BooleanFilter unread) {
        this.unread = unread;
    }

    public InstantFilter getReadTime() {
        return readTime;
    }

    public void setReadTime(InstantFilter readTime) {
        this.readTime = readTime;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(StringFilter assignedToId) {
        this.assignedToId = assignedToId;
    }

    public StringFilter getAssignedById() {
        return assignedById;
    }

    public void setAssignedById(StringFilter assignedById) {
        this.assignedById = assignedById;
    }

    // prettier-ignore
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final InboxCriteria that = (InboxCriteria) o;
        return
            Objects.equals(id, that.id) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(formId, that.formId) &&
                Objects.equals(formType, that.formType) &&
                Objects.equals(title, that.title) &&
                Objects.equals(message, that.message) &&
                Objects.equals(unread, that.unread) &&
                Objects.equals(readTime, that.readTime) &&
                Objects.equals(description, that.description) &&
                Objects.equals(assignedToId, that.assignedToId) &&
                Objects.equals(assignedById, that.assignedById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            dateTime,
            formId,
            formType,
            title,
            message,
            unread,
            readTime,
            description,
            assignedToId,
            assignedById
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InboxCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (dateTime != null ? "dateTime=" + dateTime + ", " : "") +
            (formId != null ? "formId=" + formId + ", " : "") +
            (formType != null ? "formType=" + formType + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (message != null ? "message=" + message + ", " : "") +
            (unread != null ? "unread=" + unread + ", " : "") +
            (readTime != null ? "readTime=" + readTime + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (assignedToId != null ? "assignedToId=" + assignedToId + ", " : "") +
            (assignedById != null ? "assignedById=" + assignedById + ", " : "") +
            "}";
    }

}
