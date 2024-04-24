package net.ccc.apps.campmanage.repository;

import net.ccc.apps.campmanage.domain.RoomAdvanceBooking;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RoomAdvanceBooking entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoomAdvanceBookingRepository
    extends JpaRepository<RoomAdvanceBooking, Long>, JpaSpecificationExecutor<RoomAdvanceBooking> {}
