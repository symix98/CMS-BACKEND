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
 * Criteria class for the {@link net.ccc.apps.core.domain.ProjectSettings} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.ProjectSettingsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /project-settings?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ProjectSettingsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter property;

    private StringFilter description;

    private StringFilter value;

    private StringFilter valueType;

    private StringFilter category;

    private BooleanFilter isMultiple;

    private Boolean distinct;

    public ProjectSettingsCriteria() {}

    public ProjectSettingsCriteria(ProjectSettingsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.property = other.property == null ? null : other.property.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.value = other.value == null ? null : other.value.copy();
        this.valueType = other.valueType == null ? null : other.valueType.copy();
        this.category = other.category == null ? null : other.category.copy();
        this.isMultiple = other.isMultiple == null ? null : other.isMultiple.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ProjectSettingsCriteria copy() {
        return new ProjectSettingsCriteria(this);
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

    public StringFilter getProperty() {
        return property;
    }

    public StringFilter property() {
        if (property == null) {
            property = new StringFilter();
        }
        return property;
    }

    public void setProperty(StringFilter property) {
        this.property = property;
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

    public StringFilter getValueType() {
        return valueType;
    }

    public StringFilter valueType() {
        if (valueType == null) {
            valueType = new StringFilter();
        }
        return valueType;
    }

    public void setValueType(StringFilter valueType) {
        this.valueType = valueType;
    }

    public StringFilter getCategory() {
        return category;
    }

    public StringFilter category() {
        if (category == null) {
            category = new StringFilter();
        }
        return category;
    }

    public void setCategory(StringFilter category) {
        this.category = category;
    }

    public BooleanFilter getIsMultiple() {
        return isMultiple;
    }

    public BooleanFilter isMultiple() {
        if (isMultiple == null) {
            isMultiple = new BooleanFilter();
        }
        return isMultiple;
    }

    public void setIsMultiple(BooleanFilter isMultiple) {
        this.isMultiple = isMultiple;
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
        final ProjectSettingsCriteria that = (ProjectSettingsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(property, that.property) &&
            Objects.equals(description, that.description) &&
            Objects.equals(value, that.value) &&
            Objects.equals(valueType, that.valueType) &&
            Objects.equals(category, that.category) &&
            Objects.equals(isMultiple, that.isMultiple) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, property, description, value, valueType, category, isMultiple, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectSettingsCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (property != null ? "property=" + property + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (value != null ? "value=" + value + ", " : "") +
            (valueType != null ? "valueType=" + valueType + ", " : "") +
            (category != null ? "category=" + category + ", " : "") +
            (isMultiple != null ? "isMultiple=" + isMultiple + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
