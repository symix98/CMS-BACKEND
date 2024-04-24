package net.ccc.apps.core.repository;


import net.ccc.apps.core.domain.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the EmailMessage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmailMessageRepository extends JpaRepository<EmailMessage, Long>, JpaSpecificationExecutor<EmailMessage> {

    List<EmailMessage>findAllByReferenceIdIn(List<Long> requestIdList);
    List<EmailMessage>findAllByReferenceId(Long requestId);

}
