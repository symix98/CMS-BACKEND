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
 * Criteria class for the {@link net.ccc.apps.core.domain.Reference} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.ReferenceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /references?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ReferenceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter referenceKey;

    private StringFilter value;

    private StringFilter description;

    private StringFilter type;

    private Boolean distinct;

    public ReferenceCriteria() {}

    public ReferenceCriteria(ReferenceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.referenceKey = other.referenceKey == null ? null : other.referenceKey.copy();
        this.value = other.value == null ? null : other.value.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ReferenceCriteria copy() {
        return new ReferenceCriteria(this);
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

    public StringFilter getReferenceKey() {
        return referenceKey;
    }

    public StringFilter referenceKey() {
        if (referenceKey == null) {
            referenceKey = new StringFilter();
        }
        return referenceKey;
    }

    public void setReferenceKey(StringFilter referenceKey) {
        this.referenceKey = referenceKey;
    }

    public StringFilter getValue() {
        return value;
    }

    public StringFilter value() {
        if (value == null) {
            value = new StringFilter();
        }
        return value;
    }

    public void setValue(StringFilter value) {
        this.value = value;
    }

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getType() {
        return type;
    }

    public StringFilter type() {
        if (type == null) {
            type = new StringFilter();
        }
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
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
        final ReferenceCriteria that = (ReferenceCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(referenceKey, that.referenceKey) &&
            Objects.equals(value, that.value) &&
            Objects.equals(description, that.description) &&
            Objects.equals(type, that.type) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, referenceKey, value, description, type, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReferenceCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (referenceKey != null ? "referenceKey=" + referenceKey + ", " : "") +
            (value != null ? "value=" + value + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
