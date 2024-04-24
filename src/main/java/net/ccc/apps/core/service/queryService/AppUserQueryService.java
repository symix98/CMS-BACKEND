package net.ccc.apps.core.service.queryService;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.AppUser;
import net.ccc.apps.core.repository.AppUserRepository;
import net.ccc.apps.core.service.criteria.AppUserCriteria;
import net.ccc.apps.core.service.dto.AppUserDTO;
import net.ccc.apps.core.service.mapper.AppUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link AppUser} entities in the database.
 * The main input is a {@link AppUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AppUserDTO} or a {@link Page} of {@link AppUserDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AppUserQueryService extends QueryService<AppUser> {

    private final Logger log = LoggerFactory.getLogger(AppUserQueryService.class);

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserMapper appUserMapper;

    /**
     * Return a {@link List} of {@link AppUserDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AppUserDTO> findByCriteria(boolean globalSearch,AppUserCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AppUser> specification = globalSearch? createSearchSpecification(criteria) : createSpecification(criteria);
        return appUserMapper.toDto(appUserRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AppUserDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AppUserDTO> findByCriteria(boolean globalSearch, AppUserCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AppUser> specification = globalSearch? createSearchSpecification(criteria) : createSpecification(criteria);
        return appUserRepository.findAll(specification, page).map(appUserMapper :: toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AppUserCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AppUser> specification = createSpecification(criteria);
        return appUserRepository.count(specification);
    }

    /**
     * Function to convert {@link AppUserCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AppUser> createSpecification(AppUserCriteria criteria) {
        Specification<AppUser> specification = Specification.where(null);
        if (criteria != null) {

            if (criteria.getUserId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserId(), AppUser_.userId));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), AppUser_.name));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), AppUser_.email));
            }
            if (criteria.getIsAdministrator() != null) {
                specification = specification.and(buildSpecification(criteria.getIsAdministrator(), AppUser_.isAdministrator));
            }
            if (criteria.getRoleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRoleId(), root -> root.join(AppUser_.roles, JoinType.LEFT).get(Role_.ROLE_ID)));
            }
            if (criteria.getAttachmentId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAttachmentId(), AppUser_.attachmentId));
            }
        }
        return specification;
    }

    protected Specification<AppUser> createSearchSpecification(AppUserCriteria criteria) {
        Specification<AppUser> specification = Specification.where(null);
        if (criteria != null) {

            if (criteria.getUserId() != null) {
                specification = specification.or(buildStringSpecification(criteria.getUserId(), AppUser_.userId));
            }
            if (criteria.getName() != null) {
                specification = specification.or(buildStringSpecification(criteria.getName(), AppUser_.name));
            }
            if (criteria.getEmail() != null) {
                specification = specification.or(buildStringSpecification(criteria.getEmail(), AppUser_.email));
            }
            if (criteria.getIsAdministrator() != null) {
                specification = specification.or(buildSpecification(criteria.getIsAdministrator(), AppUser_.isAdministrator));
            }
            if (criteria.getRoleId() != null) {
                specification = specification.or(buildSpecification(criteria.getRoleId(), root -> root.join(AppUser_.roles, JoinType.LEFT).get(Role_.ROLE_ID)));
            }
            if (criteria.getAttachmentId() != null) {
                specification = specification.or(buildRangeSpecification(criteria.getAttachmentId(), AppUser_.attachmentId));
            }
            if (criteria.getAppUserSettingsId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getAppUserSettingsId(),
                            root -> root.join(AppUser_.appUserSettings, JoinType.LEFT).get(AppUserSettings_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
