package net.ccc.apps.core.service.criteria;

import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link net.ccc.apps.core.domain.EmailMessage} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.EmailMessageResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /email-messages?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EmailMessageCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter from;

    private StringFilter subject;

    private StringFilter content;

    private LongFilter referenceId;

    private BooleanFilter isSent;

    private InstantFilter sentTime;

    private StringFilter comment;

    private LongFilter recipientId;

    public EmailMessageCriteria() {}

    public EmailMessageCriteria(EmailMessageCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.from = other.from == null ? null : other.from.copy();
        this.subject = other.subject == null ? null : other.subject.copy();
        this.content = other.content == null ? null : other.content.copy();
        this.referenceId = other.referenceId == null ? null : other.referenceId.copy();
        this.isSent = other.isSent == null ? null : other.isSent.copy();
        this.sentTime = other.sentTime == null ? null : other.sentTime.copy();
        this.comment = other.comment == null ? null : other.comment.copy();
        this.recipientId = other.recipientId == null ? null : other.recipientId.copy();
    }

    @Override
    public EmailMessageCriteria copy() {
        return new EmailMessageCriteria(this);
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

    public StringFilter getFrom() {
        return from;
    }

    public StringFilter from() {
        if (from == null) {
            from = new StringFilter();
        }
        return from;
    }

    public void setFrom(StringFilter from) {
        this.from = from;
    }

    public StringFilter getSubject() {
        return subject;
    }

    public StringFilter subject() {
        if (subject == null) {
            subject = new StringFilter();
        }
        return subject;
    }

    public void setSubject(StringFilter subject) {
        this.subject = subject;
    }

    public StringFilter getContent() {
        return content;
    }

    public StringFilter content() {
        if (content == null) {
            content = new StringFilter();
        }
        return content;
    }

    public void setContent(StringFilter content) {
        this.content = content;
    }

    public LongFilter getReferenceId() {
        return referenceId;
    }

    public LongFilter referenceId() {
        if (referenceId == null) {
            referenceId = new LongFilter();
        }
        return referenceId;
    }

    public void setReferenceId(LongFilter referenceId) {
        this.referenceId = referenceId;
    }

    public BooleanFilter getIsSent() {
        return isSent;
    }

    public BooleanFilter isSent() {
        if (isSent == null) {
            isSent = new BooleanFilter();
        }
        return isSent;
    }

    public void setIsSent(BooleanFilter isSent) {
        this.isSent = isSent;
    }

    public InstantFilter getSentTime() {
        return sentTime;
    }

    public InstantFilter sentTime() {
        if (sentTime == null) {
            sentTime = new InstantFilter();
        }
        return sentTime;
    }

    public void setSentTime(InstantFilter sentTime) {
        this.sentTime = sentTime;
    }

    public StringFilter getComment() {
        return comment;
    }

    public StringFilter comment() {
        if (comment == null) {
            comment = new StringFilter();
        }
        return comment;
    }

    public void setComment(StringFilter comment) {
        this.comment = comment;
    }

    public LongFilter getRecipientId() {
        return recipientId;
    }

    public LongFilter recipientId() {
        if (recipientId == null) {
            recipientId = new LongFilter();
        }
        return recipientId;
    }

    public void setRecipientId(LongFilter recipientId) {
        this.recipientId = recipientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EmailMessageCriteria that = (EmailMessageCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(from, that.from) &&
            Objects.equals(subject, that.subject) &&
            Objects.equals(content, that.content) &&
            Objects.equals(referenceId, that.referenceId) &&
            Objects.equals(isSent, that.isSent) &&
            Objects.equals(sentTime, that.sentTime) &&
            Objects.equals(comment, that.comment) &&
            Objects.equals(recipientId, that.recipientId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, subject, content, referenceId, isSent, sentTime, comment, recipientId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmailMessageCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (from != null ? "from=" + from + ", " : "") +
            (subject != null ? "subject=" + subject + ", " : "") +
            (content != null ? "content=" + content + ", " : "") +
            (referenceId != null ? "referenceId=" + referenceId + ", " : "") +
            (isSent != null ? "isSent=" + isSent + ", " : "") +
            (sentTime != null ? "sentTime=" + sentTime + ", " : "") +
            (comment != null ? "comment=" + comment + ", " : "") +
            (recipientId != null ? "recipientId=" + recipientId + ", " : "") +
            "}";
    }
}
