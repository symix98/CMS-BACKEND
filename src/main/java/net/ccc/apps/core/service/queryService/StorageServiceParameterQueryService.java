package net.ccc.apps.core.service.queryService;

import java.util.List;

import javax.persistence.criteria.JoinType;

import net.ccc.apps.core.domain.StorageServiceParameter;
import net.ccc.apps.core.domain.StorageServiceParameter_;
import net.ccc.apps.core.repository.StorageServiceParameterRepository;
import net.ccc.apps.core.service.criteria.StorageServiceParameterCriteria;
import net.ccc.apps.core.service.dto.StorageServiceParameterDTO;
import net.ccc.apps.core.service.mapper.StorageServiceParameterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link StorageServiceParameter} entities in the database.
 * The main input is a {@link StorageServiceParameterCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link StorageServiceParameterDTO} or a {@link Page} of {@link StorageServiceParameterDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class StorageServiceParameterQueryService extends QueryService<StorageServiceParameter> {

    private final Logger log = LoggerFactory.getLogger(StorageServiceParameterQueryService.class);

    private final StorageServiceParameterRepository storageServiceParameterRepository;

    private final StorageServiceParameterMapper storageServiceParameterMapper;

    public StorageServiceParameterQueryService(StorageServiceParameterRepository storageServiceParameterRepository, StorageServiceParameterMapper storageServiceParameterMapper) {
        this.storageServiceParameterRepository = storageServiceParameterRepository;
        this.storageServiceParameterMapper = storageServiceParameterMapper;
    }

    /**
     * Return a {@link List} of {@link StorageServiceParameterDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<StorageServiceParameterDTO> findByCriteria(StorageServiceParameterCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<StorageServiceParameter> specification = createSpecification(criteria);
        return storageServiceParameterMapper.toDto(storageServiceParameterRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link StorageServiceParameterDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<StorageServiceParameterDTO> findByCriteria(StorageServiceParameterCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<StorageServiceParameter> specification = createSpecification(criteria);
        return storageServiceParameterRepository.findAll(specification, page)
            .map(storageServiceParameterMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(StorageServiceParameterCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<StorageServiceParameter> specification = createSpecification(criteria);
        return storageServiceParameterRepository.count(specification);
    }

    /**
     * Function to convert {@link StorageServiceParameterCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<StorageServiceParameter> createSpecification(StorageServiceParameterCriteria criteria) {
        Specification<StorageServiceParameter> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), StorageServiceParameter_.id));
            }
            if (criteria.getServiceId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getServiceId(), StorageServiceParameter_.serviceId));
            }
            if (criteria.getParamName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParamName(), StorageServiceParameter_.paramName));
            }
            if (criteria.getParamValue() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParamValue(), StorageServiceParameter_.paramValue));
            }
        }
        return specification;
    }
}
