package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.WorkflowProcess;
import net.ccc.apps.core.service.dto.WorkflowProcessDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkflowProcess} and its DTO {@link WorkflowProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = {AppUserMapper.class})
public interface WorkflowProcessMapper extends EntityMapper<WorkflowProcessDTO, WorkflowProcess> {

    @Mapping(source = "initiatedBy.userId", target = "initiatedByUserId")
    WorkflowProcessDTO toDto(WorkflowProcess workflowProcess);

    @Mapping(target = "workflowSteps", ignore = true)
    @Mapping(target = "removeWorkflowStep", ignore = true)
    @Mapping(source = "initiatedByUserId", target = "initiatedBy")
    WorkflowProcess toEntity(WorkflowProcessDTO workflowProcessDTO);

    default WorkflowProcess fromId(Long id) {
        if (id == null) {
            return null;
        }
        WorkflowProcess workflowProcess = new WorkflowProcess();
        workflowProcess.setId(id);
        return workflowProcess;
    }
}

