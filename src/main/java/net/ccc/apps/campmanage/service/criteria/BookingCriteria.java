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
 * Criteria class for the {@link net.ccc.apps.campmanage.domain.Booking} entity. This class is used
 * in {@link net.ccc.apps.campmanage.web.rest.BookingResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /bookings?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class BookingCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter badgeNo;

    private StringFilter roomId;

    private LocalDateFilter checkInDate;

    private LocalDateFilter checkOutDate;

    private StringFilter guestStatus;

    private LocalDateFilter leaveStartDate;

    private LocalDateFilter leaveEndDate;

    private StringFilter bedNo;

    private StringFilter remarks;

    private BooleanFilter docuploaded;

    private LongFilter roomDetailsId;

    private LongFilter employeeMasterId;

    private Boolean distinct;

    public BookingCriteria() {}

    public BookingCriteria(BookingCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.badgeNo = other.badgeNo == null ? null : other.badgeNo.copy();
        this.roomId = other.roomId == null ? null : other.roomId.copy();
        this.checkInDate = other.checkInDate == null ? null : other.checkInDate.copy();
        this.checkOutDate = other.checkOutDate == null ? null : other.checkOutDate.copy();
        this.guestStatus = other.guestStatus == null ? null : other.guestStatus.copy();
        this.leaveStartDate = other.leaveStartDate == null ? null : other.leaveStartDate.copy();
        this.leaveEndDate = other.leaveEndDate == null ? null : other.leaveEndDate.copy();
        this.bedNo = other.bedNo == null ? null : other.bedNo.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
        this.docuploaded = other.docuploaded == null ? null : other.docuploaded.copy();
        this.roomDetailsId = other.roomDetailsId == null ? null : other.roomDetailsId.copy();
        this.employeeMasterId = other.employeeMasterId == null ? null : other.employeeMasterId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public BookingCriteria copy() {
        return new BookingCriteria(this);
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

    public StringFilter getBadgeNo() {
        return badgeNo;
    }

    public StringFilter badgeNo() {
        if (badgeNo == null) {
            badgeNo = new StringFilter();
        }
        return badgeNo;
    }

    public void setBadgeNo(StringFilter badgeNo) {
        this.badgeNo = badgeNo;
    }

    public StringFilter getRoomId() {
        return roomId;
    }

    public StringFilter roomId() {
        if (roomId == null) {
            roomId = new StringFilter();
        }
        return roomId;
    }

    public void setRoomId(StringFilter roomId) {
        this.roomId = roomId;
    }

    public LocalDateFilter getCheckInDate() {
        return checkInDate;
    }

    public LocalDateFilter checkInDate() {
        if (checkInDate == null) {
            checkInDate = new LocalDateFilter();
        }
        return checkInDate;
    }

    public void setCheckInDate(LocalDateFilter checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateFilter getCheckOutDate() {
        return checkOutDate;
    }

    public LocalDateFilter checkOutDate() {
        if (checkOutDate == null) {
            checkOutDate = new LocalDateFilter();
        }
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateFilter checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public StringFilter getGuestStatus() {
        return guestStatus;
    }

    public StringFilter guestStatus() {
        if (guestStatus == null) {
            guestStatus = new StringFilter();
        }
        return guestStatus;
    }

    public void setGuestStatus(StringFilter guestStatus) {
        this.guestStatus = guestStatus;
    }

    public LocalDateFilter getLeaveStartDate() {
        return leaveStartDate;
    }

    public LocalDateFilter leaveStartDate() {
        if (leaveStartDate == null) {
            leaveStartDate = new LocalDateFilter();
        }
        return leaveStartDate;
    }

    public void setLeaveStartDate(LocalDateFilter leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public LocalDateFilter getLeaveEndDate() {
        return leaveEndDate;
    }

    public LocalDateFilter leaveEndDate() {
        if (leaveEndDate == null) {
            leaveEndDate = new LocalDateFilter();
        }
        return leaveEndDate;
    }

    public void setLeaveEndDate(LocalDateFilter leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
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

    public BooleanFilter getDocuploaded() {
        return docuploaded;
    }

    public BooleanFilter docuploaded() {
        if (docuploaded == null) {
            docuploaded = new BooleanFilter();
        }
        return docuploaded;
    }

    public void setDocuploaded(BooleanFilter docuploaded) {
        this.docuploaded = docuploaded;
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

    public LongFilter getEmployeeMasterId() {
        return employeeMasterId;
    }

    public LongFilter employeeMasterId() {
        if (employeeMasterId == null) {
            employeeMasterId = new LongFilter();
        }
        return employeeMasterId;
    }

    public void setEmployeeMasterId(LongFilter employeeMasterId) {
        this.employeeMasterId = employeeMasterId;
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
        final BookingCriteria that = (BookingCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(badgeNo, that.badgeNo) &&
            Objects.equals(roomId, that.roomId) &&
            Objects.equals(checkInDate, that.checkInDate) &&
            Objects.equals(checkOutDate, that.checkOutDate) &&
            Objects.equals(guestStatus, that.guestStatus) &&
            Objects.equals(leaveStartDate, that.leaveStartDate) &&
            Objects.equals(leaveEndDate, that.leaveEndDate) &&
            Objects.equals(bedNo, that.bedNo) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(docuploaded, that.docuploaded) &&
            Objects.equals(roomDetailsId, that.roomDetailsId) &&
            Objects.equals(employeeMasterId, that.employeeMasterId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            badgeNo,
            roomId,
            checkInDate,
            checkOutDate,
            guestStatus,
            leaveStartDate,
            leaveEndDate,
            bedNo,
            remarks,
            docuploaded,
            roomDetailsId,
            employeeMasterId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookingCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (badgeNo != null ? "badgeNo=" + badgeNo + ", " : "") +
            (roomId != null ? "roomId=" + roomId + ", " : "") +
            (checkInDate != null ? "checkInDate=" + checkInDate + ", " : "") +
            (checkOutDate != null ? "checkOutDate=" + checkOutDate + ", " : "") +
            (guestStatus != null ? "guestStatus=" + guestStatus + ", " : "") +
            (leaveStartDate != null ? "leaveStartDate=" + leaveStartDate + ", " : "") +
            (leaveEndDate != null ? "leaveEndDate=" + leaveEndDate + ", " : "") +
            (bedNo != null ? "bedNo=" + bedNo + ", " : "") +
            (remarks != null ? "remarks=" + remarks + ", " : "") +
            (docuploaded != null ? "docuploaded=" + docuploaded + ", " : "") +
            (roomDetailsId != null ? "roomDetailsId=" + roomDetailsId + ", " : "") +
            (employeeMasterId != null ? "employeeMasterId=" + employeeMasterId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
