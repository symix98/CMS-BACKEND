package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.StorageServiceParameter;
import net.ccc.apps.core.service.dto.StorageServiceParameterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link StorageServiceParameter} and its DTO {@link StorageServiceParameterDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StorageServiceParameterMapper extends EntityMapper<StorageServiceParameterDTO, StorageServiceParameter> {



    default StorageServiceParameter fromId(Long id) {
        if (id == null) {
            return null;
        }
        StorageServiceParameter storageServiceParameter = new StorageServiceParameter();
        storageServiceParameter.setId(id);
        return storageServiceParameter;
    }
}
