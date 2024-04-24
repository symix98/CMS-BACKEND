package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.MessageTemplate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MessageTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long>, JpaSpecificationExecutor<MessageTemplate> {}
