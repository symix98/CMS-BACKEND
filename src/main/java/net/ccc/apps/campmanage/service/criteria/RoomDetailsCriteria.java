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
 * Criteria class for the {@link net.ccc.apps.campmanage.domain.RoomDetails} entity. This class is used
 * in {@link net.ccc.apps.campmanage.web.rest.RoomDetailsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /room-details?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class RoomDetailsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter roomDescription;

    private StringFilter block;

    private StringFilter floor;

    private StringFilter roomCategory;

    private IntegerFilter bedCount;

    private StringFilter roomNo;

    private StringFilter roomAllocation;

    private StringFilter roomConfiguration;

    private LocalDateFilter availableFrom;

    private StringFilter roomStatus;

    private BooleanFilter bedOnly;

    private FloatFilter monthlyRate;

    private FloatFilter dailyRate;

    private FloatFilter bedRate;

    private FloatFilter reservationRate;

    private StringFilter remarks;

    private StringFilter createdBy;

    private LocalDateFilter createdAt;

    private StringFilter modifyBy;

    private LocalDateFilter modifyAt;

    private LongFilter cateringId;

    private LongFilter bedDetailsId;

    private LongFilter roomAdvanceBookingId;

    private LongFilter bookingId;

    private LongFilter campId;

    private Boolean distinct;

    public RoomDetailsCriteria() {}

    public RoomDetailsCriteria(RoomDetailsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.roomDescription = other.roomDescription == null ? null : other.roomDescription.copy();
        this.block = other.block == null ? null : other.block.copy();
        this.floor = other.floor == null ? null : other.floor.copy();
        this.roomCategory = other.roomCategory == null ? null : other.roomCategory.copy();
        this.bedCount = other.bedCount == null ? null : other.bedCount.copy();
        this.roomNo = other.roomNo == null ? null : other.roomNo.copy();
        this.roomAllocation = other.roomAllocation == null ? null : other.roomAllocation.copy();
        this.roomConfiguration = other.roomConfiguration == null ? null : other.roomConfiguration.copy();
        this.availableFrom = other.availableFrom == null ? null : other.availableFrom.copy();
        this.roomStatus = other.roomStatus == null ? null : other.roomStatus.copy();
        this.bedOnly = other.bedOnly == null ? null : other.bedOnly.copy();
        this.monthlyRate = other.monthlyRate == null ? null : other.monthlyRate.copy();
        this.dailyRate = other.dailyRate == null ? null : other.dailyRate.copy();
        this.bedRate = other.bedRate == null ? null : other.bedRate.copy();
        this.reservationRate = other.reservationRate == null ? null : other.reservationRate.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.modifyBy = other.modifyBy == null ? null : other.modifyBy.copy();
        this.modifyAt = other.modifyAt == null ? null : other.modifyAt.copy();
        this.cateringId = other.cateringId == null ? null : other.cateringId.copy();
        this.bedDetailsId = other.bedDetailsId == null ? null : other.bedDetailsId.copy();
        this.roomAdvanceBookingId = other.roomAdvanceBookingId == null ? null : other.roomAdvanceBookingId.copy();
        this.bookingId = other.bookingId == null ? null : other.bookingId.copy();
        this.campId = other.campId == null ? null : other.campId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public RoomDetailsCriteria copy() {
        return new RoomDetailsCriteria(this);
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

    public StringFilter getRoomDescription() {
        return roomDescription;
    }

    public StringFilter roomDescription() {
        if (roomDescription == null) {
            roomDescription = new StringFilter();
        }
        return roomDescription;
    }

    public void setRoomDescription(StringFilter roomDescription) {
        this.roomDescription = roomDescription;
    }

    public StringFilter getBlock() {
        return block;
    }

    public StringFilter block() {
        if (block == null) {
            block = new StringFilter();
        }
        return block;
    }

    public void setBlock(StringFilter block) {
        this.block = block;
    }

    public StringFilter getFloor() {
        return floor;
    }

    public StringFilter floor() {
        if (floor == null) {
            floor = new StringFilter();
        }
        return floor;
    }

    public void setFloor(StringFilter floor) {
        this.floor = floor;
    }

    public StringFilter getRoomCategory() {
        return roomCategory;
    }

    public StringFilter roomCategory() {
        if (roomCategory == null) {
            roomCategory = new StringFilter();
        }
        return roomCategory;
    }

    public void setRoomCategory(StringFilter roomCategory) {
        this.roomCategory = roomCategory;
    }

    public IntegerFilter getBedCount() {
        return bedCount;
    }

    public IntegerFilter bedCount() {
        if (bedCount == null) {
            bedCount = new IntegerFilter();
        }
        return bedCount;
    }

    public void setBedCount(IntegerFilter bedCount) {
        this.bedCount = bedCount;
    }

    public StringFilter getRoomNo() {
        return roomNo;
    }

    public StringFilter roomNo() {
        if (roomNo == null) {
            roomNo = new StringFilter();
        }
        return roomNo;
    }

    public void setRoomNo(StringFilter roomNo) {
        this.roomNo = roomNo;
    }

    public StringFilter getRoomAllocation() {
        return roomAllocation;
    }

    public StringFilter roomAllocation() {
        if (roomAllocation == null) {
            roomAllocation = new StringFilter();
        }
        return roomAllocation;
    }

    public void setRoomAllocation(StringFilter roomAllocation) {
        this.roomAllocation = roomAllocation;
    }

    public StringFilter getRoomConfiguration() {
        return roomConfiguration;
    }

    public StringFilter roomConfiguration() {
        if (roomConfiguration == null) {
            roomConfiguration = new StringFilter();
        }
        return roomConfiguration;
    }

    public void setRoomConfiguration(StringFilter roomConfiguration) {
        this.roomConfiguration = roomConfiguration;
    }

    public LocalDateFilter getAvailableFrom() {
        return availableFrom;
    }

    public LocalDateFilter availableFrom() {
        if (availableFrom == null) {
            availableFrom = new LocalDateFilter();
        }
        return availableFrom;
    }

    public void setAvailableFrom(LocalDateFilter availableFrom) {
        this.availableFrom = availableFrom;
    }

    public StringFilter getRoomStatus() {
        return roomStatus;
    }

    public StringFilter roomStatus() {
        if (roomStatus == null) {
            roomStatus = new StringFilter();
        }
        return roomStatus;
    }

    public void setRoomStatus(StringFilter roomStatus) {
        this.roomStatus = roomStatus;
    }

    public BooleanFilter getBedOnly() {
        return bedOnly;
    }

    public BooleanFilter bedOnly() {
        if (bedOnly == null) {
            bedOnly = new BooleanFilter();
        }
        return bedOnly;
    }

    public void setBedOnly(BooleanFilter bedOnly) {
        this.bedOnly = bedOnly;
    }

    public FloatFilter getMonthlyRate() {
        return monthlyRate;
    }

    public FloatFilter monthlyRate() {
        if (monthlyRate == null) {
            monthlyRate = new FloatFilter();
        }
        return monthlyRate;
    }

    public void setMonthlyRate(FloatFilter monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public FloatFilter getDailyRate() {
        return dailyRate;
    }

    public FloatFilter dailyRate() {
        if (dailyRate == null) {
            dailyRate = new FloatFilter();
        }
        return dailyRate;
    }

    public void setDailyRate(FloatFilter dailyRate) {
        this.dailyRate = dailyRate;
    }

    public FloatFilter getBedRate() {
        return bedRate;
    }

    public FloatFilter bedRate() {
        if (bedRate == null) {
            bedRate = new FloatFilter();
        }
        return bedRate;
    }

    public void setBedRate(FloatFilter bedRate) {
        this.bedRate = bedRate;
    }

    public FloatFilter getReservationRate() {
        return reservationRate;
    }

    public FloatFilter reservationRate() {
        if (reservationRate == null) {
            reservationRate = new FloatFilter();
        }
        return reservationRate;
    }

    public void setReservationRate(FloatFilter reservationRate) {
        this.reservationRate = reservationRate;
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

    public LongFilter getCateringId() {
        return cateringId;
    }

    public LongFilter cateringId() {
        if (cateringId == null) {
            cateringId = new LongFilter();
        }
        return cateringId;
    }

    public void setCateringId(LongFilter cateringId) {
        this.cateringId = cateringId;
    }

    public LongFilter getBedDetailsId() {
        return bedDetailsId;
    }

    public LongFilter bedDetailsId() {
        if (bedDetailsId == null) {
            bedDetailsId = new LongFilter();
        }
        return bedDetailsId;
    }

    public void setBedDetailsId(LongFilter bedDetailsId) {
        this.bedDetailsId = bedDetailsId;
    }

    public LongFilter getRoomAdvanceBookingId() {
        return roomAdvanceBookingId;
    }

    public LongFilter roomAdvanceBookingId() {
        if (roomAdvanceBookingId == null) {
            roomAdvanceBookingId = new LongFilter();
        }
        return roomAdvanceBookingId;
    }

    public void setRoomAdvanceBookingId(LongFilter roomAdvanceBookingId) {
        this.roomAdvanceBookingId = roomAdvanceBookingId;
    }

    public LongFilter getBookingId() {
        return bookingId;
    }

    public LongFilter bookingId() {
        if (bookingId == null) {
            bookingId = new LongFilter();
        }
        return bookingId;
    }

    public void setBookingId(LongFilter bookingId) {
        this.bookingId = bookingId;
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
        final RoomDetailsCriteria that = (RoomDetailsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(roomDescription, that.roomDescription) &&
            Objects.equals(block, that.block) &&
            Objects.equals(floor, that.floor) &&
            Objects.equals(roomCategory, that.roomCategory) &&
            Objects.equals(bedCount, that.bedCount) &&
            Objects.equals(roomNo, that.roomNo) &&
            Objects.equals(roomAllocation, that.roomAllocation) &&
            Objects.equals(roomConfiguration, that.roomConfiguration) &&
            Objects.equals(availableFrom, that.availableFrom) &&
            Objects.equals(roomStatus, that.roomStatus) &&
            Objects.equals(bedOnly, that.bedOnly) &&
            Objects.equals(monthlyRate, that.monthlyRate) &&
            Objects.equals(dailyRate, that.dailyRate) &&
            Objects.equals(bedRate, that.bedRate) &&
            Objects.equals(reservationRate, that.reservationRate) &&
            Objects.equals(remarks, that.remarks) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(modifyBy, that.modifyBy) &&
            Objects.equals(modifyAt, that.modifyAt) &&
            Objects.equals(cateringId, that.cateringId) &&
            Objects.equals(bedDetailsId, that.bedDetailsId) &&
            Objects.equals(roomAdvanceBookingId, that.roomAdvanceBookingId) &&
            Objects.equals(bookingId, that.bookingId) &&
            Objects.equals(campId, that.campId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            roomDescription,
            block,
            floor,
            roomCategory,
            bedCount,
            roomNo,
            roomAllocation,
            roomConfiguration,
            availableFrom,
            roomStatus,
            bedOnly,
            monthlyRate,
            dailyRate,
            bedRate,
            reservationRate,
            remarks,
            createdBy,
            createdAt,
            modifyBy,
            modifyAt,
            cateringId,
            bedDetailsId,
            roomAdvanceBookingId,
            bookingId,
            campId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoomDetailsCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (roomDescription != null ? "roomDescription=" + roomDescription + ", " : "") +
            (block != null ? "block=" + block + ", " : "") +
            (floor != null ? "floor=" + floor + ", " : "") +
            (roomCategory != null ? "roomCategory=" + roomCategory + ", " : "") +
            (bedCount != null ? "bedCount=" + bedCount + ", " : "") +
            (roomNo != null ? "roomNo=" + roomNo + ", " : "") +
            (roomAllocation != null ? "roomAllocation=" + roomAllocation + ", " : "") +
            (roomConfiguration != null ? "roomConfiguration=" + roomConfiguration + ", " : "") +
            (availableFrom != null ? "availableFrom=" + availableFrom + ", " : "") +
            (roomStatus != null ? "roomStatus=" + roomStatus + ", " : "") +
            (bedOnly != null ? "bedOnly=" + bedOnly + ", " : "") +
            (monthlyRate != null ? "monthlyRate=" + monthlyRate + ", " : "") +
            (dailyRate != null ? "dailyRate=" + dailyRate + ", " : "") +
            (bedRate != null ? "bedRate=" + bedRate + ", " : "") +
            (reservationRate != null ? "reservationRate=" + reservationRate + ", " : "") +
            (remarks != null ? "remarks=" + remarks + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
            (modifyBy != null ? "modifyBy=" + modifyBy + ", " : "") +
            (modifyAt != null ? "modifyAt=" + modifyAt + ", " : "") +
            (cateringId != null ? "cateringId=" + cateringId + ", " : "") +
            (bedDetailsId != null ? "bedDetailsId=" + bedDetailsId + ", " : "") +
            (roomAdvanceBookingId != null ? "roomAdvanceBookingId=" + roomAdvanceBookingId + ", " : "") +
            (bookingId != null ? "bookingId=" + bookingId + ", " : "") +
            (campId != null ? "campId=" + campId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
