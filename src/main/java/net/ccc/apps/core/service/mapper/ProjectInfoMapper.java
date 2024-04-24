package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.ProjectInfo;
import net.ccc.apps.core.service.dto.ProjectInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProjectInfo} and its DTO {@link ProjectInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectInfoMapper extends EntityMapper<ProjectInfoDTO, ProjectInfo> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProjectInfoDTO toDtoId(ProjectInfo projectInfo);

    @Mapping(target = "attachements", source = "attachements")
    ProjectInfoDTO toDto(ProjectInfo projectInfo);
}
