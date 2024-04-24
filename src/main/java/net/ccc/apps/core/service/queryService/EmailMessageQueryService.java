package net.ccc.apps.core.service.queryService;


import net.ccc.apps.core.domain.EmailMessage;
import net.ccc.apps.core.domain.EmailMessage_;
import net.ccc.apps.core.domain.MailRecipient_;
import net.ccc.apps.core.repository.EmailMessageRepository;
import net.ccc.apps.core.service.criteria.EmailMessageCriteria;
import net.ccc.apps.core.service.dto.EmailMessageDTO;
import net.ccc.apps.core.service.mapper.EmailMessageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

import javax.persistence.criteria.JoinType;
import java.util.List;

/**
 * Service for executing complex queries for {@link EmailMessage} entities in the database.
 * The main input is a {@link EmailMessageCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EmailMessageDTO} or a {@link Page} of {@link EmailMessageDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EmailMessageQueryService extends QueryService<EmailMessage> {

    private final Logger log = LoggerFactory.getLogger(EmailMessageQueryService.class);

    private final EmailMessageRepository emailMessageRepository;

    private final EmailMessageMapper emailMessageMapper;

    public EmailMessageQueryService(EmailMessageRepository emailMessageRepository, EmailMessageMapper emailMessageMapper) {
        this.emailMessageRepository = emailMessageRepository;
        this.emailMessageMapper = emailMessageMapper;
    }

    /**
     * Return a {@link List} of {@link EmailMessageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EmailMessageDTO> findByCriteria(EmailMessageCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EmailMessage> specification = createSpecification(criteria);
        return emailMessageMapper.toDto(emailMessageRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EmailMessageDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EmailMessageDTO> findByCriteria(EmailMessageCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EmailMessage> specification = createSpecification(criteria);
        return emailMessageRepository.findAll(specification, page).map(emailMessageMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EmailMessageCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EmailMessage> specification = createSpecification(criteria);
        return emailMessageRepository.count(specification);
    }

    /**
     * Function to convert {@link EmailMessageCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<EmailMessage> createSpecification(EmailMessageCriteria criteria) {
        Specification<EmailMessage> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), EmailMessage_.id));
            }
            if (criteria.getFrom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFrom(), EmailMessage_.from));
            }
            if (criteria.getSubject() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSubject(), EmailMessage_.subject));
            }
            if (criteria.getContent() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContent(), EmailMessage_.content));
            }
            if (criteria.getReferenceId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReferenceId(), EmailMessage_.referenceId));
            }
            if (criteria.getIsSent() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSent(), EmailMessage_.isSent));
            }
            if (criteria.getSentTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSentTime(), EmailMessage_.sentTime));
            }
            if (criteria.getComment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComment(), EmailMessage_.comment));
            }
            if (criteria.getRecipientId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRecipientId(),
                            root -> root.join(EmailMessage_.recipients, JoinType.LEFT).get(MailRecipient_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
