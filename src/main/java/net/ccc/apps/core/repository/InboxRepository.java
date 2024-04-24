package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.AppUser;
import net.ccc.apps.core.domain.Inbox;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Inbox entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InboxRepository extends JpaRepository<Inbox, Long>, JpaSpecificationExecutor<Inbox> {
    List<Inbox> findAllByFormId(Long formId);
    Inbox findByIdAndAssignedTo(Long id, AppUser assignedTo);
}
