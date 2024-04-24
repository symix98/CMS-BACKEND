package net.ccc.apps.core.repository;

import java.util.List;
import java.util.Optional;
import net.ccc.apps.core.domain.AppUser;
import org.springframework.data.domain.Page;

public interface AppUserRepositoryWithBagRelationships {
    Optional<AppUser> fetchBagRelationships(Optional<AppUser> appUser);

    List<AppUser> fetchBagRelationships(List<AppUser> appUsers);

    Page<AppUser> fetchBagRelationships(Page<AppUser> appUsers);
}
