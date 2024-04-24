package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.DataType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DataType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataTypeRepository extends JpaRepository<DataType, Long>, JpaSpecificationExecutor<DataType> {}
