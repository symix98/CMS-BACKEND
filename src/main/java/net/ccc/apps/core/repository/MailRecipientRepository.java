package net.ccc.apps.core.repository;


import net.ccc.apps.core.domain.MailRecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MailRecipient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MailRecipientRepository extends JpaRepository<MailRecipient, Long>, JpaSpecificationExecutor<MailRecipient> {}
