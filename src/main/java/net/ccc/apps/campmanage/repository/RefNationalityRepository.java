package net.ccc.apps.campmanage.repository;

import net.ccc.apps.campmanage.domain.RefNationality;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RefNationality entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefNationalityRepository extends JpaRepository<RefNationality, Long>, JpaSpecificationExecutor<RefNationality> {}
