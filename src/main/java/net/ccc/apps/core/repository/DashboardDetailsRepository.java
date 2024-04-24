package net.ccc.apps.core.repository;

import java.util.List;
import net.ccc.apps.core.domain.DashboardDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DashboardDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DashboardDetailsRepository extends JpaRepository<DashboardDetails, Long> {
    @Query(nativeQuery = true, value = "Select * from dashboard_details d where d.dashBoard_dashBoard_id=:dashBoardId ")
    List<DashboardDetails> findByDashBoardId(@Param("dashBoardId") String dashBoardId);
}
