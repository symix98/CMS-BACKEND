package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.Document;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Document entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentRepository extends JpaRepository<Document, String> , JpaSpecificationExecutor<Document>{
}
