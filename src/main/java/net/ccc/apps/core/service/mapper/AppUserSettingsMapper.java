package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.AppUserSettings;
import net.ccc.apps.core.service.dto.AppUserSettingsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AppUserSettings} and its DTO {@link AppUserSettingsDTO}.
 */
@Mapper(componentModel = "spring", uses = { AppUserMapper.class })
public interface AppUserSettingsMapper extends EntityMapper<AppUserSettingsDTO, AppUserSettings> {
//    @Mapping(target = "appUser", source = "appUser")
//    @Mapping(source = "appUser.userId", target = "appUserId")
    AppUserSettingsDTO toDto(AppUserSettings s);
}
