package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.IdConfig;
import net.ccc.apps.core.service.dto.IdConfigDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public interface IdConfigMapper extends EntityMapper<IdConfigDTO, IdConfig> {
    IdConfig toEntity(IdConfigDTO idConfigDTO);

    default IdConfig fromId(String name) {
        if (name == null) {
            return null;
        }
        IdConfig idConfig = new IdConfig();
        idConfig.setName(name);
        return idConfig;
    }
}
