package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.SecurityPermission;
import net.ccc.apps.core.domain.composite.SecurityPermissionId;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the SecurityPermission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecurityPermissionRepository extends JpaRepository<SecurityPermission, SecurityPermissionId>, JpaSpecificationExecutor<SecurityPermission> {
    List<SecurityPermission> findByIdRole(String role);
}
