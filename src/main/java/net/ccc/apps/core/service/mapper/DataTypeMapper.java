package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.DataType;
import net.ccc.apps.core.service.dto.DataTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DataType} and its DTO {@link DataTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DataTypeMapper extends EntityMapper<DataTypeDTO, DataType> {}
