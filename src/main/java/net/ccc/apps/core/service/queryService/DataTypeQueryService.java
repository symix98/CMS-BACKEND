package net.ccc.apps.core.service.queryService;

import java.util.List;

import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.DataType;
import net.ccc.apps.core.repository.DataTypeRepository;
import net.ccc.apps.core.service.criteria.DataTypeCriteria;
import net.ccc.apps.core.service.dto.DataTypeDTO;
import net.ccc.apps.core.service.mapper.DataTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link DataType} entities in the database.
 * The main input is a {@link DataTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DataTypeDTO} or a {@link Page} of {@link DataTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DataTypeQueryService extends QueryService<DataType> {

    private final Logger log = LoggerFactory.getLogger(DataTypeQueryService.class);

    private final DataTypeRepository dataTypeRepository;

    private final DataTypeMapper dataTypeMapper;

    public DataTypeQueryService(DataTypeRepository dataTypeRepository, DataTypeMapper dataTypeMapper) {
        this.dataTypeRepository = dataTypeRepository;
        this.dataTypeMapper = dataTypeMapper;
    }

    /**
     * Return a {@link List} of {@link DataTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DataTypeDTO> findByCriteria(DataTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DataType> specification = createSpecification(criteria);
        return dataTypeMapper.toDto(dataTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DataTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DataTypeDTO> findByCriteria(DataTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DataType> specification = createSpecification(criteria);
        return dataTypeRepository.findAll(specification, page).map(dataTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DataTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DataType> specification = createSpecification(criteria);
        return dataTypeRepository.count(specification);
    }

    /**
     * Function to convert {@link DataTypeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DataType> createSpecification(DataTypeCriteria criteria) {
        Specification<DataType> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DataType_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), DataType_.name));
            }
            if (criteria.getComplex() != null) {
                specification = specification.and(buildSpecification(criteria.getComplex(), DataType_.complex));
            }
        }
        return specification;
    }
}
