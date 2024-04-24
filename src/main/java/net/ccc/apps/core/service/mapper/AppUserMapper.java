package net.ccc.apps.core.service.mapper;

import java.util.Set;
import net.ccc.apps.core.domain.AppUser;
import net.ccc.apps.core.service.dto.AppUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppUser} and its DTO {@link AppUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface AppUserMapper extends EntityMapper<AppUserDTO, AppUser> {
    @Mapping(target = "roles", source = "roles", qualifiedByName = "idSet")
    AppUserDTO toDto(AppUser s);

    @Mapping(target = "removeRole", ignore = true)
    @Mapping(target = "workflowProcesses", ignore = true)
    @Mapping(target = "removeWorkflowProcess", ignore = true)
    AppUser toEntity(AppUserDTO appUserDTO);

    default AppUser fromId(String userId) {
        if (userId == null) {
            return null;
        }
        AppUser appUser = new AppUser();
        appUser.setUserId(userId);
        return appUser;
    }
}
