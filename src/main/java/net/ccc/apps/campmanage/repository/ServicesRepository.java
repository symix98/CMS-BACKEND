package net.ccc.apps.campmanage.repository;

import net.ccc.apps.campmanage.domain.Services;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Services entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServicesRepository extends JpaRepository<Services, Long>, JpaSpecificationExecutor<Services> {}
