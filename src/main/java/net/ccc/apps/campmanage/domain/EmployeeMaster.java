package net.ccc.apps.campmanage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A EmployeeMaster.
 */
@Entity
@Table(name = "employee_master")
public class EmployeeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "badge_no")
    private String badgeNo;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "department")
    private String department;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "category")
    private String category;

    @Column(name = "contract_base")
    private String contractBase;

    @Column(name = "band")
    private String band;

    @Column(name = "eqv_band")
    private String eqvBand;

    @Column(name = "project")
    private String project;

    @Column(name = "is_ccc")
    private Boolean isCcc;

    @Column(name = "company")
    private String company;

    @Column(name = "work_location")
    private String workLocation;

    @Column(name = "mess_entitlment")
    private String messEntitlment;

    @Column(name = "meal_category")
    private String mealCategory;

    @Column(name = "meal_type")
    private String mealType;

    @Column(name = "religion")
    private String religion;

    @Column(name = "employee_active")
    private Boolean employeeActive;

    @Column(name = "inactive_reason")
    private String inactiveReason;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "passport_no")
    private String passportNo;

    @Column(name = "qid_no")
    private String qidNo;

    @Column(name = "email")
    private String email;

    @Column(name = "mess_card")
    private String messCard;

    @Column(name = "milk_card")
    private String milkCard;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "modify_by")
    private String modifyBy;

    @Column(name = "modify_at")
    private LocalDate modifyAt;

    @JsonIgnoreProperties(value = { "employeeMaster" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private RefTrade refTrade;

    @JsonIgnoreProperties(value = { "employeeMaster" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private RefNationality refNationality;

    @JsonIgnoreProperties(value = { "employeeMaster" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private RefProject refProject;

    @JsonIgnoreProperties(value = { "employeeMaster" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private RefEmployeeCompany refEmployeeCompany;

    @JsonIgnoreProperties(value = { "employeeMaster" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private RefMealCategory refMealCategory;

    @JsonIgnoreProperties(value = { "employeeMaster" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private RefInactiveReason refInactiveReason;

    @OneToMany(mappedBy = "employeeMaster")
    @JsonIgnoreProperties(value = { "roomDetails", "employeeMaster" }, allowSetters = true)
    private Set<Booking> bookings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EmployeeMaster id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBadgeNo() {
        return this.badgeNo;
    }

    public EmployeeMaster badgeNo(String badgeNo) {
        this.setBadgeNo(badgeNo);
        return this;
    }

    public void setBadgeNo(String badgeNo) {
        this.badgeNo = badgeNo;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public EmployeeMaster employeeName(String employeeName) {
        this.setEmployeeName(employeeName);
        return this;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public EmployeeMaster jobTitle(String jobTitle) {
        this.setJobTitle(jobTitle);
        return this;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return this.department;
    }

    public EmployeeMaster department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNationality() {
        return this.nationality;
    }

    public EmployeeMaster nationality(String nationality) {
        this.setNationality(nationality);
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCategory() {
        return this.category;
    }

    public EmployeeMaster category(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContractBase() {
        return this.contractBase;
    }

    public EmployeeMaster contractBase(String contractBase) {
        this.setContractBase(contractBase);
        return this;
    }

    public void setContractBase(String contractBase) {
        this.contractBase = contractBase;
    }

    public String getBand() {
        return this.band;
    }

    public EmployeeMaster band(String band) {
        this.setBand(band);
        return this;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getEqvBand() {
        return this.eqvBand;
    }

    public EmployeeMaster eqvBand(String eqvBand) {
        this.setEqvBand(eqvBand);
        return this;
    }

    public void setEqvBand(String eqvBand) {
        this.eqvBand = eqvBand;
    }

    public String getProject() {
        return this.project;
    }

    public EmployeeMaster project(String project) {
        this.setProject(project);
        return this;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Boolean getIsCcc() {
        return this.isCcc;
    }

    public EmployeeMaster isCcc(Boolean isCcc) {
        this.setIsCcc(isCcc);
        return this;
    }

    public void setIsCcc(Boolean isCcc) {
        this.isCcc = isCcc;
    }

    public String getCompany() {
        return this.company;
    }

    public EmployeeMaster company(String company) {
        this.setCompany(company);
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWorkLocation() {
        return this.workLocation;
    }

    public EmployeeMaster workLocation(String workLocation) {
        this.setWorkLocation(workLocation);
        return this;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getMessEntitlment() {
        return this.messEntitlment;
    }

    public EmployeeMaster messEntitlment(String messEntitlment) {
        this.setMessEntitlment(messEntitlment);
        return this;
    }

    public void setMessEntitlment(String messEntitlment) {
        this.messEntitlment = messEntitlment;
    }

    public String getMealCategory() {
        return this.mealCategory;
    }

    public EmployeeMaster mealCategory(String mealCategory) {
        this.setMealCategory(mealCategory);
        return this;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
    }

    public String getMealType() {
        return this.mealType;
    }

    public EmployeeMaster mealType(String mealType) {
        this.setMealType(mealType);
        return this;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getReligion() {
        return this.religion;
    }

    public EmployeeMaster religion(String religion) {
        this.setReligion(religion);
        return this;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Boolean getEmployeeActive() {
        return this.employeeActive;
    }

    public EmployeeMaster employeeActive(Boolean employeeActive) {
        this.setEmployeeActive(employeeActive);
        return this;
    }

    public void setEmployeeActive(Boolean employeeActive) {
        this.employeeActive = employeeActive;
    }

    public String getInactiveReason() {
        return this.inactiveReason;
    }

    public EmployeeMaster inactiveReason(String inactiveReason) {
        this.setInactiveReason(inactiveReason);
        return this;
    }

    public void setInactiveReason(String inactiveReason) {
        this.inactiveReason = inactiveReason;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public EmployeeMaster mobileNo(String mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassportNo() {
        return this.passportNo;
    }

    public EmployeeMaster passportNo(String passportNo) {
        this.setPassportNo(passportNo);
        return this;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getQidNo() {
        return this.qidNo;
    }

    public EmployeeMaster qidNo(String qidNo) {
        this.setQidNo(qidNo);
        return this;
    }

    public void setQidNo(String qidNo) {
        this.qidNo = qidNo;
    }

    public String getEmail() {
        return this.email;
    }

    public EmployeeMaster email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessCard() {
        return this.messCard;
    }

    public EmployeeMaster messCard(String messCard) {
        this.setMessCard(messCard);
        return this;
    }

    public void setMessCard(String messCard) {
        this.messCard = messCard;
    }

    public String getMilkCard() {
        return this.milkCard;
    }

    public EmployeeMaster milkCard(String milkCard) {
        this.setMilkCard(milkCard);
        return this;
    }

    public void setMilkCard(String milkCard) {
        this.milkCard = milkCard;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public EmployeeMaster createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public EmployeeMaster createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifyBy() {
        return this.modifyBy;
    }

    public EmployeeMaster modifyBy(String modifyBy) {
        this.setModifyBy(modifyBy);
        return this;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public LocalDate getModifyAt() {
        return this.modifyAt;
    }

    public EmployeeMaster modifyAt(LocalDate modifyAt) {
        this.setModifyAt(modifyAt);
        return this;
    }

    public void setModifyAt(LocalDate modifyAt) {
        this.modifyAt = modifyAt;
    }

    public RefTrade getRefTrade() {
        return this.refTrade;
    }

    public void setRefTrade(RefTrade refTrade) {
        this.refTrade = refTrade;
    }

    public EmployeeMaster refTrade(RefTrade refTrade) {
        this.setRefTrade(refTrade);
        return this;
    }

    public RefNationality getRefNationality() {
        return this.refNationality;
    }

    public void setRefNationality(RefNationality refNationality) {
        this.refNationality = refNationality;
    }

    public EmployeeMaster refNationality(RefNationality refNationality) {
        this.setRefNationality(refNationality);
        return this;
    }

    public RefProject getRefProject() {
        return this.refProject;
    }

    public void setRefProject(RefProject refProject) {
        this.refProject = refProject;
    }

    public EmployeeMaster refProject(RefProject refProject) {
        this.setRefProject(refProject);
        return this;
    }

    public RefEmployeeCompany getRefEmployeeCompany() {
        return this.refEmployeeCompany;
    }

    public void setRefEmployeeCompany(RefEmployeeCompany refEmployeeCompany) {
        this.refEmployeeCompany = refEmployeeCompany;
    }

    public EmployeeMaster refEmployeeCompany(RefEmployeeCompany refEmployeeCompany) {
        this.setRefEmployeeCompany(refEmployeeCompany);
        return this;
    }

    public RefMealCategory getRefMealCategory() {
        return this.refMealCategory;
    }

    public void setRefMealCategory(RefMealCategory refMealCategory) {
        this.refMealCategory = refMealCategory;
    }

    public EmployeeMaster refMealCategory(RefMealCategory refMealCategory) {
        this.setRefMealCategory(refMealCategory);
        return this;
    }

    public RefInactiveReason getRefInactiveReason() {
        return this.refInactiveReason;
    }

    public void setRefInactiveReason(RefInactiveReason refInactiveReason) {
        this.refInactiveReason = refInactiveReason;
    }

    public EmployeeMaster refInactiveReason(RefInactiveReason refInactiveReason) {
        this.setRefInactiveReason(refInactiveReason);
        return this;
    }

    public Set<Booking> getBookings() {
        return this.bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        if (this.bookings != null) {
            this.bookings.forEach(i -> i.setEmployeeMaster(null));
        }
        if (bookings != null) {
            bookings.forEach(i -> i.setEmployeeMaster(this));
        }
        this.bookings = bookings;
    }

    public EmployeeMaster bookings(Set<Booking> bookings) {
        this.setBookings(bookings);
        return this;
    }

    public EmployeeMaster addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setEmployeeMaster(this);
        return this;
    }

    public EmployeeMaster removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setEmployeeMaster(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeMaster)) {
            return false;
        }
        return id != null && id.equals(((EmployeeMaster) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeMaster{" +
            "id=" + getId() +
            ", badgeNo='" + getBadgeNo() + "'" +
            ", employeeName='" + getEmployeeName() + "'" +
            ", jobTitle='" + getJobTitle() + "'" +
            ", department='" + getDepartment() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", category='" + getCategory() + "'" +
            ", contractBase='" + getContractBase() + "'" +
            ", band='" + getBand() + "'" +
            ", eqvBand='" + getEqvBand() + "'" +
            ", project='" + getProject() + "'" +
            ", isCcc='" + getIsCcc() + "'" +
            ", company='" + getCompany() + "'" +
            ", workLocation='" + getWorkLocation() + "'" +
            ", messEntitlment='" + getMessEntitlment() + "'" +
            ", mealCategory='" + getMealCategory() + "'" +
            ", mealType='" + getMealType() + "'" +
            ", religion='" + getReligion() + "'" +
            ", employeeActive='" + getEmployeeActive() + "'" +
            ", inactiveReason='" + getInactiveReason() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", passportNo='" + getPassportNo() + "'" +
            ", qidNo='" + getQidNo() + "'" +
            ", email='" + getEmail() + "'" +
            ", messCard='" + getMessCard() + "'" +
            ", milkCard='" + getMilkCard() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", modifyBy='" + getModifyBy() + "'" +
            ", modifyAt='" + getModifyAt() + "'" +
            "}";
    }
}
