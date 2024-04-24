package net.ccc.apps.core.repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import net.ccc.apps.core.domain.AppUser;
import org.hibernate.annotations.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class AppUserRepositoryWithBagRelationshipsImpl implements AppUserRepositoryWithBagRelationships {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<AppUser> fetchBagRelationships(Optional<AppUser> appUser) {
        return appUser.map(this::fetchRoles);
    }

    @Override
    public Page<AppUser> fetchBagRelationships(Page<AppUser> appUsers) {
        return new PageImpl<>(fetchBagRelationships(appUsers.getContent()), appUsers.getPageable(), appUsers.getTotalElements());
    }

    @Override
    public List<AppUser> fetchBagRelationships(List<AppUser> appUsers) {
        return Optional.of(appUsers).map(this::fetchRoles).get();
    }

    AppUser fetchRoles(AppUser result) {
        return entityManager
            .createQuery("select appUser from AppUser appUser left join fetch appUser.roles where appUser is :appUser", AppUser.class)
            .setParameter("appUser", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<AppUser> fetchRoles(List<AppUser> appUsers) {
        return entityManager
            .createQuery(
                "select distinct appUser from AppUser appUser left join fetch appUser.roles where appUser in :appUsers",
                AppUser.class
            )
            .setParameter("appUsers", appUsers)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
