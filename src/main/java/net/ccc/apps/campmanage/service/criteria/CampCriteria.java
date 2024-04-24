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
 * Criteria class for the {@link net.ccc.apps.campmanage.domain.Camp} entity. This class is used
 * in {@link net.ccc.apps.campmanage.web.rest.CampResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /camps?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class CampCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter campName;

    private StringFilter location;

    private StringFilter remarks;

    private StringFilter createdBy;

    private LocalDateFilter createdAt;

    private StringFilter modifyBy;

    private LocalDateFilter modifyAt;

    private LongFilter roomDetailsId;

    private LongFilter companyId;

    private Boolean distinct;

    public CampCriteria() {}

    public CampCriteria(CampCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.campName = other.campName == null ? null : other.campName.copy();
        this.location = other.location == null ? null : other.location.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.modifyBy = other.modifyBy == null ? null : other.modifyBy.copy();
        this.modifyAt = other.modifyAt == null ? null : other.modifyAt.copy();
        this.roomDetailsId = other.roomDetailsId == null ? null : other.roomDetailsId.copy();
        this.companyId = other.companyId == null ? null : other.companyId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CampCriteria copy() {
        return new CampCriteria(this);
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

    public StringFilter getCampName() {
        return campName;
    }

    public StringFilter campName() {
        if (campName == null) {
            campName = new StringFilter();
        }
        return campName;
    }

    public void setCampName(StringFilter campName) {
        this.campName = campName;
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

    public LongFilter getRoomDetailsId() {
        return roomDetailsId;
    }

    public LongFilter roomDetailsId() {
        if (roomDetailsId == null) {
            roomDetailsId = new LongFilter();
        }
        return roomDetailsId;
    }

    public void setRoomDetailsId(LongFilter roomDetailsId) {
        this.roomDetailsId = roomDetailsId;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public LongFilter companyId() {
        if (companyId == null) {
            companyId = new LongFilter();
        }
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
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
        final CampCriteria that = (CampCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(campName, that.campName) &&
            Objects.equals(location, that.location) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(modifyBy, that.modifyBy) &&
            Objects.equals(modifyAt, that.modifyAt) &&
            Objects.equals(roomDetailsId, that.roomDetailsId) &&
            Objects.equals(companyId, that.companyId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, campName, location, remarks, createdBy, createdAt, modifyBy, modifyAt, roomDetailsId, companyId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CampCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (campName != null ? "campName=" + campName + ", " : "") +
            (location != null ? "location=" + location + ", " : "") +
            (remarks != null ? "remarks=" + remarks + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
            (modifyBy != null ? "modifyBy=" + modifyBy + ", " : "") +
            (modifyAt != null ? "modifyAt=" + modifyAt + ", " : "") +
            (roomDetailsId != null ? "roomDetailsId=" + roomDetailsId + ", " : "") +
            (companyId != null ? "companyId=" + companyId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
