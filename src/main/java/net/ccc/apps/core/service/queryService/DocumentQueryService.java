package net.ccc.apps.core.service.queryService;

import java.util.List;

import javax.persistence.criteria.JoinType;

import net.ccc.apps.core.domain.Document;
import net.ccc.apps.core.domain.Document_;
import net.ccc.apps.core.repository.DocumentRepository;
import net.ccc.apps.core.service.criteria.DocumentCriteria;
import net.ccc.apps.core.service.dto.DocumentDTO;
import net.ccc.apps.core.service.mapper.DocumentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;


/**
 * Service for executing complex queries for {@link Document} entities in the database.
 * The main input is a {@link DocumentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DocumentDTO} or a {@link Page} of {@link DocumentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DocumentQueryService extends QueryService<Document> {

    private final Logger log = LoggerFactory.getLogger(DocumentQueryService.class);

    private final DocumentRepository documentRepository;

    private final DocumentMapper documentMapper;

    public DocumentQueryService(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    /**
     * Return a {@link List} of {@link DocumentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DocumentDTO> findByCriteria(DocumentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Document> specification = createSpecification(criteria);
        return documentMapper.toDto(documentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DocumentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DocumentDTO> findByCriteria(DocumentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Document> specification = createSpecification(criteria);
        return documentRepository.findAll(specification, page)
            .map(documentMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DocumentCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Document> specification = createSpecification(criteria);
        return documentRepository.count(specification);
    }

    /**
     * Function to convert {@link DocumentCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Document> createSpecification(DocumentCriteria criteria) {
        Specification<Document> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDocumentId(), Document_.documentId));
            }
            if (criteria.getServiceId() != null) {
                specification = specification.and(buildSpecification(criteria.getServiceId(), Document_.serviceId));
            }
            if (criteria.getServiceURI() != null) {
                specification = specification.and(buildStringSpecification(criteria.getServiceURI(), Document_.serviceURI));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Document_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Document_.description));
            }
            if (criteria.getVersion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVersion(), Document_.version));
            }
            if (criteria.getMimeType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMimeType(), Document_.mimeType));
            }
            if (criteria.getFolder() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFolder(), Document_.folder));
            }
            if (criteria.getFormType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFormType(), Document_.formType));
            }
            if (criteria.getFormId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFormId(), Document_.formId));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), Document_.creationDate));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Document_.createdBy));
            }
            if (criteria.getModificationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModificationDate(), Document_.modificationDate));
            }
            if (criteria.getModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifiedBy(), Document_.modifiedBy));
            }
        }
        return specification;
    }
}
