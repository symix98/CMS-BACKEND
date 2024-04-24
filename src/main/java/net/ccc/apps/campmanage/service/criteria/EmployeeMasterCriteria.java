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
 * Criteria class for the {@link net.ccc.apps.campmanage.domain.EmployeeMaster} entity. This class is used
 * in {@link net.ccc.apps.campmanage.web.rest.EmployeeMasterResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /employee-masters?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class EmployeeMasterCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter badgeNo;

    private StringFilter employeeName;

    private StringFilter jobTitle;

    private StringFilter department;

    private StringFilter nationality;

    private StringFilter category;

    private StringFilter contractBase;

    private StringFilter band;

    private StringFilter eqvBand;

    private StringFilter project;

    private BooleanFilter isCcc;

    private StringFilter company;

    private StringFilter workLocation;

    private StringFilter messEntitlment;

    private StringFilter mealCategory;

    private StringFilter mealType;

    private StringFilter religion;

    private BooleanFilter employeeActive;

    private StringFilter inactiveReason;

    private StringFilter mobileNo;

    private StringFilter passportNo;

    private StringFilter qidNo;

    private StringFilter email;

    private StringFilter messCard;

    private StringFilter milkCard;

    private StringFilter createdBy;

    private LocalDateFilter createdAt;

    private StringFilter modifyBy;

    private LocalDateFilter modifyAt;

    private LongFilter refTradeId;

    private LongFilter refNationalityId;

    private LongFilter refProjectId;

    private LongFilter refEmployeeCompanyId;

    private LongFilter refMealCategoryId;

    private LongFilter refInactiveReasonId;

    private LongFilter bookingId;

    private Boolean distinct;

    public EmployeeMasterCriteria() {}

    public EmployeeMasterCriteria(EmployeeMasterCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.badgeNo = other.badgeNo == null ? null : other.badgeNo.copy();
        this.employeeName = other.employeeName == null ? null : other.employeeName.copy();
        this.jobTitle = other.jobTitle == null ? null : other.jobTitle.copy();
        this.department = other.department == null ? null : other.department.copy();
        this.nationality = other.nationality == null ? null : other.nationality.copy();
        this.category = other.category == null ? null : other.category.copy();
        this.contractBase = other.contractBase == null ? null : other.contractBase.copy();
        this.band = other.band == null ? null : other.band.copy();
        this.eqvBand = other.eqvBand == null ? null : other.eqvBand.copy();
        this.project = other.project == null ? null : other.project.copy();
        this.isCcc = other.isCcc == null ? null : other.isCcc.copy();
        this.company = other.company == null ? null : other.company.copy();
        this.workLocation = other.workLocation == null ? null : other.workLocation.copy();
        this.messEntitlment = other.messEntitlment == null ? null : other.messEntitlment.copy();
        this.mealCategory = other.mealCategory == null ? null : other.mealCategory.copy();
        this.mealType = other.mealType == null ? null : other.mealType.copy();
        this.religion = other.religion == null ? null : other.religion.copy();
        this.employeeActive = other.employeeActive == null ? null : other.employeeActive.copy();
        this.inactiveReason = other.inactiveReason == null ? null : other.inactiveReason.copy();
        this.mobileNo = other.mobileNo == null ? null : other.mobileNo.copy();
        this.passportNo = other.passportNo == null ? null : other.passportNo.copy();
        this.qidNo = other.qidNo == null ? null : other.qidNo.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.messCard = other.messCard == null ? null : other.messCard.copy();
        this.milkCard = other.milkCard == null ? null : other.milkCard.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.modifyBy = other.modifyBy == null ? null : other.modifyBy.copy();
        this.modifyAt = other.modifyAt == null ? null : other.modifyAt.copy();
        this.refTradeId = other.refTradeId == null ? null : other.refTradeId.copy();
        this.refNationalityId = other.refNationalityId == null ? null : other.refNationalityId.copy();
        this.refProjectId = other.refProjectId == null ? null : other.refProjectId.copy();
        this.refEmployeeCompanyId = other.refEmployeeCompanyId == null ? null : other.refEmployeeCompanyId.copy();
        this.refMealCategoryId = other.refMealCategoryId == null ? null : other.refMealCategoryId.copy();
        this.refInactiveReasonId = other.refInactiveReasonId == null ? null : other.refInactiveReasonId.copy();
        this.bookingId = other.bookingId == null ? null : other.bookingId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public EmployeeMasterCriteria copy() {
        return new EmployeeMasterCriteria(this);
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

    public StringFilter getEmployeeName() {
        return employeeName;
    }

    public StringFilter employeeName() {
        if (employeeName == null) {
            employeeName = new StringFilter();
        }
        return employeeName;
    }

    public void setEmployeeName(StringFilter employeeName) {
        this.employeeName = employeeName;
    }

    public StringFilter getJobTitle() {
        return jobTitle;
    }

    public StringFilter jobTitle() {
        if (jobTitle == null) {
            jobTitle = new StringFilter();
        }
        return jobTitle;
    }

    public void setJobTitle(StringFilter jobTitle) {
        this.jobTitle = jobTitle;
    }

    public StringFilter getDepartment() {
        return department;
    }

    public StringFilter department() {
        if (department == null) {
            department = new StringFilter();
        }
        return department;
    }

    public void setDepartment(StringFilter department) {
        this.department = department;
    }

    public StringFilter getNationality() {
        return nationality;
    }

    public StringFilter nationality() {
        if (nationality == null) {
            nationality = new StringFilter();
        }
        return nationality;
    }

    public void setNationality(StringFilter nationality) {
        this.nationality = nationality;
    }

    public StringFilter getCategory() {
        return category;
    }

    public StringFilter category() {
        if (category == null) {
            category = new StringFilter();
        }
        return category;
    }

    public void setCategory(StringFilter category) {
        this.category = category;
    }

    public StringFilter getContractBase() {
        return contractBase;
    }

    public StringFilter contractBase() {
        if (contractBase == null) {
            contractBase = new StringFilter();
        }
        return contractBase;
    }

    public void setContractBase(StringFilter contractBase) {
        this.contractBase = contractBase;
    }

    public StringFilter getBand() {
        return band;
    }

    public StringFilter band() {
        if (band == null) {
            band = new StringFilter();
        }
        return band;
    }

    public void setBand(StringFilter band) {
        this.band = band;
    }

    public StringFilter getEqvBand() {
        return eqvBand;
    }

    public StringFilter eqvBand() {
        if (eqvBand == null) {
            eqvBand = new StringFilter();
        }
        return eqvBand;
    }

    public void setEqvBand(StringFilter eqvBand) {
        this.eqvBand = eqvBand;
    }

    public StringFilter getProject() {
        return project;
    }

    public StringFilter project() {
        if (project == null) {
            project = new StringFilter();
        }
        return project;
    }

    public void setProject(StringFilter project) {
        this.project = project;
    }

    public BooleanFilter getIsCcc() {
        return isCcc;
    }

    public BooleanFilter isCcc() {
        if (isCcc == null) {
            isCcc = new BooleanFilter();
        }
        return isCcc;
    }

    public void setIsCcc(BooleanFilter isCcc) {
        this.isCcc = isCcc;
    }

    public StringFilter getCompany() {
        return company;
    }

    public StringFilter company() {
        if (company == null) {
            company = new StringFilter();
        }
        return company;
    }

    public void setCompany(StringFilter company) {
        this.company = company;
    }

    public StringFilter getWorkLocation() {
        return workLocation;
    }

    public StringFilter workLocation() {
        if (workLocation == null) {
            workLocation = new StringFilter();
        }
        return workLocation;
    }

    public void setWorkLocation(StringFilter workLocation) {
        this.workLocation = workLocation;
    }

    public StringFilter getMessEntitlment() {
        return messEntitlment;
    }

    public StringFilter messEntitlment() {
        if (messEntitlment == null) {
            messEntitlment = new StringFilter();
        }
        return messEntitlment;
    }

    public void setMessEntitlment(StringFilter messEntitlment) {
        this.messEntitlment = messEntitlment;
    }

    public StringFilter getMealCategory() {
        return mealCategory;
    }

    public StringFilter mealCategory() {
        if (mealCategory == null) {
            mealCategory = new StringFilter();
        }
        return mealCategory;
    }

    public void setMealCategory(StringFilter mealCategory) {
        this.mealCategory = mealCategory;
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

    public StringFilter getReligion() {
        return religion;
    }

    public StringFilter religion() {
        if (religion == null) {
            religion = new StringFilter();
        }
        return religion;
    }

    public void setReligion(StringFilter religion) {
        this.religion = religion;
    }

    public BooleanFilter getEmployeeActive() {
        return employeeActive;
    }

    public BooleanFilter employeeActive() {
        if (employeeActive == null) {
            employeeActive = new BooleanFilter();
        }
        return employeeActive;
    }

    public void setEmployeeActive(BooleanFilter employeeActive) {
        this.employeeActive = employeeActive;
    }

    public StringFilter getInactiveReason() {
        return inactiveReason;
    }

    public StringFilter inactiveReason() {
        if (inactiveReason == null) {
            inactiveReason = new StringFilter();
        }
        return inactiveReason;
    }

    public void setInactiveReason(StringFilter inactiveReason) {
        this.inactiveReason = inactiveReason;
    }

    public StringFilter getMobileNo() {
        return mobileNo;
    }

    public StringFilter mobileNo() {
        if (mobileNo == null) {
            mobileNo = new StringFilter();
        }
        return mobileNo;
    }

    public void setMobileNo(StringFilter mobileNo) {
        this.mobileNo = mobileNo;
    }

    public StringFilter getPassportNo() {
        return passportNo;
    }

    public StringFilter passportNo() {
        if (passportNo == null) {
            passportNo = new StringFilter();
        }
        return passportNo;
    }

    public void setPassportNo(StringFilter passportNo) {
        this.passportNo = passportNo;
    }

    public StringFilter getQidNo() {
        return qidNo;
    }

    public StringFilter qidNo() {
        if (qidNo == null) {
            qidNo = new StringFilter();
        }
        return qidNo;
    }

    public void setQidNo(StringFilter qidNo) {
        this.qidNo = qidNo;
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

    public StringFilter getMessCard() {
        return messCard;
    }

    public StringFilter messCard() {
        if (messCard == null) {
            messCard = new StringFilter();
        }
        return messCard;
    }

    public void setMessCard(StringFilter messCard) {
        this.messCard = messCard;
    }

    public StringFilter getMilkCard() {
        return milkCard;
    }

    public StringFilter milkCard() {
        if (milkCard == null) {
            milkCard = new StringFilter();
        }
        return milkCard;
    }

    public void setMilkCard(StringFilter milkCard) {
        this.milkCard = milkCard;
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

    public LongFilter getRefTradeId() {
        return refTradeId;
    }

    public LongFilter refTradeId() {
        if (refTradeId == null) {
            refTradeId = new LongFilter();
        }
        return refTradeId;
    }

    public void setRefTradeId(LongFilter refTradeId) {
        this.refTradeId = refTradeId;
    }

    public LongFilter getRefNationalityId() {
        return refNationalityId;
    }

    public LongFilter refNationalityId() {
        if (refNationalityId == null) {
            refNationalityId = new LongFilter();
        }
        return refNationalityId;
    }

    public void setRefNationalityId(LongFilter refNationalityId) {
        this.refNationalityId = refNationalityId;
    }

    public LongFilter getRefProjectId() {
        return refProjectId;
    }

    public LongFilter refProjectId() {
        if (refProjectId == null) {
            refProjectId = new LongFilter();
        }
        return refProjectId;
    }

    public void setRefProjectId(LongFilter refProjectId) {
        this.refProjectId = refProjectId;
    }

    public LongFilter getRefEmployeeCompanyId() {
        return refEmployeeCompanyId;
    }

    public LongFilter refEmployeeCompanyId() {
        if (refEmployeeCompanyId == null) {
            refEmployeeCompanyId = new LongFilter();
        }
        return refEmployeeCompanyId;
    }

    public void setRefEmployeeCompanyId(LongFilter refEmployeeCompanyId) {
        this.refEmployeeCompanyId = refEmployeeCompanyId;
    }

    public LongFilter getRefMealCategoryId() {
        return refMealCategoryId;
    }

    public LongFilter refMealCategoryId() {
        if (refMealCategoryId == null) {
            refMealCategoryId = new LongFilter();
        }
        return refMealCategoryId;
    }

    public void setRefMealCategoryId(LongFilter refMealCategoryId) {
        this.refMealCategoryId = refMealCategoryId;
    }

    public LongFilter getRefInactiveReasonId() {
        return refInactiveReasonId;
    }

    public LongFilter refInactiveReasonId() {
        if (refInactiveReasonId == null) {
            refInactiveReasonId = new LongFilter();
        }
        return refInactiveReasonId;
    }

    public void setRefInactiveReasonId(LongFilter refInactiveReasonId) {
        this.refInactiveReasonId = refInactiveReasonId;
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
        final EmployeeMasterCriteria that = (EmployeeMasterCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(badgeNo, that.badgeNo) &&
            Objects.equals(employeeName, that.employeeName) &&
            Objects.equals(jobTitle, that.jobTitle) &&
            Objects.equals(department, that.department) &&
            Objects.equals(nationality, that.nationality) &&
            Objects.equals(category, that.category) &&
            Objects.equals(contractBase, that.contractBase) &&
            Objects.equals(band, that.band) &&
            Objects.equals(eqvBand, that.eqvBand) &&
            Objects.equals(project, that.project) &&
            Objects.equals(isCcc, that.isCcc) &&
            Objects.equals(company, that.company) &&
            Objects.equals(workLocation, that.workLocation) &&
            Objects.equals(messEntitlment, that.messEntitlment) &&
            Objects.equals(mealCategory, that.mealCategory) &&
            Objects.equals(mealType, that.mealType) &&
            Objects.equals(religion, that.religion) &&
            Objects.equals(employeeActive, that.employeeActive) &&
            Objects.equals(inactiveReason, that.inactiveReason) &&
            Objects.equals(mobileNo, that.mobileNo) &&
            Objects.equals(passportNo, that.passportNo) &&
            Objects.equals(qidNo, that.qidNo) &&
            Objects.equals(email, that.email) &&
            Objects.equals(messCard, that.messCard) &&
            Objects.equals(milkCard, that.milkCard) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(modifyBy, that.modifyBy) &&
            Objects.equals(modifyAt, that.modifyAt) &&
            Objects.equals(refTradeId, that.refTradeId) &&
            Objects.equals(refNationalityId, that.refNationalityId) &&
            Objects.equals(refProjectId, that.refProjectId) &&
            Objects.equals(refEmployeeCompanyId, that.refEmployeeCompanyId) &&
            Objects.equals(refMealCategoryId, that.refMealCategoryId) &&
            Objects.equals(refInactiveReasonId, that.refInactiveReasonId) &&
            Objects.equals(bookingId, that.bookingId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            badgeNo,
            employeeName,
            jobTitle,
            department,
            nationality,
            category,
            contractBase,
            band,
            eqvBand,
            project,
            isCcc,
            company,
            workLocation,
            messEntitlment,
            mealCategory,
            mealType,
            religion,
            employeeActive,
            inactiveReason,
            mobileNo,
            passportNo,
            qidNo,
            email,
            messCard,
            milkCard,
            createdBy,
            createdAt,
            modifyBy,
            modifyAt,
            refTradeId,
            refNationalityId,
            refProjectId,
            refEmployeeCompanyId,
            refMealCategoryId,
            refInactiveReasonId,
            bookingId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeMasterCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (badgeNo != null ? "badgeNo=" + badgeNo + ", " : "") +
            (employeeName != null ? "employeeName=" + employeeName + ", " : "") +
            (jobTitle != null ? "jobTitle=" + jobTitle + ", " : "") +
            (department != null ? "department=" + department + ", " : "") +
            (nationality != null ? "nationality=" + nationality + ", " : "") +
            (category != null ? "category=" + category + ", " : "") +
            (contractBase != null ? "contractBase=" + contractBase + ", " : "") +
            (band != null ? "band=" + band + ", " : "") +
            (eqvBand != null ? "eqvBand=" + eqvBand + ", " : "") +
            (project != null ? "project=" + project + ", " : "") +
            (isCcc != null ? "isCcc=" + isCcc + ", " : "") +
            (company != null ? "company=" + company + ", " : "") +
            (workLocation != null ? "workLocation=" + workLocation + ", " : "") +
            (messEntitlment != null ? "messEntitlment=" + messEntitlment + ", " : "") +
            (mealCategory != null ? "mealCategory=" + mealCategory + ", " : "") +
            (mealType != null ? "mealType=" + mealType + ", " : "") +
            (religion != null ? "religion=" + religion + ", " : "") +
            (employeeActive != null ? "employeeActive=" + employeeActive + ", " : "") +
            (inactiveReason != null ? "inactiveReason=" + inactiveReason + ", " : "") +
            (mobileNo != null ? "mobileNo=" + mobileNo + ", " : "") +
            (passportNo != null ? "passportNo=" + passportNo + ", " : "") +
            (qidNo != null ? "qidNo=" + qidNo + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (messCard != null ? "messCard=" + messCard + ", " : "") +
            (milkCard != null ? "milkCard=" + milkCard + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
            (modifyBy != null ? "modifyBy=" + modifyBy + ", " : "") +
            (modifyAt != null ? "modifyAt=" + modifyAt + ", " : "") +
            (refTradeId != null ? "refTradeId=" + refTradeId + ", " : "") +
            (refNationalityId != null ? "refNationalityId=" + refNationalityId + ", " : "") +
            (refProjectId != null ? "refProjectId=" + refProjectId + ", " : "") +
            (refEmployeeCompanyId != null ? "refEmployeeCompanyId=" + refEmployeeCompanyId + ", " : "") +
            (refMealCategoryId != null ? "refMealCategoryId=" + refMealCategoryId + ", " : "") +
            (refInactiveReasonId != null ? "refInactiveReasonId=" + refInactiveReasonId + ", " : "") +
            (bookingId != null ? "bookingId=" + bookingId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
