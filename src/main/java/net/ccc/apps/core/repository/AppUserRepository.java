package net.ccc.apps.core.repository;

import java.util.List;
import java.util.Optional;
import net.ccc.apps.core.domain.AppUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AppUser entity.
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String>, JpaSpecificationExecutor<AppUser>  {
    @Query(value = "select distinct appUser from AppUser appUser left join fetch appUser.roles roles where roles.roleId=:roleId ")
    List<AppUser> findAllByRoleId(@Param("roleId") String roleId);

    Optional<AppUser> findByEmail(String email);
}
