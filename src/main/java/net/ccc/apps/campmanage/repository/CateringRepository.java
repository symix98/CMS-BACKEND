package net.ccc.apps.campmanage.repository;

import net.ccc.apps.campmanage.domain.Catering;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Catering entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CateringRepository extends JpaRepository<Catering, Long>, JpaSpecificationExecutor<Catering> {}
