package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.ReportDetails;
import net.ccc.apps.core.service.dto.ReportDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ReportDetails} and its DTO {@link ReportDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = { ReportMapper.class })
public interface ReportDetailsMapper extends EntityMapper<ReportDetailsDTO, ReportDetails> {
    @Mapping(target = "report", source = "report", qualifiedByName = "id")
    ReportDetailsDTO toDto(ReportDetails s);
}
