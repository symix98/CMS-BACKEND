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
 * Criteria class for the {@link net.ccc.apps.core.domain.Role} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.RoleResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /roles?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class RoleCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter roleId;

    private StringFilter description;

    private LongFilter appUserId;

    private Boolean distinct;

    public RoleCriteria() {}

    public RoleCriteria(RoleCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.roleId = other.roleId == null ? null : other.roleId.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.appUserId = other.appUserId == null ? null : other.appUserId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public RoleCriteria copy() {
        return new RoleCriteria(this);
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

    public StringFilter getRoleId() {
        return roleId;
    }

    public StringFilter roleId() {
        if (roleId == null) {
            roleId = new StringFilter();
        }
        return roleId;
    }

    public void setRoleId(StringFilter roleId) {
        this.roleId = roleId;
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
        final RoleCriteria that = (RoleCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(roleId, that.roleId) &&
            Objects.equals(description, that.description) &&
            Objects.equals(appUserId, that.appUserId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, description, appUserId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (roleId != null ? "roleId=" + roleId + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (appUserId != null ? "appUserId=" + appUserId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
