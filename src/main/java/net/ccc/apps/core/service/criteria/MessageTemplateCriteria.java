package net.ccc.apps.core.service.criteria;

import java.io.Serializable;
import java.util.Objects;
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
 * Criteria class for the {@link net.ccc.apps.core.domain.MessageTemplate} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.MessageTemplateResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /message-templates?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class MessageTemplateCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter messageId;

    private StringFilter subject;

    private StringFilter body;

    private Boolean distinct;

    public MessageTemplateCriteria() {}

    public MessageTemplateCriteria(MessageTemplateCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.messageId = other.messageId == null ? null : other.messageId.copy();
        this.subject = other.subject == null ? null : other.subject.copy();
        this.body = other.body == null ? null : other.body.copy();
        this.distinct = other.distinct;
    }

    @Override
    public MessageTemplateCriteria copy() {
        return new MessageTemplateCriteria(this);
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

    public StringFilter getBody() {
        return body;
    }

    public StringFilter body() {
        if (body == null) {
            body = new StringFilter();
        }
        return body;
    }

    public void setBody(StringFilter body) {
        this.body = body;
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
        final MessageTemplateCriteria that = (MessageTemplateCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(messageId, that.messageId) &&
            Objects.equals(subject, that.subject) &&
            Objects.equals(body, that.body) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageId, subject, body, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageTemplateCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (messageId != null ? "messageId=" + messageId + ", " : "") +
            (subject != null ? "subject=" + subject + ", " : "") +
            (body != null ? "body=" + body + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
