package net.ccc.apps.core.service;


import net.ccc.apps.core.domain.MailRecipient;
import net.ccc.apps.core.repository.MailRecipientRepository;
import net.ccc.apps.core.service.dto.MailRecipientDTO;
import net.ccc.apps.core.service.mapper.MailRecipientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MailRecipient}.
 */
@Service
@Transactional
public class MailRecipientService {

    private final Logger log = LoggerFactory.getLogger(MailRecipientService.class);

    private final MailRecipientRepository mailRecipientRepository;

    private final MailRecipientMapper mailRecipientMapper;

    public MailRecipientService(MailRecipientRepository mailRecipientRepository, MailRecipientMapper mailRecipientMapper) {
        this.mailRecipientRepository = mailRecipientRepository;
        this.mailRecipientMapper = mailRecipientMapper;
    }

    /**
     * Save a mailRecipient.
     *
     * @param mailRecipientDTO the entity to save.
     * @return the persisted entity.
     */
    public MailRecipientDTO save(MailRecipientDTO mailRecipientDTO) {
        log.debug("Request to save MailRecipient : {}", mailRecipientDTO);
        MailRecipient mailRecipient = mailRecipientMapper.toEntity(mailRecipientDTO);
        mailRecipient = mailRecipientRepository.save(mailRecipient);
        return mailRecipientMapper.toDto(mailRecipient);
    }

    /**
     * Partially update a mailRecipient.
     *
     * @param mailRecipientDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MailRecipientDTO> partialUpdate(MailRecipientDTO mailRecipientDTO) {
        log.debug("Request to partially update MailRecipient : {}", mailRecipientDTO);

        return mailRecipientRepository
            .findById(mailRecipientDTO.getId())
            .map(
                existingMailRecipient -> {
                    mailRecipientMapper.partialUpdate(existingMailRecipient, mailRecipientDTO);
                    return existingMailRecipient;
                }
            )
            .map(mailRecipientRepository::save)
            .map(mailRecipientMapper::toDto);
    }

    /**
     * Get all the mailRecipients.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MailRecipientDTO> findAll() {
        log.debug("Request to get all MailRecipients");
        return mailRecipientRepository.findAll().stream().map(mailRecipientMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one mailRecipient by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MailRecipientDTO> findOne(Long id) {
        log.debug("Request to get MailRecipient : {}", id);
        return mailRecipientRepository.findById(id).map(mailRecipientMapper::toDto);
    }

    /**
     * Delete the mailRecipient by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MailRecipient : {}", id);
        mailRecipientRepository.deleteById(id);
    }
}
