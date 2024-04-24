package net.ccc.apps.core.service;

import java.util.List;
import net.ccc.apps.core.domain.IdConfig;
import net.ccc.apps.core.domain.IdConfig_;
import net.ccc.apps.core.repository.IdConfigRepository;
import net.ccc.apps.core.service.criteria.IdConfigCriteria;
import net.ccc.apps.core.service.dto.IdConfigDTO;
import net.ccc.apps.core.service.mapper.IdConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link IdConfig} entities in the database.
 * The main input is a {@link IdConfigCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link IdConfigDTO} or a {@link Page} of {@link IdConfigDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class IdConfigQueryService extends QueryService<IdConfig> {

    private final Logger log = LoggerFactory.getLogger(IdConfigQueryService.class);

    private final IdConfigRepository idConfigRepository;

    private final IdConfigMapper idConfigMapper;

    public IdConfigQueryService(IdConfigRepository idConfigRepository, IdConfigMapper idConfigMapper) {
        this.idConfigRepository = idConfigRepository;
        this.idConfigMapper = idConfigMapper;
    }

    /**
     * Return a {@link List} of {@link IdConfigDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<IdConfigDTO> findByCriteria(IdConfigCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<IdConfig> specification = createSpecification(criteria);
        return idConfigMapper.toDto(idConfigRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link IdConfigDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<IdConfigDTO> findByCriteria(IdConfigCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<IdConfig> specification = createSpecification(criteria);
        return idConfigRepository.findAll(specification, page).map(idConfigMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(IdConfigCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<IdConfig> specification = createSpecification(criteria);
        return idConfigRepository.count(specification);
    }

    /**
     * Function to convert {@link IdConfigCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<IdConfig> createSpecification(IdConfigCriteria criteria) {
        Specification<IdConfig> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            /*if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }*/
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), IdConfig_.name));
            }
            if (criteria.getPrefix() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrefix(), IdConfig_.prefix));
            }
            if (criteria.getPostfix() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPostfix(), IdConfig_.postfix));
            }
            if (criteria.getCounterStart() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCounterStart(), IdConfig_.counterStart));
            }
            if (criteria.getCounterDigits() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCounterDigits(), IdConfig_.counterDigits));
            }
            if (criteria.getCurrentCounterValue() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getCurrentCounterValue(), IdConfig_.currentCounterValue));
            }
        }
        return specification;
    }
}
