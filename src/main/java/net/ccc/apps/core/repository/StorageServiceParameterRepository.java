package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.StorageServiceParameter;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StorageServiceParameter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StorageServiceParameterRepository extends JpaRepository<StorageServiceParameter, Long>, JpaSpecificationExecutor<StorageServiceParameter> {
}
