package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.Attachement;
import net.ccc.apps.core.service.dto.AttachementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Attachement} and its DTO {@link AttachementDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProjectInfoMapper.class, ReportMapper.class })
public interface AttachementMapper extends EntityMapper<AttachementDTO, Attachement> {
    @Mapping(target = "projectInfo", source = "projectInfo", qualifiedByName = "id")
    @Mapping(target = "report", source = "report", qualifiedByName = "id")
    AttachementDTO toDto(Attachement s);
}
