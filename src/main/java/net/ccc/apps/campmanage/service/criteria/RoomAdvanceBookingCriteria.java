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
 * Criteria class for the {@link net.ccc.apps.campmanage.domain.RoomAdvanceBooking} entity. This class is used
 * in {@link net.ccc.apps.campmanage.web.rest.RoomAdvanceBookingResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /room-advance-bookings?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class RoomAdvanceBookingCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter bookingStartDate;

    private LocalDateFilter bookingEndDate;

    private StringFilter remarks;

    private StringFilter createdBy;

    private LocalDateFilter createdAt;

    private StringFilter modifyBy;

    private LocalDateFilter modifyAt;

    private LongFilter roomDetailsId;

    private Boolean distinct;

    public RoomAdvanceBookingCriteria() {}

    public RoomAdvanceBookingCriteria(RoomAdvanceBookingCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.bookingStartDate = other.bookingStartDate == null ? null : other.bookingStartDate.copy();
        this.bookingEndDate = other.bookingEndDate == null ? null : other.bookingEndDate.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.modifyBy = other.modifyBy == null ? null : other.modifyBy.copy();
        this.modifyAt = other.modifyAt == null ? null : other.modifyAt.copy();
        this.roomDetailsId = other.roomDetailsId == null ? null : other.roomDetailsId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public RoomAdvanceBookingCriteria copy() {
        return new RoomAdvanceBookingCriteria(this);
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

    public LocalDateFilter getBookingStartDate() {
        return bookingStartDate;
    }

    public LocalDateFilter bookingStartDate() {
        if (bookingStartDate == null) {
            bookingStartDate = new LocalDateFilter();
        }
        return bookingStartDate;
    }

    public void setBookingStartDate(LocalDateFilter bookingStartDate) {
        this.bookingStartDate = bookingStartDate;
    }

    public LocalDateFilter getBookingEndDate() {
        return bookingEndDate;
    }

    public LocalDateFilter bookingEndDate() {
        if (bookingEndDate == null) {
            bookingEndDate = new LocalDateFilter();
        }
        return bookingEndDate;
    }

    public void setBookingEndDate(LocalDateFilter bookingEndDate) {
        this.bookingEndDate = bookingEndDate;
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
        final RoomAdvanceBookingCriteria that = (RoomAdvanceBookingCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(bookingStartDate, that.bookingStartDate) &&
            Objects.equals(bookingEndDate, that.bookingEndDate) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(modifyBy, that.modifyBy) &&
            Objects.equals(modifyAt, that.modifyAt) &&
            Objects.equals(roomDetailsId, that.roomDetailsId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            bookingStartDate,
            bookingEndDate,
            remarks,
            createdBy,
            createdAt,
            modifyBy,
            modifyAt,
            roomDetailsId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoomAdvanceBookingCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (bookingStartDate != null ? "bookingStartDate=" + bookingStartDate + ", " : "") +
            (bookingEndDate != null ? "bookingEndDate=" + bookingEndDate + ", " : "") +
            (remarks != null ? "remarks=" + remarks + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
            (modifyBy != null ? "modifyBy=" + modifyBy + ", " : "") +
            (modifyAt != null ? "modifyAt=" + modifyAt + ", " : "") +
            (roomDetailsId != null ? "roomDetailsId=" + roomDetailsId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
