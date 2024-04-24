package net.ccc.apps.campmanage.repository;

import net.ccc.apps.campmanage.domain.EmployeeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EmployeeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Long>, JpaSpecificationExecutor<EmployeeMaster> {}
