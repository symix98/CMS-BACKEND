package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.ReportDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ReportDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportDetailsRepository extends JpaRepository<ReportDetails, Long>, JpaSpecificationExecutor<ReportDetails> {}
