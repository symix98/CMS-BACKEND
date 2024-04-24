package net.ccc.apps.campmanage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A RoomDetails.
 */
@Entity
@Table(name = "room_details")
public class RoomDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "room_description")
    private String roomDescription;

    @Column(name = "block")
    private String block;

    @Column(name = "floor")
    private String floor;

    @Column(name = "room_category")
    private String roomCategory;

    @Column(name = "bed_count")
    private Integer bedCount;

    @Column(name = "room_no")
    private String roomNo;

    @Column(name = "room_allocation")
    private String roomAllocation;

    @Column(name = "room_configuration")
    private String roomConfiguration;

    @Column(name = "available_from")
    private LocalDate availableFrom;

    @Column(name = "room_status")
    private String roomStatus;

    @Column(name = "bed_only")
    private Boolean bedOnly;

    @Column(name = "monthly_rate")
    private Float monthlyRate;

    @Column(name = "daily_rate")
    private Float dailyRate;

    @Column(name = "bed_rate")
    private Float bedRate;

    @Column(name = "reservation_rate")
    private Float reservationRate;

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

    @JsonIgnoreProperties(value = { "roomDetails" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Catering catering;

    @OneToMany(mappedBy = "roomDetails")
    @JsonIgnoreProperties(value = { "roomDetails" }, allowSetters = true)
    private Set<BedDetails> bedDetails = new HashSet<>();

    @OneToMany(mappedBy = "roomDetails")
    @JsonIgnoreProperties(value = { "roomDetails" }, allowSetters = true)
    private Set<RoomAdvanceBooking> roomAdvanceBookings = new HashSet<>();

    @OneToMany(mappedBy = "roomDetails")
    @JsonIgnoreProperties(value = { "roomDetails", "employeeMaster" }, allowSetters = true)
    private Set<Booking> bookings = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "roomDetails", "company" }, allowSetters = true)
    private Camp camp;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RoomDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomDescription() {
        return this.roomDescription;
    }

    public RoomDetails roomDescription(String roomDescription) {
        this.setRoomDescription(roomDescription);
        return this;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getBlock() {
        return this.block;
    }

    public RoomDetails block(String block) {
        this.setBlock(block);
        return this;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getFloor() {
        return this.floor;
    }

    public RoomDetails floor(String floor) {
        this.setFloor(floor);
        return this;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomCategory() {
        return this.roomCategory;
    }

    public RoomDetails roomCategory(String roomCategory) {
        this.setRoomCategory(roomCategory);
        return this;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Integer getBedCount() {
        return this.bedCount;
    }

    public RoomDetails bedCount(Integer bedCount) {
        this.setBedCount(bedCount);
        return this;
    }

    public void setBedCount(Integer bedCount) {
        this.bedCount = bedCount;
    }

    public String getRoomNo() {
        return this.roomNo;
    }

    public RoomDetails roomNo(String roomNo) {
        this.setRoomNo(roomNo);
        return this;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomAllocation() {
        return this.roomAllocation;
    }

    public RoomDetails roomAllocation(String roomAllocation) {
        this.setRoomAllocation(roomAllocation);
        return this;
    }

    public void setRoomAllocation(String roomAllocation) {
        this.roomAllocation = roomAllocation;
    }

    public String getRoomConfiguration() {
        return this.roomConfiguration;
    }

    public RoomDetails roomConfiguration(String roomConfiguration) {
        this.setRoomConfiguration(roomConfiguration);
        return this;
    }

    public void setRoomConfiguration(String roomConfiguration) {
        this.roomConfiguration = roomConfiguration;
    }

    public LocalDate getAvailableFrom() {
        return this.availableFrom;
    }

    public RoomDetails availableFrom(LocalDate availableFrom) {
        this.setAvailableFrom(availableFrom);
        return this;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getRoomStatus() {
        return this.roomStatus;
    }

    public RoomDetails roomStatus(String roomStatus) {
        this.setRoomStatus(roomStatus);
        return this;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Boolean getBedOnly() {
        return this.bedOnly;
    }

    public RoomDetails bedOnly(Boolean bedOnly) {
        this.setBedOnly(bedOnly);
        return this;
    }

    public void setBedOnly(Boolean bedOnly) {
        this.bedOnly = bedOnly;
    }

    public Float getMonthlyRate() {
        return this.monthlyRate;
    }

    public RoomDetails monthlyRate(Float monthlyRate) {
        this.setMonthlyRate(monthlyRate);
        return this;
    }

    public void setMonthlyRate(Float monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public Float getDailyRate() {
        return this.dailyRate;
    }

    public RoomDetails dailyRate(Float dailyRate) {
        this.setDailyRate(dailyRate);
        return this;
    }

    public void setDailyRate(Float dailyRate) {
        this.dailyRate = dailyRate;
    }

    public Float getBedRate() {
        return this.bedRate;
    }

    public RoomDetails bedRate(Float bedRate) {
        this.setBedRate(bedRate);
        return this;
    }

    public void setBedRate(Float bedRate) {
        this.bedRate = bedRate;
    }

    public Float getReservationRate() {
        return this.reservationRate;
    }

    public RoomDetails reservationRate(Float reservationRate) {
        this.setReservationRate(reservationRate);
        return this;
    }

    public void setReservationRate(Float reservationRate) {
        this.reservationRate = reservationRate;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public RoomDetails remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public RoomDetails createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public RoomDetails createdAt(LocalDate createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifyBy() {
        return this.modifyBy;
    }

    public RoomDetails modifyBy(String modifyBy) {
        this.setModifyBy(modifyBy);
        return this;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public LocalDate getModifyAt() {
        return this.modifyAt;
    }

    public RoomDetails modifyAt(LocalDate modifyAt) {
        this.setModifyAt(modifyAt);
        return this;
    }

    public void setModifyAt(LocalDate modifyAt) {
        this.modifyAt = modifyAt;
    }

    public Catering getCatering() {
        return this.catering;
    }

    public void setCatering(Catering catering) {
        this.catering = catering;
    }

    public RoomDetails catering(Catering catering) {
        this.setCatering(catering);
        return this;
    }

    public Set<BedDetails> getBedDetails() {
        return this.bedDetails;
    }

    public void setBedDetails(Set<BedDetails> bedDetails) {
        if (this.bedDetails != null) {
            this.bedDetails.forEach(i -> i.setRoomDetails(null));
        }
        if (bedDetails != null) {
            bedDetails.forEach(i -> i.setRoomDetails(this));
        }
        this.bedDetails = bedDetails;
    }

    public RoomDetails bedDetails(Set<BedDetails> bedDetails) {
        this.setBedDetails(bedDetails);
        return this;
    }

    public RoomDetails addBedDetails(BedDetails bedDetails) {
        this.bedDetails.add(bedDetails);
        bedDetails.setRoomDetails(this);
        return this;
    }

    public RoomDetails removeBedDetails(BedDetails bedDetails) {
        this.bedDetails.remove(bedDetails);
        bedDetails.setRoomDetails(null);
        return this;
    }

    public Set<RoomAdvanceBooking> getRoomAdvanceBookings() {
        return this.roomAdvanceBookings;
    }

    public void setRoomAdvanceBookings(Set<RoomAdvanceBooking> roomAdvanceBookings) {
        if (this.roomAdvanceBookings != null) {
            this.roomAdvanceBookings.forEach(i -> i.setRoomDetails(null));
        }
        if (roomAdvanceBookings != null) {
            roomAdvanceBookings.forEach(i -> i.setRoomDetails(this));
        }
        this.roomAdvanceBookings = roomAdvanceBookings;
    }

    public RoomDetails roomAdvanceBookings(Set<RoomAdvanceBooking> roomAdvanceBookings) {
        this.setRoomAdvanceBookings(roomAdvanceBookings);
        return this;
    }

    public RoomDetails addRoomAdvanceBooking(RoomAdvanceBooking roomAdvanceBooking) {
        this.roomAdvanceBookings.add(roomAdvanceBooking);
        roomAdvanceBooking.setRoomDetails(this);
        return this;
    }

    public RoomDetails removeRoomAdvanceBooking(RoomAdvanceBooking roomAdvanceBooking) {
        this.roomAdvanceBookings.remove(roomAdvanceBooking);
        roomAdvanceBooking.setRoomDetails(null);
        return this;
    }

    public Set<Booking> getBookings() {
        return this.bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        if (this.bookings != null) {
            this.bookings.forEach(i -> i.setRoomDetails(null));
        }
        if (bookings != null) {
            bookings.forEach(i -> i.setRoomDetails(this));
        }
        this.bookings = bookings;
    }

    public RoomDetails bookings(Set<Booking> bookings) {
        this.setBookings(bookings);
        return this;
    }

    public RoomDetails addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setRoomDetails(this);
        return this;
    }

    public RoomDetails removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setRoomDetails(null);
        return this;
    }

    public Camp getCamp() {
        return this.camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public RoomDetails camp(Camp camp) {
        this.setCamp(camp);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoomDetails)) {
            return false;
        }
        return id != null && id.equals(((RoomDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoomDetails{" +
            "id=" + getId() +
            ", roomDescription='" + getRoomDescription() + "'" +
            ", block='" + getBlock() + "'" +
            ", floor='" + getFloor() + "'" +
            ", roomCategory='" + getRoomCategory() + "'" +
            ", bedCount=" + getBedCount() +
            ", roomNo='" + getRoomNo() + "'" +
            ", roomAllocation='" + getRoomAllocation() + "'" +
            ", roomConfiguration='" + getRoomConfiguration() + "'" +
            ", availableFrom='" + getAvailableFrom() + "'" +
            ", roomStatus='" + getRoomStatus() + "'" +
            ", bedOnly='" + getBedOnly() + "'" +
            ", monthlyRate=" + getMonthlyRate() +
            ", dailyRate=" + getDailyRate() +
            ", bedRate=" + getBedRate() +
            ", reservationRate=" + getReservationRate() +
            ", remarks='" + getRemarks() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", modifyBy='" + getModifyBy() + "'" +
            ", modifyAt='" + getModifyAt() + "'" +
            "}";
    }
}
