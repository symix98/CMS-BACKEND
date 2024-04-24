package net.ccc.apps.core.service.mapper;

import java.util.Set;
import net.ccc.apps.core.domain.Role;
import net.ccc.apps.core.service.dto.RoleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Role} and its DTO {@link RoleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
    @Named("idSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    Set<RoleDTO> toDtoIdSet(Set<Role> role);
}
