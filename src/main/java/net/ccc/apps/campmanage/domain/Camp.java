package net.ccc.apps.campmanage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Camp.
 */
@Entity
@Table(name = "camp")
public class Camp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "camp_name")
    private String campName;

    @Column(name = "location")
    private String location;

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

    @OneToMany(mappedBy = "camp")
    @JsonIgnoreProperties(value = { "catering", "bedDetails", "roomAdvanceBookings", "bookings", "camp" }, allowSetters = true)
    private Set<RoomDetails> roomDetails = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "camps", "services" }, allowSetters = true)
    private Company company;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Camp id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampName() {
        return this.campName;
    }

    public Camp campName(String campName) {
        this.setCampName(campName);
        return this;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getLocation() {
        return this.location;
    }

    public Camp location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Camp remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Camp createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public Camp createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifyBy() {
        return this.modifyBy;
    }

    public Camp modifyBy(String modifyBy) {
        this.setModifyBy(modifyBy);
        return this;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public LocalDate getModifyAt() {
        return this.modifyAt;
    }

    public Camp modifyAt(LocalDate modifyAt) {
        this.setModifyAt(modifyAt);
        return this;
    }

    public void setModifyAt(LocalDate modifyAt) {
        this.modifyAt = modifyAt;
    }

    public Set<RoomDetails> getRoomDetails() {
        return this.roomDetails;
    }

    public void setRoomDetails(Set<RoomDetails> roomDetails) {
        if (this.roomDetails != null) {
            this.roomDetails.forEach(i -> i.setCamp(null));
        }
        if (roomDetails != null) {
            roomDetails.forEach(i -> i.setCamp(this));
        }
        this.roomDetails = roomDetails;
    }

    public Camp roomDetails(Set<RoomDetails> roomDetails) {
        this.setRoomDetails(roomDetails);
        return this;
    }

    public Camp addRoomDetails(RoomDetails roomDetails) {
        this.roomDetails.add(roomDetails);
        roomDetails.setCamp(this);
        return this;
    }

    public Camp removeRoomDetails(RoomDetails roomDetails) {
        this.roomDetails.remove(roomDetails);
        roomDetails.setCamp(null);
        return this;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Camp company(Company company) {
        this.setCompany(company);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Camp)) {
            return false;
        }
        return id != null && id.equals(((Camp) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Camp{" +
            "id=" + getId() +
            ", campName='" + getCampName() + "'" +
            ", location='" + getLocation() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", modifyBy='" + getModifyBy() + "'" +
            ", modifyAt='" + getModifyAt() + "'" +
            "}";
    }
}
