package net.ccc.apps.core.service.queryService;

import java.util.List;

import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.ProjectSettings;
import net.ccc.apps.core.repository.ProjectSettingsRepository;
import net.ccc.apps.core.service.criteria.ProjectSettingsCriteria;
import net.ccc.apps.core.service.dto.ProjectSettingsDTO;
import net.ccc.apps.core.service.mapper.ProjectSettingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link ProjectSettings} entities in the database.
 * The main input is a {@link ProjectSettingsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProjectSettingsDTO} or a {@link Page} of {@link ProjectSettingsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProjectSettingsQueryService extends QueryService<ProjectSettings> {

    private final Logger log = LoggerFactory.getLogger(ProjectSettingsQueryService.class);

    private final ProjectSettingsRepository projectSettingsRepository;

    private final ProjectSettingsMapper projectSettingsMapper;

    public ProjectSettingsQueryService(ProjectSettingsRepository projectSettingsRepository, ProjectSettingsMapper projectSettingsMapper) {
        this.projectSettingsRepository = projectSettingsRepository;
        this.projectSettingsMapper = projectSettingsMapper;
    }

    /**
     * Return a {@link List} of {@link ProjectSettingsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProjectSettingsDTO> findByCriteria(ProjectSettingsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ProjectSettings> specification = createSpecification(criteria);
        return projectSettingsMapper.toDto(projectSettingsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProjectSettingsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProjectSettingsDTO> findByCriteria(ProjectSettingsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ProjectSettings> specification = createSpecification(criteria);
        return projectSettingsRepository.findAll(specification, page).map(projectSettingsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProjectSettingsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ProjectSettings> specification = createSpecification(criteria);
        return projectSettingsRepository.count(specification);
    }

    /**
     * Function to convert {@link ProjectSettingsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ProjectSettings> createSpecification(ProjectSettingsCriteria criteria) {
        Specification<ProjectSettings> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ProjectSettings_.id));
            }
            if (criteria.getProperty() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProperty(), ProjectSettings_.property));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ProjectSettings_.description));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValue(), ProjectSettings_.value));
            }
            if (criteria.getValueType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValueType(), ProjectSettings_.valueType));
            }
            if (criteria.getCategory() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCategory(), ProjectSettings_.category));
            }
            if (criteria.getIsMultiple() != null) {
                specification = specification.and(buildSpecification(criteria.getIsMultiple(), ProjectSettings_.isMultiple));
            }
        }
        return specification;
    }
}
