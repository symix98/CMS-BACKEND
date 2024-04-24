package net.ccc.apps.core.repository;

import java.util.List;
import net.ccc.apps.core.domain.DashboardLayout;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DashboardLayout entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DashboardLayoutRepository extends JpaRepository<DashboardLayout, Long> {
    List<DashboardLayout> findByDashboardLayoutId(String dashboardLayoutId);
}
