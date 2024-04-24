package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.WorkflowTemplate;
import net.ccc.apps.core.service.dto.WorkflowTemplateDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkflowTemplate} and its DTO {@link WorkflowTemplateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WorkflowTemplateMapper extends EntityMapper<WorkflowTemplateDTO, WorkflowTemplate> {}
