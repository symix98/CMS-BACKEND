package net.ccc.apps.core.service.queryService;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.AppUserSettings;
import net.ccc.apps.core.repository.AppUserSettingsRepository;
import net.ccc.apps.core.service.criteria.AppUserSettingsCriteria;
import net.ccc.apps.core.service.dto.AppUserSettingsDTO;
import net.ccc.apps.core.service.mapper.AppUserSettingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link AppUserSettings} entities in the database.
 * The main input is a {@link AppUserSettingsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AppUserSettingsDTO} or a {@link Page} of {@link AppUserSettingsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AppUserSettingsQueryService extends QueryService<AppUserSettings> {

    private final Logger log = LoggerFactory.getLogger(AppUserSettingsQueryService.class);

    private final AppUserSettingsRepository appUserSettingsRepository;

    private final AppUserSettingsMapper appUserSettingsMapper;

    public AppUserSettingsQueryService(AppUserSettingsRepository appUserSettingsRepository, AppUserSettingsMapper appUserSettingsMapper) {
        this.appUserSettingsRepository = appUserSettingsRepository;
        this.appUserSettingsMapper = appUserSettingsMapper;
    }

    /**
     * Return a {@link List} of {@link AppUserSettingsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AppUserSettingsDTO> findByCriteria(AppUserSettingsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AppUserSettings> specification = createSpecification(criteria);
        return appUserSettingsMapper.toDto(appUserSettingsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AppUserSettingsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AppUserSettingsDTO> findByCriteria(AppUserSettingsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AppUserSettings> specification = createSpecification(criteria);
        return appUserSettingsRepository.findAll(specification, page).map(appUserSettingsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AppUserSettingsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AppUserSettings> specification = createSpecification(criteria);
        return appUserSettingsRepository.count(specification);
    }

    /**
     * Function to convert {@link AppUserSettingsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AppUserSettings> createSpecification(AppUserSettingsCriteria criteria) {
        Specification<AppUserSettings> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), AppUserSettings_.id));
            }
            if (criteria.getProperty() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProperty(), AppUserSettings_.property));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValue(), AppUserSettings_.value));
            }
            if (criteria.getAppUserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getAppUserId(),
                            root -> root.join(AppUserSettings_.appUser, JoinType.LEFT).get(AppUser_.USER_ID)
                        )
                    );
            }
        }
        return specification;
    }
}
