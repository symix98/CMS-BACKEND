package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.DashboardDetails;
import net.ccc.apps.core.service.dto.DashboardDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DashboardDetails} and its DTO {@link DashboardDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = { DashboardMapper.class })
public interface DashboardDetailsMapper extends EntityMapper<DashboardDetailsDTO, DashboardDetails> {
    @Mapping(source = "dashboard.dashboardId", target = "dashboardId")
    DashboardDetailsDTO toDto(DashboardDetails dashboardDetails);

    @Mapping(source = "dashboardId", target = "dashboard")
    DashboardDetails toEntity(DashboardDetailsDTO dashboardDetailsDTO);

    default DashboardDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        DashboardDetails dashboardDetails = new DashboardDetails();
        dashboardDetails.setId(id);
        return dashboardDetails;
    }
}
