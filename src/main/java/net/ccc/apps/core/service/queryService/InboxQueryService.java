package net.ccc.apps.core.service.queryService;

import java.util.List;
import javax.persistence.criteria.JoinType;
import net.ccc.apps.core.domain.*; // for static metamodels
import net.ccc.apps.core.domain.Inbox;
import net.ccc.apps.core.repository.InboxRepository;
import net.ccc.apps.core.service.UserService;
import net.ccc.apps.core.service.criteria.InboxCriteria;
import net.ccc.apps.core.service.dto.InboxDTO;
import net.ccc.apps.core.service.mapper.InboxMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import tech.jhipster.service.filter.StringFilter;

/**
 * Service for executing complex queries for {@link Inbox} entities in the database.
 * The main input is a {@link InboxCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InboxDTO} or a {@link Page} of {@link InboxDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InboxQueryService extends QueryService<Inbox> {

    private final Logger log = LoggerFactory.getLogger(InboxQueryService.class);

    @Autowired
    private InboxRepository inboxRepository;

    @Autowired
    private InboxMapper inboxMapper;

    /**
     * Return a {@link List} of {@link InboxDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<InboxDTO> findByCriteria(InboxCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        StringFilter assignedToFilter = new StringFilter();
        assignedToFilter.setEquals(UserService.currentLoggedInUser().getEmail());
        criteria.setAssignedToId(assignedToFilter);
        final Specification<Inbox> specification = createSpecification(criteria);
        return inboxMapper.toDto(inboxRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link InboxDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page     The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<InboxDTO> findByCriteria(InboxCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Inbox> specification = createSpecification(criteria);
        return inboxRepository.findAll(specification, page).map(inboxMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(InboxCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        StringFilter assignedToFilter = new StringFilter();
        assignedToFilter.setEquals(UserService.currentLoggedInUser().getEmail());
        criteria.setAssignedToId(assignedToFilter);
        final Specification<Inbox> specification = createSpecification(criteria);
        return inboxRepository.count(specification);
    }

    /**
     * Function to convert {@link InboxCriteria} to a {@link Specification}
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Inbox> createSpecification(InboxCriteria criteria) {
        Specification<Inbox> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Inbox_.id));
            }
            if (criteria.getDateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTime(), Inbox_.dateTime));
            }
            if (criteria.getFormId() != null) {
                specification = specification.and(buildSpecification(criteria.getFormId(), Inbox_.formId));
            }
            if (criteria.getFormType() != null) {
                specification = specification.and(buildSpecification(criteria.getFormType(), Inbox_.formType));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Inbox_.title));
            }
            if (criteria.getMessage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMessage(), Inbox_.message));
            }
            if (criteria.getUnread() != null) {
                specification = specification.and(buildSpecification(criteria.getUnread(), Inbox_.unread));
            }
            if (criteria.getReadTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReadTime(), Inbox_.readTime));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Inbox_.description));
            }
            if (criteria.getAssignedToId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getAssignedToId(),
                            root -> root.join(Inbox_.assignedTo, JoinType.LEFT).get(AppUser_.userId)
                        )
                    );
            }
            if (criteria.getAssignedById() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getAssignedById(),
                            root -> root.join(Inbox_.assignedBy, JoinType.LEFT).get(AppUser_.userId)
                        )
                    );
            }
        }
        return specification;
    }
}
