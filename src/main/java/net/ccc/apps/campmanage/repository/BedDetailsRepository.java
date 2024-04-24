package net.ccc.apps.campmanage.repository;

import net.ccc.apps.campmanage.domain.BedDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BedDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BedDetailsRepository extends JpaRepository<BedDetails, Long>, JpaSpecificationExecutor<BedDetails> {}
