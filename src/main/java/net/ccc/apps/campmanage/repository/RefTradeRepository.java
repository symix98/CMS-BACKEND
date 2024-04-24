package net.ccc.apps.campmanage.repository;

import net.ccc.apps.campmanage.domain.RefTrade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RefTrade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefTradeRepository extends JpaRepository<RefTrade, Long>, JpaSpecificationExecutor<RefTrade> {}
