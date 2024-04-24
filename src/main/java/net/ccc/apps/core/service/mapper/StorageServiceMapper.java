package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.StorageService;
import net.ccc.apps.core.service.dto.StorageServiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link StorageService} and its DTO {@link StorageServiceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StorageServiceMapper extends EntityMapper<StorageServiceDTO, StorageService> {



    default StorageService fromId(String id) {
        if (id == null) {
            return null;
        }
        StorageService storageService = new StorageService();
        storageService.setServiceId(id);
        return storageService;
    }
}
