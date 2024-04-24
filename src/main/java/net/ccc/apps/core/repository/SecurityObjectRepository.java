package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.SecurityObject;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SecurityObject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecurityObjectRepository extends JpaRepository<SecurityObject, Long> {}
