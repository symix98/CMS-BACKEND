package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.WorkflowActionUser;
import net.ccc.apps.core.service.dto.WorkflowActionUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkflowActionUser} and its DTO {@link WorkflowActionUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {WorkflowStepMapper.class, AppUserMapper.class})
public interface WorkflowActionUserMapper extends EntityMapper<WorkflowActionUserDTO, WorkflowActionUser> {
     @Mapping(source = "workflowStep.id", target = "workflowStepId")
    WorkflowActionUserDTO toDto(WorkflowActionUser workflowActionUser);

     @Mapping(source = "workflowStepId", target = "workflowStep")
    WorkflowActionUser toEntity(WorkflowActionUserDTO workflowActionUserDTO);

    default WorkflowActionUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        WorkflowActionUser workflowActionUser = new WorkflowActionUser();
        workflowActionUser.setId(id);
        return workflowActionUser;
    }
}
