package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.IdConfig;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the IdConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IdConfigRepository extends JpaRepository<IdConfig, String>, JpaSpecificationExecutor<IdConfig> {}
