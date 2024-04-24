package net.ccc.apps.campmanage.repository;

import net.ccc.apps.campmanage.domain.RefMealCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RefMealCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefMealCategoryRepository extends JpaRepository<RefMealCategory, Long>, JpaSpecificationExecutor<RefMealCategory> {}
