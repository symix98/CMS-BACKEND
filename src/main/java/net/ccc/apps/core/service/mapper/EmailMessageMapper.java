package net.ccc.apps.core.service.mapper;


import net.ccc.apps.core.domain.EmailMessage;
import net.ccc.apps.core.service.dto.EmailMessageDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link EmailMessage} and its DTO {@link EmailMessageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmailMessageMapper extends EntityMapper<EmailMessageDTO, EmailMessage> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmailMessageDTO toDtoId(EmailMessage emailMessage);
}
