package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.Inbox;
import net.ccc.apps.core.service.dto.InboxDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Inbox} and its DTO {@link InboxDTO}.
 */
@Mapper(componentModel = "spring", uses = {AppUserMapper.class})
public interface InboxMapper extends EntityMapper<InboxDTO, Inbox> {

    @Mapping(source = "assignedTo.userId", target = "assignedToId")
    @Mapping(source = "assignedBy.userId", target = "assignedById")
    @Mapping(source = "assignedTo.name", target = "assignedToName")
    @Mapping(source = "assignedBy.name", target = "assignedByName")
    InboxDTO toDto(Inbox inbox);

    @Mapping(source = "assignedToId", target = "assignedTo")
    @Mapping(source = "assignedById", target = "assignedBy")
    Inbox toEntity(InboxDTO inboxDTO);

    default Inbox fromId(Long id) {
        if (id == null) {
            return null;
        }
        Inbox inbox = new Inbox();
        inbox.setId(id);
        return inbox;
    }
}
