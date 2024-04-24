package net.ccc.apps.core.service.queryService;


import net.ccc.apps.core.domain.EmailMessage_;
import net.ccc.apps.core.domain.MailRecipient;
import net.ccc.apps.core.domain.MailRecipient_;
import net.ccc.apps.core.repository.MailRecipientRepository;
import net.ccc.apps.core.service.criteria.MailRecipientCriteria;
import net.ccc.apps.core.service.dto.MailRecipientDTO;

import net.ccc.apps.core.service.mapper.MailRecipientMapper;
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
 * Service for executing complex queries for {@link MailRecipient} entities in the database.
 * The main input is a {@link MailRecipientCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MailRecipientDTO} or a {@link Page} of {@link MailRecipientDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MailRecipientQueryService extends QueryService<MailRecipient> {

    private final Logger log = LoggerFactory.getLogger(MailRecipientQueryService.class);

    private final MailRecipientRepository mailRecipientRepository;

    private final MailRecipientMapper mailRecipientMapper;

    public MailRecipientQueryService(MailRecipientRepository mailRecipientRepository, MailRecipientMapper mailRecipientMapper) {
        this.mailRecipientRepository = mailRecipientRepository;
        this.mailRecipientMapper = mailRecipientMapper;
    }

    /**
     * Return a {@link List} of {@link MailRecipientDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MailRecipientDTO> findByCriteria(MailRecipientCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MailRecipient> specification = createSpecification(criteria);
        return mailRecipientMapper.toDto(mailRecipientRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MailRecipientDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MailRecipientDTO> findByCriteria(MailRecipientCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MailRecipient> specification = createSpecification(criteria);
        return mailRecipientRepository.findAll(specification, page).map(mailRecipientMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MailRecipientCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MailRecipient> specification = createSpecification(criteria);
        return mailRecipientRepository.count(specification);
    }

    /**
     * Function to convert {@link MailRecipientCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<MailRecipient> createSpecification(MailRecipientCriteria criteria) {
        Specification<MailRecipient> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), MailRecipient_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), MailRecipient_.name));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), MailRecipient_.email));
            }
            if (criteria.getRecipientsId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getRecipientsId(),
                            root -> root.join(MailRecipient_.recipients, JoinType.LEFT).get(EmailMessage_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
