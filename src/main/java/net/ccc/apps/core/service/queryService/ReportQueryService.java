package net.ccc.apps.core.service.queryService;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.Report;
import net.ccc.apps.core.repository.ReportRepository;
import net.ccc.apps.core.service.criteria.ReportCriteria;
import net.ccc.apps.core.service.dto.ReportDTO;
import net.ccc.apps.core.service.mapper.ReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Report} entities in the database.
 * The main input is a {@link ReportCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ReportDTO} or a {@link Page} of {@link ReportDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ReportQueryService extends QueryService<Report> {

    private final Logger log = LoggerFactory.getLogger(ReportQueryService.class);

    private final ReportRepository reportRepository;

    private final ReportMapper reportMapper;

    public ReportQueryService(ReportRepository reportRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    /**
     * Return a {@link List} of {@link ReportDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ReportDTO> findByCriteria(ReportCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Report> specification = createSpecification(criteria);
        return reportMapper.toDto(reportRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ReportDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ReportDTO> findByCriteria(ReportCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Report> specification = createSpecification(criteria);
        return reportRepository.findAll(specification, page).map(reportMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ReportCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Report> specification = createSpecification(criteria);
        return reportRepository.count(specification);
    }

    /**
     * Function to convert {@link ReportCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Report> createSpecification(ReportCriteria criteria) {
        Specification<Report> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Report_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Report_.name));
            }
            if (criteria.getReportPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReportPath(), Report_.reportPath));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Report_.description));
            }
            if (criteria.getAttachementId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getAttachementId(),
                            root -> root.join(Report_.attachements, JoinType.LEFT).get(Attachement_.id)
                        )
                    );
            }
            if (criteria.getReportDetailsId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getReportDetailsId(),
                            root -> root.join(Report_.reportDetails, JoinType.LEFT).get(ReportDetails_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
