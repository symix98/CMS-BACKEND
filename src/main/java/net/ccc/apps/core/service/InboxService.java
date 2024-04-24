package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.Inbox;
import net.ccc.apps.core.repository.InboxRepository;
import net.ccc.apps.core.service.dto.InboxDTO;
import net.ccc.apps.core.service.mapper.InboxMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Inbox}.
 */
@Service
@Transactional
public class InboxService {

    private final Logger log = LoggerFactory.getLogger(InboxService.class);

    private final InboxRepository inboxRepository;

    private final InboxMapper inboxMapper;

    public InboxService(InboxRepository inboxRepository, InboxMapper inboxMapper) {
        this.inboxRepository = inboxRepository;
        this.inboxMapper = inboxMapper;
    }

    /**
     * Save a inbox.
     *
     * @param inboxDTO the entity to save.
     * @return the persisted entity.
     */
    public InboxDTO save(InboxDTO inboxDTO) {
        log.debug("Request to save Inbox : {}", inboxDTO);
        Inbox inbox = inboxMapper.toEntity(inboxDTO);
        inbox = inboxRepository.save(inbox);
        return inboxMapper.toDto(inbox);
    }

    /**
     * Partially update a inbox.
     *
     * @param inboxDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<InboxDTO> partialUpdate(InboxDTO inboxDTO) {
        log.debug("Request to partially update Inbox : {}", inboxDTO);

        return inboxRepository
            .findById(inboxDTO.getId())
            .map(existingInbox -> {
                inboxMapper.partialUpdate(existingInbox, inboxDTO);

                return existingInbox;
            })
            .map(inboxRepository::save)
            .map(inboxMapper::toDto);
    }

    /**
     * Get all the inboxes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InboxDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Inboxes");
        return inboxRepository.findAll(pageable).map(inboxMapper::toDto);
    }

    /**
     * Get one inbox by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InboxDTO> findOne(Long id) {
        log.debug("Request to get Inbox : {}", id);
        return inboxRepository.findById(id).map(inboxMapper::toDto);
    }

    /**
     * Delete the inbox by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Inbox : {}", id);
        inboxRepository.deleteById(id);
    }
}
