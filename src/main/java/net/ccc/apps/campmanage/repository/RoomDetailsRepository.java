package net.ccc.apps.campmanage.repository;

import net.ccc.apps.campmanage.domain.RoomDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RoomDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoomDetailsRepository extends JpaRepository<RoomDetails, Long>, JpaSpecificationExecutor<RoomDetails> {}
