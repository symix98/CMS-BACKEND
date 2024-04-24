package net.ccc.apps.core.service.queryService;

import java.util.List;

import javax.persistence.criteria.JoinType;

import net.ccc.apps.core.domain.StorageService;
import net.ccc.apps.core.domain.StorageService_;
import net.ccc.apps.core.repository.StorageServiceRepository;
import net.ccc.apps.core.service.criteria.StorageServiceCriteria;
import net.ccc.apps.core.service.dto.StorageServiceDTO;
import net.ccc.apps.core.service.mapper.StorageServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link StorageService} entities in the database.
 * The main input is a {@link StorageServiceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link StorageServiceDTO} or a {@link Page} of {@link StorageServiceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class StorageServiceQueryService extends QueryService<StorageService> {

    private final Logger log = LoggerFactory.getLogger(StorageServiceQueryService.class);

    private final StorageServiceRepository storageServiceRepository;

    private final StorageServiceMapper storageServiceMapper;

    public StorageServiceQueryService(StorageServiceRepository storageServiceRepository, StorageServiceMapper storageServiceMapper) {
        this.storageServiceRepository = storageServiceRepository;
        this.storageServiceMapper = storageServiceMapper;
    }

    /**
     * Return a {@link List} of {@link StorageServiceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<StorageServiceDTO> findByCriteria(StorageServiceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<StorageService> specification = createSpecification(criteria);
        return storageServiceMapper.toDto(storageServiceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link StorageServiceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<StorageServiceDTO> findByCriteria(StorageServiceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<StorageService> specification = createSpecification(criteria);
        return storageServiceRepository.findAll(specification, page)
            .map(storageServiceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(StorageServiceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<StorageService> specification = createSpecification(criteria);
        return storageServiceRepository.count(specification);
    }

    /**
     * Function to convert {@link StorageServiceCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<StorageService> createSpecification(StorageServiceCriteria criteria) {
        Specification<StorageService> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getServiceId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getServiceId(), StorageService_.serviceId));
            }
            if (criteria.getSecret() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSecret(), StorageService_.secret));
            }
            if (criteria.getHostname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHostname(), StorageService_.hostname));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), StorageService_.name));
            }
            if (criteria.getDefaultValue() != null) {
                specification = specification.and(buildSpecification(criteria.getDefaultValue(), StorageService_.defaultValue));
            }
        }
        return specification;
    }
}
