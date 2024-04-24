package net.ccc.apps.campmanage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A Booking.
 */
@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "badge_no")
    private String badgeNo;

    @Column(name = "room_id")
    private String roomId;

    @Column(name = "check_in_date")
    private LocalDate checkInDate;

    @Column(name = "check_out_date")
    private LocalDate checkOutDate;

    @Column(name = "guest_status")
    private String guestStatus;

    @Column(name = "leave_start_date")
    private LocalDate leaveStartDate;

    @Column(name = "leave_end_date")
    private LocalDate leaveEndDate;

    @Column(name = "bed_no")
    private String bedNo;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "docuploaded")
    private Boolean docuploaded;

    @ManyToOne
    @JsonIgnoreProperties(value = { "catering", "bedDetails", "roomAdvanceBookings", "bookings", "camp" }, allowSetters = true)
    private RoomDetails roomDetails;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "refTrade", "refNationality", "refProject", "refEmployeeCompany", "refMealCategory", "refInactiveReason", "bookings" },
        allowSetters = true
    )
    private EmployeeMaster employeeMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Booking id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBadgeNo() {
        return this.badgeNo;
    }

    public Booking badgeNo(String badgeNo) {
        this.setBadgeNo(badgeNo);
        return this;
    }

    public void setBadgeNo(String badgeNo) {
        this.badgeNo = badgeNo;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public Booking roomId(String roomId) {
        this.setRoomId(roomId);
        return this;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckInDate() {
        return this.checkInDate;
    }

    public Booking checkInDate(LocalDate checkInDate) {
        this.setCheckInDate(checkInDate);
        return this;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return this.checkOutDate;
    }

    public Booking checkOutDate(LocalDate checkOutDate) {
        this.setCheckOutDate(checkOutDate);
        return this;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getGuestStatus() {
        return this.guestStatus;
    }

    public Booking guestStatus(String guestStatus) {
        this.setGuestStatus(guestStatus);
        return this;
    }

    public void setGuestStatus(String guestStatus) {
        this.guestStatus = guestStatus;
    }

    public LocalDate getLeaveStartDate() {
        return this.leaveStartDate;
    }

    public Booking leaveStartDate(LocalDate leaveStartDate) {
        this.setLeaveStartDate(leaveStartDate);
        return this;
    }

    public void setLeaveStartDate(LocalDate leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public LocalDate getLeaveEndDate() {
        return this.leaveEndDate;
    }

    public Booking leaveEndDate(LocalDate leaveEndDate) {
        this.setLeaveEndDate(leaveEndDate);
        return this;
    }

    public void setLeaveEndDate(LocalDate leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    public String getBedNo() {
        return this.bedNo;
    }

    public Booking bedNo(String bedNo) {
        this.setBedNo(bedNo);
        return this;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Booking remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getDocuploaded() {
        return this.docuploaded;
    }

    public Booking docuploaded(Boolean docuploaded) {
        this.setDocuploaded(docuploaded);
        return this;
    }

    public void setDocuploaded(Boolean docuploaded) {
        this.docuploaded = docuploaded;
    }

    public RoomDetails getRoomDetails() {
        return this.roomDetails;
    }

    public void setRoomDetails(RoomDetails roomDetails) {
        this.roomDetails = roomDetails;
    }

    public Booking roomDetails(RoomDetails roomDetails) {
        this.setRoomDetails(roomDetails);
        return this;
    }

    public EmployeeMaster getEmployeeMaster() {
        return this.employeeMaster;
    }

    public void setEmployeeMaster(EmployeeMaster employeeMaster) {
        this.employeeMaster = employeeMaster;
    }

    public Booking employeeMaster(EmployeeMaster employeeMaster) {
        this.setEmployeeMaster(employeeMaster);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Booking)) {
            return false;
        }
        return id != null && id.equals(((Booking) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Booking{" +
            "id=" + getId() +
            ", badgeNo='" + getBadgeNo() + "'" +
            ", roomId='" + getRoomId() + "'" +
            ", checkInDate='" + getCheckInDate() + "'" +
            ", checkOutDate='" + getCheckOutDate() + "'" +
            ", guestStatus='" + getGuestStatus() + "'" +
            ", leaveStartDate='" + getLeaveStartDate() + "'" +
            ", leaveEndDate='" + getLeaveEndDate() + "'" +
            ", bedNo='" + getBedNo() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", docuploaded='" + getDocuploaded() + "'" +
            "}";
    }
}
