package net.ccc.apps.core.service.mapper;


import net.ccc.apps.core.domain.MailRecipient;
import net.ccc.apps.core.service.dto.MailRecipientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link MailRecipient} and its DTO {@link MailRecipientDTO}.
 */
@Mapper(componentModel = "spring", uses = { EmailMessageMapper.class })
public interface MailRecipientMapper extends EntityMapper<MailRecipientDTO, MailRecipient> {
    @Mapping(target = "recipients", source = "recipients", qualifiedByName = "id")
    MailRecipientDTO toDto(MailRecipient s);
}
