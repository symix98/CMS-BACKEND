package net.ccc.apps.core.service.queryService;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.ReportDetails;
import net.ccc.apps.core.repository.ReportDetailsRepository;
import net.ccc.apps.core.service.criteria.ReportDetailsCriteria;
import net.ccc.apps.core.service.dto.ReportDetailsDTO;
import net.ccc.apps.core.service.mapper.ReportDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link ReportDetails} entities in the database.
 * The main input is a {@link ReportDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ReportDetailsDTO} or a {@link Page} of {@link ReportDetailsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ReportDetailsQueryService extends QueryService<ReportDetails> {

    private final Logger log = LoggerFactory.getLogger(ReportDetailsQueryService.class);

    private final ReportDetailsRepository reportDetailsRepository;

    private final ReportDetailsMapper reportDetailsMapper;

    public ReportDetailsQueryService(ReportDetailsRepository reportDetailsRepository, ReportDetailsMapper reportDetailsMapper) {
        this.reportDetailsRepository = reportDetailsRepository;
        this.reportDetailsMapper = reportDetailsMapper;
    }

    /**
     * Return a {@link List} of {@link ReportDetailsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ReportDetailsDTO> findByCriteria(ReportDetailsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ReportDetails> specification = createSpecification(criteria);
        return reportDetailsMapper.toDto(reportDetailsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ReportDetailsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ReportDetailsDTO> findByCriteria(ReportDetailsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ReportDetails> specification = createSpecification(criteria);
        return reportDetailsRepository.findAll(specification, page).map(reportDetailsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ReportDetailsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ReportDetails> specification = createSpecification(criteria);
        return reportDetailsRepository.count(specification);
    }

    /**
     * Function to convert {@link ReportDetailsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ReportDetails> createSpecification(ReportDetailsCriteria criteria) {
        Specification<ReportDetails> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ReportDetails_.id));
            }
            if (criteria.getParamName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParamName(), ReportDetails_.paramName));
            }
            if (criteria.getParamType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParamType(), ReportDetails_.paramType));
            }
            if (criteria.getOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrder(), ReportDetails_.order));
            }
            if (criteria.getMandatory() != null) {
                specification = specification.and(buildSpecification(criteria.getMandatory(), ReportDetails_.mandatory));
            }
            if (criteria.getReportId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getReportId(), root -> root.join(ReportDetails_.report, JoinType.LEFT).get(Report_.id))
                    );
            }
        }
        return specification;
    }
}
