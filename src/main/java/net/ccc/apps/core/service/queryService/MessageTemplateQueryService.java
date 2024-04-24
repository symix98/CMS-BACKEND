package net.ccc.apps.core.service.queryService;

import java.util.List;

import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.MessageTemplate;
import net.ccc.apps.core.repository.MessageTemplateRepository;
import net.ccc.apps.core.service.criteria.MessageTemplateCriteria;
import net.ccc.apps.core.service.dto.MessageTemplateDTO;
import net.ccc.apps.core.service.mapper.MessageTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link MessageTemplate} entities in the database.
 * The main input is a {@link MessageTemplateCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MessageTemplateDTO} or a {@link Page} of {@link MessageTemplateDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MessageTemplateQueryService extends QueryService<MessageTemplate> {

    private final Logger log = LoggerFactory.getLogger(MessageTemplateQueryService.class);

    private final MessageTemplateRepository messageTemplateRepository;

    private final MessageTemplateMapper messageTemplateMapper;

    public MessageTemplateQueryService(MessageTemplateRepository messageTemplateRepository, MessageTemplateMapper messageTemplateMapper) {
        this.messageTemplateRepository = messageTemplateRepository;
        this.messageTemplateMapper = messageTemplateMapper;
    }

    /**
     * Return a {@link List} of {@link MessageTemplateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MessageTemplateDTO> findByCriteria(MessageTemplateCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MessageTemplate> specification = createSpecification(criteria);
        return messageTemplateMapper.toDto(messageTemplateRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MessageTemplateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MessageTemplateDTO> findByCriteria(MessageTemplateCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MessageTemplate> specification = createSpecification(criteria);
        return messageTemplateRepository.findAll(specification, page).map(messageTemplateMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MessageTemplateCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MessageTemplate> specification = createSpecification(criteria);
        return messageTemplateRepository.count(specification);
    }

    /**
     * Function to convert {@link MessageTemplateCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<MessageTemplate> createSpecification(MessageTemplateCriteria criteria) {
        Specification<MessageTemplate> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), MessageTemplate_.id));
            }
            if (criteria.getMessageId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMessageId(), MessageTemplate_.messageId));
            }
            if (criteria.getSubject() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSubject(), MessageTemplate_.subject));
            }
            if (criteria.getBody() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBody(), MessageTemplate_.body));
            }
        }
        return specification;
    }
}
