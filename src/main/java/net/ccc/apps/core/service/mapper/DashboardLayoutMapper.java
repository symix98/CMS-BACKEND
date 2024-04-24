package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.DashboardLayout;
import net.ccc.apps.core.service.dto.DashboardLayoutDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DashboardLayout} and its DTO {@link DashboardLayoutDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DashboardLayoutMapper extends EntityMapper<DashboardLayoutDTO, DashboardLayout> {
    default DashboardLayout fromId(Long id) {
        if (id == null) {
            return null;
        }
        DashboardLayout dashboardLayout = new DashboardLayout();
        dashboardLayout.setId(id);
        return dashboardLayout;
    }
}
