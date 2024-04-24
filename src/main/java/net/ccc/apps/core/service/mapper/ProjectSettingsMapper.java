package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.ProjectSettings;
import net.ccc.apps.core.service.dto.ProjectSettingsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProjectSettings} and its DTO {@link ProjectSettingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProjectSettingsMapper extends EntityMapper<ProjectSettingsDTO, ProjectSettings> {



    default ProjectSettings fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProjectSettings projectSettings = new ProjectSettings();
        projectSettings.setId(id);
        return projectSettings;
    }
}
