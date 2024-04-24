package net.ccc.apps.campmanage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Catering.
 */
@Entity
@Table(name = "catering")
public class Catering implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "catering_name")
    private String cateringName;

    @Column(name = "location")
    private String location;

    @Column(name = "mess_category")
    private String messCategory;

    @Column(name = "meal_type")
    private String mealType;

    @Column(name = "menu_type")
    private String menuType;

    @Column(name = "rate")
    private Float rate;

    @Column(name = "upgraded_rate")
    private Float upgradedRate;

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

    @JsonIgnoreProperties(value = { "catering", "bedDetails", "roomAdvanceBookings", "bookings", "camp" }, allowSetters = true)
    @OneToOne(mappedBy = "catering")
    private RoomDetails roomDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Catering id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCateringName() {
        return this.cateringName;
    }

    public Catering cateringName(String cateringName) {
        this.setCateringName(cateringName);
        return this;
    }

    public void setCateringName(String cateringName) {
        this.cateringName = cateringName;
    }

    public String getLocation() {
        return this.location;
    }

    public Catering location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessCategory() {
        return this.messCategory;
    }

    public Catering messCategory(String messCategory) {
        this.setMessCategory(messCategory);
        return this;
    }

    public void setMessCategory(String messCategory) {
        this.messCategory = messCategory;
    }

    public String getMealType() {
        return this.mealType;
    }

    public Catering mealType(String mealType) {
        this.setMealType(mealType);
        return this;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMenuType() {
        return this.menuType;
    }

    public Catering menuType(String menuType) {
        this.setMenuType(menuType);
        return this;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public Float getRate() {
        return this.rate;
    }

    public Catering rate(Float rate) {
        this.setRate(rate);
        return this;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Float getUpgradedRate() {
        return this.upgradedRate;
    }

    public Catering upgradedRate(Float upgradedRate) {
        this.setUpgradedRate(upgradedRate);
        return this;
    }

    public void setUpgradedRate(Float upgradedRate) {
        this.upgradedRate = upgradedRate;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Catering remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Catering createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public Catering createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifyBy() {
        return this.modifyBy;
    }

    public Catering modifyBy(String modifyBy) {
        this.setModifyBy(modifyBy);
        return this;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public LocalDate getModifyAt() {
        return this.modifyAt;
    }

    public Catering modifyAt(LocalDate modifyAt) {
        this.setModifyAt(modifyAt);
        return this;
    }

    public void setModifyAt(LocalDate modifyAt) {
        this.modifyAt = modifyAt;
    }

    public RoomDetails getRoomDetails() {
        return this.roomDetails;
    }

    public void setRoomDetails(RoomDetails roomDetails) {
        if (this.roomDetails != null) {
            this.roomDetails.setCatering(null);
        }
        if (roomDetails != null) {
            roomDetails.setCatering(this);
        }
        this.roomDetails = roomDetails;
    }

    public Catering roomDetails(RoomDetails roomDetails) {
        this.setRoomDetails(roomDetails);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Catering)) {
            return false;
        }
        return id != null && id.equals(((Catering) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Catering{" +
            "id=" + getId() +
            ", cateringName='" + getCateringName() + "'" +
            ", location='" + getLocation() + "'" +
            ", messCategory='" + getMessCategory() + "'" +
            ", mealType='" + getMealType() + "'" +
            ", menuType='" + getMenuType() + "'" +
            ", rate=" + getRate() +
            ", upgradedRate=" + getUpgradedRate() +
            ", remarks='" + getRemarks() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", modifyBy='" + getModifyBy() + "'" +
            ", modifyAt='" + getModifyAt() + "'" +
            "}";
    }
}
