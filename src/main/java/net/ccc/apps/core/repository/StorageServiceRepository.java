package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.StorageService;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StorageService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StorageServiceRepository extends JpaRepository<StorageService, String>, JpaSpecificationExecutor<StorageService> {
}
