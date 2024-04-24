package net.ccc.apps.core.service.queryService;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.Attachement;
import net.ccc.apps.core.repository.AttachementRepository;
import net.ccc.apps.core.service.criteria.AttachementCriteria;
import net.ccc.apps.core.service.dto.AttachementDTO;
import net.ccc.apps.core.service.mapper.AttachementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Attachement} entities in the database.
 * The main input is a {@link AttachementCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AttachementDTO} or a {@link Page} of {@link AttachementDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AttachementQueryService extends QueryService<Attachement> {

    private final Logger log = LoggerFactory.getLogger(AttachementQueryService.class);

    private final AttachementRepository attachementRepository;

    private final AttachementMapper attachementMapper;

    public AttachementQueryService(AttachementRepository attachementRepository, AttachementMapper attachementMapper) {
        this.attachementRepository = attachementRepository;
        this.attachementMapper = attachementMapper;
    }

    /**
     * Return a {@link List} of {@link AttachementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AttachementDTO> findByCriteria(AttachementCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Attachement> specification = createSpecification(criteria);
        return attachementMapper.toDto(attachementRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AttachementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AttachementDTO> findByCriteria(AttachementCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Attachement> specification = createSpecification(criteria);
        return attachementRepository.findAll(specification, page).map(attachementMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AttachementCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Attachement> specification = createSpecification(criteria);
        return attachementRepository.count(specification);
    }

    /**
     * Function to convert {@link AttachementCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Attachement> createSpecification(AttachementCriteria criteria) {
        Specification<Attachement> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Attachement_.id));
            }
            if (criteria.getFileName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileName(), Attachement_.fileName));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Attachement_.description));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildSpecification(criteria.getType(), Attachement_.type));
            }
            if (criteria.getProjectInfoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getProjectInfoId(),
                            root -> root.join(Attachement_.projectInfo, JoinType.LEFT).get(ProjectInfo_.id)
                        )
                    );
            }
            if (criteria.getReportId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getReportId(), root -> root.join(Attachement_.report, JoinType.LEFT).get(Report_.id))
                    );
            }
        }
        return specification;
    }
}
