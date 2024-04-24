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
 * Criteria class for the {@link net.ccc.apps.campmanage.domain.Catering} entity. This class is used
 * in {@link net.ccc.apps.campmanage.web.rest.CateringResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /caterings?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class CateringCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter cateringName;

    private StringFilter location;

    private StringFilter messCategory;

    private StringFilter mealType;

    private StringFilter menuType;

    private FloatFilter rate;

    private FloatFilter upgradedRate;

    private StringFilter remarks;

    private StringFilter createdBy;

    private LocalDateFilter createdAt;

    private StringFilter modifyBy;

    private LocalDateFilter modifyAt;

    private LongFilter roomDetailsId;

    private Boolean distinct;

    public CateringCriteria() {}

    public CateringCriteria(CateringCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.cateringName = other.cateringName == null ? null : other.cateringName.copy();
        this.location = other.location == null ? null : other.location.copy();
        this.messCategory = other.messCategory == null ? null : other.messCategory.copy();
        this.mealType = other.mealType == null ? null : other.mealType.copy();
        this.menuType = other.menuType == null ? null : other.menuType.copy();
        this.rate = other.rate == null ? null : other.rate.copy();
        this.upgradedRate = other.upgradedRate == null ? null : other.upgradedRate.copy();
        this.remarks = other.remarks == null ? null : other.remarks.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.modifyBy = other.modifyBy == null ? null : other.modifyBy.copy();
        this.modifyAt = other.modifyAt == null ? null : other.modifyAt.copy();
        this.roomDetailsId = other.roomDetailsId == null ? null : other.roomDetailsId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CateringCriteria copy() {
        return new CateringCriteria(this);
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

    public StringFilter getCateringName() {
        return cateringName;
    }

    public StringFilter cateringName() {
        if (cateringName == null) {
            cateringName = new StringFilter();
        }
        return cateringName;
    }

    public void setCateringName(StringFilter cateringName) {
        this.cateringName = cateringName;
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

    public StringFilter getMessCategory() {
        return messCategory;
    }

    public StringFilter messCategory() {
        if (messCategory == null) {
            messCategory = new StringFilter();
        }
        return messCategory;
    }

    public void setMessCategory(StringFilter messCategory) {
        this.messCategory = messCategory;
    }

    public StringFilter getMealType() {
        return mealType;
    }

    public StringFilter mealType() {
        if (mealType == null) {
            mealType = new StringFilter();
        }
        return mealType;
    }

    public void setMealType(StringFilter mealType) {
        this.mealType = mealType;
    }

    public StringFilter getMenuType() {
        return menuType;
    }

    public StringFilter menuType() {
        if (menuType == null) {
            menuType = new StringFilter();
        }
        return menuType;
    }

    public void setMenuType(StringFilter menuType) {
        this.menuType = menuType;
    }

    public FloatFilter getRate() {
        return rate;
    }

    public FloatFilter rate() {
        if (rate == null) {
            rate = new FloatFilter();
        }
        return rate;
    }

    public void setRate(FloatFilter rate) {
        this.rate = rate;
    }

    public FloatFilter getUpgradedRate() {
        return upgradedRate;
    }

    public FloatFilter upgradedRate() {
        if (upgradedRate == null) {
            upgradedRate = new FloatFilter();
        }
        return upgradedRate;
    }

    public void setUpgradedRate(FloatFilter upgradedRate) {
        this.upgradedRate = upgradedRate;
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
        final CateringCriteria that = (CateringCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(cateringName, that.cateringName) &&
            Objects.equals(location, that.location) &&
            Objects.equals(messCategory, that.messCategory) &&
            Objects.equals(mealType, that.mealType) &&
            Objects.equals(menuType, that.menuType) &&
            Objects.equals(rate, that.rate) &&
            Objects.equals(upgradedRate, that.upgradedRate) &&
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
            cateringName,
            location,
            messCategory,
            mealType,
            menuType,
            rate,
            upgradedRate,
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
        return "CateringCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (cateringName != null ? "cateringName=" + cateringName + ", " : "") +
            (location != null ? "location=" + location + ", " : "") +
            (messCategory != null ? "messCategory=" + messCategory + ", " : "") +
            (mealType != null ? "mealType=" + mealType + ", " : "") +
            (menuType != null ? "menuType=" + menuType + ", " : "") +
            (rate != null ? "rate=" + rate + ", " : "") +
            (upgradedRate != null ? "upgradedRate=" + upgradedRate + ", " : "") +
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
