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
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link net.ccc.apps.campmanage.domain.BedDetails} entity. This class is used
 * in {@link net.ccc.apps.campmanage.web.rest.BedDetailsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /bed-details?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class BedDetailsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter bedNo;

    private StringFilter bedStatus;

    private StringFilter remarks;

    private LongFilter roomDetailsId;

    private Boolean distinct;

    public BedDetailsCriteria() {}

    public BedDetailsCriteria(BedDetailsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.bedNo = other.bedNo == null ? null : other.bedNo.copy();
        this.bedStatus = other.bedStatus == null ? null : other.bedStatus.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
        this.roomDetailsId = other.roomDetailsId == null ? null : other.roomDetailsId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public BedDetailsCriteria copy() {
        return new BedDetailsCriteria(this);
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

    public StringFilter getBedNo() {
        return bedNo;
    }

    public StringFilter bedNo() {
        if (bedNo == null) {
            bedNo = new StringFilter();
        }
        return bedNo;
    }

    public void setBedNo(StringFilter bedNo) {
        this.bedNo = bedNo;
    }

    public StringFilter getBedStatus() {
        return bedStatus;
    }

    public StringFilter bedStatus() {
        if (bedStatus == null) {
            bedStatus = new StringFilter();
        }
        return bedStatus;
    }

    public void setBedStatus(StringFilter bedStatus) {
        this.bedStatus = bedStatus;
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
        final BedDetailsCriteria that = (BedDetailsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(bedNo, that.bedNo) &&
            Objects.equals(bedStatus, that.bedStatus) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(roomDetailsId, that.roomDetailsId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bedNo, bedStatus, remarks, roomDetailsId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BedDetailsCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (bedNo != null ? "bedNo=" + bedNo + ", " : "") +
            (bedStatus != null ? "bedStatus=" + bedStatus + ", " : "") +
            (remarks != null ? "remarks=" + remarks + ", " : "") +
            (roomDetailsId != null ? "roomDetailsId=" + roomDetailsId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
