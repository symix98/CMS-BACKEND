package net.ccc.apps.core.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import net.ccc.apps.core.domain.enumeration.Permission;
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
 * Criteria class for the {@link net.ccc.apps.core.domain.SecurityPermission} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.SecurityPermissionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /security-permissions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class SecurityPermissionCriteria implements Serializable, Criteria {

    /**
     * Class for filtering Permission
     */
    public static class PermissionFilter extends Filter<Permission> {

        public PermissionFilter() {}

        public PermissionFilter(PermissionFilter filter) {
            super(filter);
        }

        @Override
        public PermissionFilter copy() {
            return new PermissionFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter role;

    private StringFilter object;

    private PermissionFilter permission;

    private Boolean distinct;

    public SecurityPermissionCriteria() {}

    public SecurityPermissionCriteria(SecurityPermissionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.role = other.role == null ? null : other.role.copy();
        this.object = other.object == null ? null : other.object.copy();
        this.permission = other.permission == null ? null : other.permission.copy();
        this.distinct = other.distinct;
    }

    @Override
    public SecurityPermissionCriteria copy() {
        return new SecurityPermissionCriteria(this);
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

    public StringFilter getRole() {
        return role;
    }

    public StringFilter role() {
        if (role == null) {
            role = new StringFilter();
        }
        return role;
    }

    public void setRole(StringFilter role) {
        this.role = role;
    }

    public StringFilter getObject() {
        return object;
    }

    public StringFilter object() {
        if (object == null) {
            object = new StringFilter();
        }
        return object;
    }

    public void setObject(StringFilter object) {
        this.object = object;
    }

    public PermissionFilter getPermission() {
        return permission;
    }

    public PermissionFilter permission() {
        if (permission == null) {
            permission = new PermissionFilter();
        }
        return permission;
    }

    public void setPermission(PermissionFilter permission) {
        this.permission = permission;
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
        final SecurityPermissionCriteria that = (SecurityPermissionCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(role, that.role) &&
            Objects.equals(object, that.object) &&
            Objects.equals(permission, that.permission) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, object, permission, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SecurityPermissionCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (role != null ? "role=" + role + ", " : "") +
            (object != null ? "object=" + object + ", " : "") +
            (permission != null ? "permission=" + permission + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
