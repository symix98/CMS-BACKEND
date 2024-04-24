package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.Reference;
import net.ccc.apps.core.service.dto.ReferenceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reference} and its DTO {@link ReferenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReferenceMapper extends EntityMapper<ReferenceDTO, Reference> {}
