package net.ccc.apps.core.service.criteria;

import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link net.ccc.apps.core.domain.MailRecipient} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.MailRecipientResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /mail-recipients?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MailRecipientCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter email;

    private LongFilter recipientsId;

    public MailRecipientCriteria() {}

    public MailRecipientCriteria(MailRecipientCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.recipientsId = other.recipientsId == null ? null : other.recipientsId.copy();
    }

    @Override
    public MailRecipientCriteria copy() {
        return new MailRecipientCriteria(this);
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

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getEmail() {
        return email;
    }

    public StringFilter email() {
        if (email == null) {
            email = new StringFilter();
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public LongFilter getRecipientsId() {
        return recipientsId;
    }

    public LongFilter recipientsId() {
        if (recipientsId == null) {
            recipientsId = new LongFilter();
        }
        return recipientsId;
    }

    public void setRecipientsId(LongFilter recipientsId) {
        this.recipientsId = recipientsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MailRecipientCriteria that = (MailRecipientCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(email, that.email) &&
            Objects.equals(recipientsId, that.recipientsId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, recipientsId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MailRecipientCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (recipientsId != null ? "recipientsId=" + recipientsId + ", " : "") +
            "}";
    }
}
