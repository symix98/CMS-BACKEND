package net.ccc.apps.core.repository;

import net.ccc.apps.core.domain.Attachement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Attachement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttachementRepository extends JpaRepository<Attachement, Long>, JpaSpecificationExecutor<Attachement> {

    List<Attachement> findAllByProjectInfoId(Long projectInfoId);

}
