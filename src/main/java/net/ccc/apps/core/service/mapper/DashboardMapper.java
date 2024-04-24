package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.Dashboard;
import net.ccc.apps.core.service.dto.DashboardDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dashboard} and its DTO {@link DashboardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DashboardMapper extends EntityMapper<DashboardDTO, Dashboard> {
    @Mapping(target = "dashboardDetails", ignore = true)
    @Mapping(target = "removeDashboardDetails", ignore = true)
    Dashboard toEntity(DashboardDTO dashboardDTO);

    default Dashboard fromId(String dashboardId) {
        if (dashboardId == null) {
            return null;
        }
        Dashboard dashboard = new Dashboard();
        dashboard.setDashboardId(dashboardId);
        return dashboard;
    }
}
