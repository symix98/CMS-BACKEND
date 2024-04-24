package net.ccc.apps.core.service.queryService;

import java.util.List;

import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.Reference;
import net.ccc.apps.core.repository.ReferenceRepository;
import net.ccc.apps.core.service.criteria.ReferenceCriteria;
import net.ccc.apps.core.service.dto.ReferenceDTO;
import net.ccc.apps.core.service.mapper.ReferenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Reference} entities in the database.
 * The main input is a {@link ReferenceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ReferenceDTO} or a {@link Page} of {@link ReferenceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ReferenceQueryService extends QueryService<Reference> {

    private final Logger log = LoggerFactory.getLogger(ReferenceQueryService.class);

    private final ReferenceRepository referenceRepository;

    private final ReferenceMapper referenceMapper;

    public ReferenceQueryService(ReferenceRepository referenceRepository, ReferenceMapper referenceMapper) {
        this.referenceRepository = referenceRepository;
        this.referenceMapper = referenceMapper;
    }

    /**
     * Return a {@link List} of {@link ReferenceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ReferenceDTO> findByCriteria(ReferenceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Reference> specification = createSpecification(criteria);
        return referenceMapper.toDto(referenceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ReferenceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ReferenceDTO> findByCriteria(ReferenceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Reference> specification = createSpecification(criteria);
        return referenceRepository.findAll(specification, page).map(referenceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ReferenceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Reference> specification = createSpecification(criteria);
        return referenceRepository.count(specification);
    }

    /**
     * Function to convert {@link ReferenceCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Reference> createSpecification(ReferenceCriteria criteria) {
        Specification<Reference> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Reference_.id));
            }
            if (criteria.getReferenceKey() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReferenceKey(), Reference_.referenceKey));
            }
            if (criteria.getValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValue(), Reference_.value));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Reference_.description));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), Reference_.type));
            }
        }
        return specification;
    }
}
