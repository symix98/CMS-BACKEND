package net.ccc.apps.campmanage.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link net.ccc.apps.campmanage.domain.Company} entity. This class is used
 * in {@link net.ccc.apps.campmanage.web.rest.CompanyResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /companies?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class CompanyCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter companyName;

    private StringFilter location;

    private StringFilter servicesProvided;

    private StringFilter contactPerson;

    private StringFilter contactNo;

    private StringFilter contactEmail;

    private StringFilter remarks;

    private StringFilter createdBy;

    private LocalDateFilter createdAt;

    private StringFilter modifyBy;

    private LocalDateFilter modifyAt;

    private LongFilter campId;

    private LongFilter servicesId;

    private Boolean distinct;

    public CompanyCriteria() {}

    public CompanyCriteria(CompanyCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.companyName = other.companyName == null ? null : other.companyName.copy();
        this.location = other.location == null ? null : other.location.copy();
        this.servicesProvided = other.servicesProvided == null ? null : other.servicesProvided.copy();
        this.contactPerson = other.contactPerson == null ? null : other.contactPerson.copy();
        this.contactNo = other.contactNo == null ? null : other.contactNo.copy();
        this.contactEmail = other.contactEmail == null ? null : other.contactEmail.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.modifyBy = other.modifyBy == null ? null : other.modifyBy.copy();
        this.modifyAt = other.modifyAt == null ? null : other.modifyAt.copy();
        this.campId = other.campId == null ? null : other.campId.copy();
        this.servicesId = other.servicesId == null ? null : other.servicesId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CompanyCriteria copy() {
        return new CompanyCriteria(this);
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

    public StringFilter getCompanyName() {
        return companyName;
    }

    public StringFilter companyName() {
        if (companyName == null) {
            companyName = new StringFilter();
        }
        return companyName;
    }

    public void setCompanyName(StringFilter companyName) {
        this.companyName = companyName;
    }

    public StringFilter getLocation() {
        return location;
    }

    public StringFilter location() {
        if (location == null) {
            location = new StringFilter();
        }
        return location;
    }

    public void setLocation(StringFilter location) {
        this.location = location;
    }

    public StringFilter getServicesProvided() {
        return servicesProvided;
    }

    public StringFilter servicesProvided() {
        if (servicesProvided == null) {
            servicesProvided = new StringFilter();
        }
        return servicesProvided;
    }

    public void setServicesProvided(StringFilter servicesProvided) {
        this.servicesProvided = servicesProvided;
    }

    public StringFilter getContactPerson() {
        return contactPerson;
    }

    public StringFilter contactPerson() {
        if (contactPerson == null) {
            contactPerson = new StringFilter();
        }
        return contactPerson;
    }

    public void setContactPerson(StringFilter contactPerson) {
        this.contactPerson = contactPerson;
    }

    public StringFilter getContactNo() {
        return contactNo;
    }

    public StringFilter contactNo() {
        if (contactNo == null) {
            contactNo = new StringFilter();
        }
        return contactNo;
    }

    public void setContactNo(StringFilter contactNo) {
        this.contactNo = contactNo;
    }

    public StringFilter getContactEmail() {
        return contactEmail;
    }

    public StringFilter contactEmail() {
        if (contactEmail == null) {
            contactEmail = new StringFilter();
        }
        return contactEmail;
    }

    public void setContactEmail(StringFilter contactEmail) {
        this.contactEmail = contactEmail;
    }

    public StringFilter getRemarks() {
        return remarks;
    }

    public StringFilter remarks() {
        if (remarks == null) {
            remarks = new StringFilter();
        }
        return remarks;
    }

    public void setRemarks(StringFilter remarks) {
        this.remarks = remarks;
    }

    public StringFilter getCreatedBy() {
        return createdBy;
    }

    public StringFilter createdBy() {
        if (createdBy == null) {
            createdBy = new StringFilter();
        }
        return createdBy;
    }

    public void setCreatedBy(StringFilter createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateFilter getCreatedAt() {
        return createdAt;
    }

    public LocalDateFilter createdAt() {
        if (createdAt == null) {
            createdAt = new LocalDateFilter();
        }
        return createdAt;
    }

    public void setCreatedAt(LocalDateFilter createdAt) {
        this.createdAt = createdAt;
    }

    public StringFilter getModifyBy() {
        return modifyBy;
    }

    public StringFilter modifyBy() {
        if (modifyBy == null) {
            modifyBy = new StringFilter();
        }
        return modifyBy;
    }

    public void setModifyBy(StringFilter modifyBy) {
        this.modifyBy = modifyBy;
    }

    public LocalDateFilter getModifyAt() {
        return modifyAt;
    }

    public LocalDateFilter modifyAt() {
        if (modifyAt == null) {
            modifyAt = new LocalDateFilter();
        }
        return modifyAt;
    }

    public void setModifyAt(LocalDateFilter modifyAt) {
        this.modifyAt = modifyAt;
    }

    public LongFilter getCampId() {
        return campId;
    }

    public LongFilter campId() {
        if (campId == null) {
            campId = new LongFilter();
        }
        return campId;
    }

    public void setCampId(LongFilter campId) {
        this.campId = campId;
    }

    public LongFilter getServicesId() {
        return servicesId;
    }

    public LongFilter servicesId() {
        if (servicesId == null) {
            servicesId = new LongFilter();
        }
        return servicesId;
    }

    public void setServicesId(LongFilter servicesId) {
        this.servicesId = servicesId;
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
        final CompanyCriteria that = (CompanyCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(companyName, that.companyName) &&
            Objects.equals(location, that.location) &&
            Objects.equals(servicesProvided, that.servicesProvided) &&
            Objects.equals(contactPerson, that.contactPerson) &&
            Objects.equals(contactNo, that.contactNo) &&
            Objects.equals(contactEmail, that.contactEmail) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(modifyBy, that.modifyBy) &&
            Objects.equals(modifyAt, that.modifyAt) &&
            Objects.equals(campId, that.campId) &&
            Objects.equals(servicesId, that.servicesId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            companyName,
            location,
            servicesProvided,
            contactPerson,
            contactNo,
            contactEmail,
            remarks,
            createdBy,
            createdAt,
            modifyBy,
            modifyAt,
            campId,
            servicesId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CompanyCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (companyName != null ? "companyName=" + companyName + ", " : "") +
            (location != null ? "location=" + location + ", " : "") +
            (servicesProvided != null ? "servicesProvided=" + servicesProvided + ", " : "") +
            (contactPerson != null ? "contactPerson=" + contactPerson + ", " : "") +
            (contactNo != null ? "contactNo=" + contactNo + ", " : "") +
            (contactEmail != null ? "contactEmail=" + contactEmail + ", " : "") +
            (remarks != null ? "remarks=" + remarks + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
            (modifyBy != null ? "modifyBy=" + modifyBy + ", " : "") +
            (modifyAt != null ? "modifyAt=" + modifyAt + ", " : "") +
            (campId != null ? "campId=" + campId + ", " : "") +
            (servicesId != null ? "servicesId=" + servicesId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
