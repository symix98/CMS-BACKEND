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
 * Criteria class for the {@link net.ccc.materialrequisition.domain.AppUserSettings} entity. This class is used
 * in {@link net.ccc.materialrequisition.web.rest.AppUserSettingsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /app-user-settings?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class AppUserSettingsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter property;

    private StringFilter value;

    private LongFilter appUserId;

    private Boolean distinct;

    public AppUserSettingsCriteria() {}

    public AppUserSettingsCriteria(AppUserSettingsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.property = other.property == null ? null : other.property.copy();
        this.value = other.value == null ? null : other.value.copy();
        this.appUserId = other.appUserId == null ? null : other.appUserId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public AppUserSettingsCriteria copy() {
        return new AppUserSettingsCriteria(this);
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

    public LongFilter getAppUserId() {
        return appUserId;
    }

    public LongFilter appUserId() {
        if (appUserId == null) {
            appUserId = new LongFilter();
        }
        return appUserId;
    }

    public void setAppUserId(LongFilter appUserId) {
        this.appUserId = appUserId;
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
        final AppUserSettingsCriteria that = (AppUserSettingsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(property, that.property) &&
            Objects.equals(value, that.value) &&
            Objects.equals(appUserId, that.appUserId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, property, value, appUserId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUserSettingsCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (property != null ? "property=" + property + ", " : "") +
            (value != null ? "value=" + value + ", " : "") +
            (appUserId != null ? "appUserId=" + appUserId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
