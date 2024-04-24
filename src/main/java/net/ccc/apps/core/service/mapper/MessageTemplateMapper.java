package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.MessageTemplate;
import net.ccc.apps.core.service.dto.MessageTemplateDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MessageTemplate} and its DTO {@link MessageTemplateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MessageTemplateMapper extends EntityMapper<MessageTemplateDTO, MessageTemplate> {}
