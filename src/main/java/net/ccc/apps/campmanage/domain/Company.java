package net.ccc.apps.campmanage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "location")
    private String location;

    @Column(name = "services_provided")
    private String servicesProvided;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "modify_by")
    private String modifyBy;

    @Column(name = "modify_at")
    private LocalDate modifyAt;

    @OneToMany(mappedBy = "company")
    @JsonIgnoreProperties(value = { "roomDetails", "company" }, allowSetters = true)
    private Set<Camp> camps = new HashSet<>();

    @OneToMany(mappedBy = "company")
    @JsonIgnoreProperties(value = { "company" }, allowSetters = true)
    private Set<Services> services = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Company id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public Company companyName(String companyName) {
        this.setCompanyName(companyName);
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return this.location;
    }

    public Company location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServicesProvided() {
        return this.servicesProvided;
    }

    public Company servicesProvided(String servicesProvided) {
        this.setServicesProvided(servicesProvided);
        return this;
    }

    public void setServicesProvided(String servicesProvided) {
        this.servicesProvided = servicesProvided;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }

    public Company contactPerson(String contactPerson) {
        this.setContactPerson(contactPerson);
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public Company contactNo(String contactNo) {
        this.setContactNo(contactNo);
        return this;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public Company contactEmail(String contactEmail) {
        this.setContactEmail(contactEmail);
        return this;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Company remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Company createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public Company createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifyBy() {
        return this.modifyBy;
    }

    public Company modifyBy(String modifyBy) {
        this.setModifyBy(modifyBy);
        return this;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public LocalDate getModifyAt() {
        return this.modifyAt;
    }

    public Company modifyAt(LocalDate modifyAt) {
        this.setModifyAt(modifyAt);
        return this;
    }

    public void setModifyAt(LocalDate modifyAt) {
        this.modifyAt = modifyAt;
    }

    public Set<Camp> getCamps() {
        return this.camps;
    }

    public void setCamps(Set<Camp> camps) {
        if (this.camps != null) {
            this.camps.forEach(i -> i.setCompany(null));
        }
        if (camps != null) {
            camps.forEach(i -> i.setCompany(this));
        }
        this.camps = camps;
    }

    public Company camps(Set<Camp> camps) {
        this.setCamps(camps);
        return this;
    }

    public Company addCamp(Camp camp) {
        this.camps.add(camp);
        camp.setCompany(this);
        return this;
    }

    public Company removeCamp(Camp camp) {
        this.camps.remove(camp);
        camp.setCompany(null);
        return this;
    }

    public Set<Services> getServices() {
        return this.services;
    }

    public void setServices(Set<Services> services) {
        if (this.services != null) {
            this.services.forEach(i -> i.setCompany(null));
        }
        if (services != null) {
            services.forEach(i -> i.setCompany(this));
        }
        this.services = services;
    }

    public Company services(Set<Services> services) {
        this.setServices(services);
        return this;
    }

    public Company addServices(Services services) {
        this.services.add(services);
        services.setCompany(this);
        return this;
    }

    public Company removeServices(Services services) {
        this.services.remove(services);
        services.setCompany(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }
        return id != null && id.equals(((Company) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Company{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", location='" + getLocation() + "'" +
            ", servicesProvided='" + getServicesProvided() + "'" +
            ", contactPerson='" + getContactPerson() + "'" +
            ", contactNo='" + getContactNo() + "'" +
            ", contactEmail='" + getContactEmail() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", modifyBy='" + getModifyBy() + "'" +
            ", modifyAt='" + getModifyAt() + "'" +
            "}";
    }
}
