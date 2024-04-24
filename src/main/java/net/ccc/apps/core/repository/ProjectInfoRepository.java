package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.ProjectInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ProjectInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectInfoRepository extends JpaRepository<ProjectInfo, Long>, JpaSpecificationExecutor<ProjectInfo> {}
