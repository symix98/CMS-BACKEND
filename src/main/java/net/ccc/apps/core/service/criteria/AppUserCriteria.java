package net.ccc.apps.core.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link net.ccc.apps.core.domain.AppUser} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.AppUserResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /app-users?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class AppUserCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter userId;

    private StringFilter name;

    private StringFilter email;

    private BooleanFilter isAdministrator;

    private BooleanFilter inactive;

    private LongFilter roleId;

    private Boolean distinct;

    private LongFilter attachmentId;

    private LongFilter appUserSettingsId;

    public AppUserCriteria() {}

    public LongFilter getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(LongFilter attachmentId) {
        this.attachmentId = attachmentId;
    }

    public AppUserCriteria(AppUserCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.isAdministrator = other.isAdministrator == null ? null : other.isAdministrator.copy();
        this.inactive = other.inactive == null ? null : other.inactive.copy();
        this.roleId = other.roleId == null ? null : other.roleId.copy();
        this.distinct = other.distinct;
        this.attachmentId = other.attachmentId == null ? null : other.attachmentId.copy();
        this.appUserSettingsId = other.appUserSettingsId == null ? null : other.appUserSettingsId.copy();
    }

    @Override
    public AppUserCriteria copy() {
        return new AppUserCriteria(this);
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

    public StringFilter getUserId() {
        return userId;
    }

    public StringFilter userId() {
        if (userId == null) {
            userId = new StringFilter();
        }
        return userId;
    }

    public void setUserId(StringFilter userId) {
        this.userId = userId;
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

    public BooleanFilter getIsAdministrator() {
        return isAdministrator;
    }

    public BooleanFilter isAdministrator() {
        if (isAdministrator == null) {
            isAdministrator = new BooleanFilter();
        }
        return isAdministrator;
    }

    public void setIsAdministrator(BooleanFilter isAdministrator) {
        this.isAdministrator = isAdministrator;
    }

    public BooleanFilter getInactive() {
        return inactive;
    }

    public BooleanFilter inactive() {
        if (inactive == null) {
            inactive = new BooleanFilter();
        }
        return inactive;
    }

    public void setInactive(BooleanFilter inactive) {
        this.inactive = inactive;
    }

    public LongFilter getRoleId() {
        return roleId;
    }

    public LongFilter roleId() {
        if (roleId == null) {
            roleId = new LongFilter();
        }
        return roleId;
    }

    public void setRoleId(LongFilter roleId) {
        this.roleId = roleId;
    }

    public LongFilter getAppUserSettingsId() {
        return appUserSettingsId;
    }

    public LongFilter appUserSettingsId() {
        if (appUserSettingsId == null) {
            appUserSettingsId = new LongFilter();
        }
        return appUserSettingsId;
    }

    public void setAppUserSettingsId(LongFilter appUserSettingsId) {
        this.appUserSettingsId = appUserSettingsId;
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
        final AppUserCriteria that = (AppUserCriteria) o;
        return (
            Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(isAdministrator, that.isAdministrator) &&
                Objects.equals(inactive, that.inactive) &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(distinct, that.distinct) &&
                Objects.equals(attachmentId, that.attachmentId) &&
                Objects.equals(appUserSettingsId, that.appUserSettingsId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, email, isAdministrator, inactive, roleId, distinct, attachmentId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUserCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (isAdministrator != null ? "isAdministrator=" + isAdministrator + ", " : "") +
            (inactive != null ? "inactive=" + inactive + ", " : "") +
            (roleId != null ? "roleId=" + roleId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            (attachmentId != null ? "attachmentId=" + attachmentId + ", " : "") +
            (appUserSettingsId != null ? "appUserSettingsId=" + appUserSettingsId + ", " : "") +
            "}";
    }
}
