package net.ccc.apps.core.service.queryService;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.ProjectInfo;
import net.ccc.apps.core.repository.ProjectInfoRepository;
import net.ccc.apps.core.service.criteria.ProjectInfoCriteria;
import net.ccc.apps.core.service.dto.ProjectInfoDTO;
import net.ccc.apps.core.service.mapper.ProjectInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link ProjectInfo} entities in the database.
 * The main input is a {@link ProjectInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProjectInfoDTO} or a {@link Page} of {@link ProjectInfoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProjectInfoQueryService extends QueryService<ProjectInfo> {

    private final Logger log = LoggerFactory.getLogger(ProjectInfoQueryService.class);

    private final ProjectInfoRepository projectInfoRepository;

    private final ProjectInfoMapper projectInfoMapper;

    public ProjectInfoQueryService(ProjectInfoRepository projectInfoRepository, ProjectInfoMapper projectInfoMapper) {
        this.projectInfoRepository = projectInfoRepository;
        this.projectInfoMapper = projectInfoMapper;
    }

    /**
     * Return a {@link List} of {@link ProjectInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProjectInfoDTO> findByCriteria(ProjectInfoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ProjectInfo> specification = createSpecification(criteria);
        return projectInfoMapper.toDto(projectInfoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProjectInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProjectInfoDTO> findByCriteria(ProjectInfoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ProjectInfo> specification = createSpecification(criteria);
        return projectInfoRepository.findAll(specification, page).map(projectInfoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProjectInfoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ProjectInfo> specification = createSpecification(criteria);
        return projectInfoRepository.count(specification);
    }

    /**
     * Function to convert {@link ProjectInfoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ProjectInfo> createSpecification(ProjectInfoCriteria criteria) {
        Specification<ProjectInfo> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ProjectInfo_.id));
            }
            if (criteria.getProjectId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProjectId(), ProjectInfo_.projectId));
            }
            if (criteria.getContractNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContractNumber(), ProjectInfo_.contractNumber));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ProjectInfo_.name));
            }
            if (criteria.getStartDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartDate(), ProjectInfo_.startDate));
            }
            if (criteria.getEndDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndDate(), ProjectInfo_.endDate));
            }
            if (criteria.getAttachementId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getAttachementId(),
                            root -> root.join(ProjectInfo_.attachements, JoinType.LEFT).get(Attachement_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
