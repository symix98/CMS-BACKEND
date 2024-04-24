package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.CustomView;
import net.ccc.apps.core.service.dto.CustomViewDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CustomView} and its DTO {@link CustomViewDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomViewMapper extends EntityMapper<CustomViewDTO, CustomView> {}
