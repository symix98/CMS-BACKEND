package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.WorkflowStepMessageConfig;
import net.ccc.apps.core.service.dto.WorkflowStepMessageConfigDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkflowStepMessageConfig} and its DTO {@link WorkflowStepMessageConfigDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkflowStepMessageConfigMapper extends EntityMapper<WorkflowStepMessageConfigDTO, WorkflowStepMessageConfig> {}
