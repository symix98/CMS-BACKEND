package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.Report;
import net.ccc.apps.core.service.dto.ReportDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Report} and its DTO {@link ReportDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReportMapper extends EntityMapper<ReportDTO, Report> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ReportDTO toDtoId(Report report);
}
