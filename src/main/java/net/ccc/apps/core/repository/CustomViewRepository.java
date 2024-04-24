package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.CustomView;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CustomView entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomViewRepository extends JpaRepository<CustomView, Long>, JpaSpecificationExecutor<CustomView> {}
