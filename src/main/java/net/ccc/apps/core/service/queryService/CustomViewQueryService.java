package net.ccc.apps.core.service.queryService;

import java.util.List;

import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.CustomView;
import net.ccc.apps.core.repository.CustomViewRepository;
import net.ccc.apps.core.service.criteria.CustomViewCriteria;
import net.ccc.apps.core.service.dto.CustomViewDTO;
import net.ccc.apps.core.service.mapper.CustomViewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link CustomView} entities in the database.
 * The main input is a {@link CustomViewCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CustomViewDTO} or a {@link Page} of {@link CustomViewDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomViewQueryService extends QueryService<CustomView> {

    private final Logger log = LoggerFactory.getLogger(CustomViewQueryService.class);

    private final CustomViewRepository customViewRepository;

    private final CustomViewMapper customViewMapper;

    public CustomViewQueryService(CustomViewRepository customViewRepository, CustomViewMapper customViewMapper) {
        this.customViewRepository = customViewRepository;
        this.customViewMapper = customViewMapper;
    }

    /**
     * Return a {@link List} of {@link CustomViewDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CustomViewDTO> findByCriteria(CustomViewCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CustomView> specification = createSpecification(criteria);
        return customViewMapper.toDto(customViewRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CustomViewDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomViewDTO> findByCriteria(CustomViewCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CustomView> specification = createSpecification(criteria);
        return customViewRepository.findAll(specification, page).map(customViewMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CustomViewCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CustomView> specification = createSpecification(criteria);
        return customViewRepository.count(specification);
    }

    /**
     * Function to convert {@link CustomViewCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CustomView> createSpecification(CustomViewCriteria criteria) {
        Specification<CustomView> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CustomView_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), CustomView_.name));
            }
            if (criteria.getUiPage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUiPage(), CustomView_.uiPage));
            }
            if (criteria.getFilter() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFilter(), CustomView_.filter));
            }
            if (criteria.getAccessFilter() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccessFilter(), CustomView_.accessFilter));
            }
        }
        return specification;
    }
}
