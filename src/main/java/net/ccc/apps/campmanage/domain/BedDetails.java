package net.ccc.apps.campmanage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A BedDetails.
 */
@Entity
@Table(name = "bed_details")
public class BedDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "bed_no")
    private String bedNo;

    @Column(name = "bed_status")
    private String bedStatus;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne
    @JsonIgnoreProperties(value = { "catering", "bedDetails", "roomAdvanceBookings", "bookings", "camp" }, allowSetters = true)
    private RoomDetails roomDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BedDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBedNo() {
        return this.bedNo;
    }

    public BedDetails bedNo(String bedNo) {
        this.setBedNo(bedNo);
        return this;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getBedStatus() {
        return this.bedStatus;
    }

    public BedDetails bedStatus(String bedStatus) {
        this.setBedStatus(bedStatus);
        return this;
    }

    public void setBedStatus(String bedStatus) {
        this.bedStatus = bedStatus;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public BedDetails remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public RoomDetails getRoomDetails() {
        return this.roomDetails;
    }

    public void setRoomDetails(RoomDetails roomDetails) {
        this.roomDetails = roomDetails;
    }

    public BedDetails roomDetails(RoomDetails roomDetails) {
        this.setRoomDetails(roomDetails);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BedDetails)) {
            return false;
        }
        return id != null && id.equals(((BedDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BedDetails{" +
            "id=" + getId() +
            ", bedNo='" + getBedNo() + "'" +
            ", bedStatus='" + getBedStatus() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
