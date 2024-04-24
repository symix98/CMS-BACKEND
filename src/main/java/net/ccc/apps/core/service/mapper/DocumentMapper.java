package net.ccc.apps.core.service.mapper;

import net.ccc.apps.core.domain.Document;
import net.ccc.apps.core.service.dto.DocumentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Document} and its DTO {@link DocumentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {



    default Document fromId(String id) {
        if (id == null) {
            return null;
        }
        Document document = new Document();
        document.setDocumentId(id);
        return document;
    }
}
