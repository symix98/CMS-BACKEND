package net.ccc.apps.campmanage.repository;

import net.ccc.apps.campmanage.domain.RefEmployeeCompany;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RefEmployeeCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefEmployeeCompanyRepository
    extends JpaRepository<RefEmployeeCompany, Long>, JpaSpecificationExecutor<RefEmployeeCompany> {}
