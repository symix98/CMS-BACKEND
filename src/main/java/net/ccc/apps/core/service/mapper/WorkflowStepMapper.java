package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.WorkflowStep;
import net.ccc.apps.core.service.dto.WorkflowStepDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkflowStep} and its DTO {@link WorkflowStepDTO}.
 */
@Mapper(componentModel = "spring", uses = {WorkflowProcessMapper.class})
public interface WorkflowStepMapper extends EntityMapper<WorkflowStepDTO, WorkflowStep> {

    @Mapping(source = "workflowProcess.id", target = "workflowProcessId")
    @Mapping(source = "workflowActionUsers", target = "workflowActionUserDTOList")
    WorkflowStepDTO toDto(WorkflowStep workflowStep);

    @Mapping(target = "workflowActionUsers", ignore = true)
    @Mapping(target = "removeWorkflowActionUser", ignore = true)
    @Mapping(source = "workflowProcessId", target = "workflowProcess")
    WorkflowStep toEntity(WorkflowStepDTO workflowStepDTO);

    default WorkflowStep fromId(Long id) {
        if (id == null) {
            return null;
        }
        WorkflowStep workflowStep = new WorkflowStep();
        workflowStep.setId(id);
        return workflowStep;
    }
}

