package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.MessageTemplate;
import net.ccc.apps.core.repository.MessageTemplateRepository;
import net.ccc.apps.core.service.dto.MessageTemplateDTO;
import net.ccc.apps.core.service.mapper.MessageTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MessageTemplate}.
 */
@Service
@Transactional
public class MessageTemplateService {

    private final Logger log = LoggerFactory.getLogger(MessageTemplateService.class);

    private final MessageTemplateRepository messageTemplateRepository;

    private final MessageTemplateMapper messageTemplateMapper;

    public MessageTemplateService(MessageTemplateRepository messageTemplateRepository, MessageTemplateMapper messageTemplateMapper) {
        this.messageTemplateRepository = messageTemplateRepository;
        this.messageTemplateMapper = messageTemplateMapper;
    }

    /**
     * Save a messageTemplate.
     *
     * @param messageTemplateDTO the entity to save.
     * @return the persisted entity.
     */
    public MessageTemplateDTO save(MessageTemplateDTO messageTemplateDTO) {
        log.debug("Request to save MessageTemplate : {}", messageTemplateDTO);
        MessageTemplate messageTemplate = messageTemplateMapper.toEntity(messageTemplateDTO);
        messageTemplate = messageTemplateRepository.save(messageTemplate);
        return messageTemplateMapper.toDto(messageTemplate);
    }

    /**
     * Partially update a messageTemplate.
     *
     * @param messageTemplateDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MessageTemplateDTO> partialUpdate(MessageTemplateDTO messageTemplateDTO) {
        log.debug("Request to partially update MessageTemplate : {}", messageTemplateDTO);

        return messageTemplateRepository
            .findById(messageTemplateDTO.getId())
            .map(existingMessageTemplate -> {
                messageTemplateMapper.partialUpdate(existingMessageTemplate, messageTemplateDTO);

                return existingMessageTemplate;
            })
            .map(messageTemplateRepository::save)
            .map(messageTemplateMapper::toDto);
    }

    /**
     * Get all the messageTemplates.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MessageTemplateDTO> findAll() {
        log.debug("Request to get all MessageTemplates");
        return messageTemplateRepository
            .findAll()
            .stream()
            .map(messageTemplateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one messageTemplate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MessageTemplateDTO> findOne(Long id) {
        log.debug("Request to get MessageTemplate : {}", id);
        return messageTemplateRepository.findById(id).map(messageTemplateMapper::toDto);
    }

    /**
     * Delete the messageTemplate by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MessageTemplate : {}", id);
        messageTemplateRepository.deleteById(id);
    }
}
