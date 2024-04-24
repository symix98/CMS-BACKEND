package net.ccc.apps.core.repository;

import java.util.List;
import java.util.Optional;
import net.ccc.apps.core.domain.AppUser;
import net.ccc.apps.core.domain.AppUserSettings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AppUserSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppUserSettingsRepository extends JpaRepository<AppUserSettings, Long>, JpaSpecificationExecutor<AppUserSettings> {
    @Query(value = "select a.* from app_user_settings a where a.app_user_user_id=:userId", nativeQuery = true)
    Page<AppUserSettings> findByAppUserId(Pageable pageable, @Param("userId") String userId);

    @Query(
        value = " select a.value from app_user_settings a where a.app_user_user_id=:userId" +
        " and upper(a.property)='SUPERVISOR'\n" +
        " and exists (select * from app_user_settings b where b.app_user_user_id=:userId \n" +
        " and upper(b.property)='SUPERVISOR_APPROVAL' and upper(b.value)='TRUE')",
        nativeQuery = true
    )
    String findSupervisorByUserId(@Param("userId") String userId);
}
