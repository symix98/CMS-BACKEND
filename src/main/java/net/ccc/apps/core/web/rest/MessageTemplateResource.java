package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.ccc.apps.core.repository.MessageTemplateRepository;
import net.ccc.apps.core.service.queryService.MessageTemplateQueryService;
import net.ccc.apps.core.service.MessageTemplateService;
import net.ccc.apps.core.service.criteria.MessageTemplateCriteria;
import net.ccc.apps.core.service.dto.MessageTemplateDTO;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.MessageTemplate}.
 */
@RestController
@RequestMapping("/api")
public class MessageTemplateResource {

    private final Logger log = LoggerFactory.getLogger(MessageTemplateResource.class);

    private static final String ENTITY_NAME = "appscoreapiMessageTemplate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MessageTemplateService messageTemplateService;

    private final MessageTemplateRepository messageTemplateRepository;

    private final MessageTemplateQueryService messageTemplateQueryService;

    public MessageTemplateResource(
        MessageTemplateService messageTemplateService,
        MessageTemplateRepository messageTemplateRepository,
        MessageTemplateQueryService messageTemplateQueryService
    ) {
        this.messageTemplateService = messageTemplateService;
        this.messageTemplateRepository = messageTemplateRepository;
        this.messageTemplateQueryService = messageTemplateQueryService;
    }

    /**
     * {@code POST  /message-templates} : Create a new messageTemplate.
     *
     * @param messageTemplateDTO the messageTemplateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageTemplateDTO, or with status {@code 400 (Bad Request)} if the messageTemplate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/message-templates")
    public ResponseEntity<MessageTemplateDTO> createMessageTemplate(@RequestBody MessageTemplateDTO messageTemplateDTO)
        throws URISyntaxException {
        log.debug("REST request to save MessageTemplate : {}", messageTemplateDTO);
        if (messageTemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new messageTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MessageTemplateDTO result = messageTemplateService.save(messageTemplateDTO);
        return ResponseEntity
            .created(new URI("/api/message-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /message-templates/:id} : Updates an existing messageTemplate.
     *
     * @param id the id of the messageTemplateDTO to save.
     * @param messageTemplateDTO the messageTemplateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageTemplateDTO,
     * or with status {@code 400 (Bad Request)} if the messageTemplateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the messageTemplateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/message-templates/{id}")
    public ResponseEntity<MessageTemplateDTO> updateMessageTemplate(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MessageTemplateDTO messageTemplateDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MessageTemplate : {}, {}", id, messageTemplateDTO);
        if (messageTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, messageTemplateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!messageTemplateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MessageTemplateDTO result = messageTemplateService.save(messageTemplateDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, messageTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /message-templates/:id} : Partial updates given fields of an existing messageTemplate, field will ignore if it is null
     *
     * @param id the id of the messageTemplateDTO to save.
     * @param messageTemplateDTO the messageTemplateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messageTemplateDTO,
     * or with status {@code 400 (Bad Request)} if the messageTemplateDTO is not valid,
     * or with status {@code 404 (Not Found)} if the messageTemplateDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the messageTemplateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/message-templates/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MessageTemplateDTO> partialUpdateMessageTemplate(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MessageTemplateDTO messageTemplateDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update MessageTemplate partially : {}, {}", id, messageTemplateDTO);
        if (messageTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, messageTemplateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!messageTemplateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MessageTemplateDTO> result = messageTemplateService.partialUpdate(messageTemplateDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, messageTemplateDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /message-templates} : get all the messageTemplates.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of messageTemplates in body.
     */
    @GetMapping("/message-templates")
    public List<MessageTemplateDTO> getAllMessageTemplates() {
        log.debug("REST request to get all MessageTemplates");
        return messageTemplateService.findAll();
    }

    /**
     * {@code GET  /message-templates/count} : count all the messageTemplates.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/message-templates/count")
    public ResponseEntity<Long> countMessageTemplates(MessageTemplateCriteria criteria) {
        log.debug("REST request to count MessageTemplates by criteria: {}", criteria);
        return ResponseEntity.ok().body(messageTemplateQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /message-templates/:id} : get the "id" messageTemplate.
     *
     * @param id the id of the messageTemplateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the messageTemplateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/message-templates/{id}")
    public ResponseEntity<MessageTemplateDTO> getMessageTemplate(@PathVariable Long id) {
        log.debug("REST request to get MessageTemplate : {}", id);
        Optional<MessageTemplateDTO> messageTemplateDTO = messageTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(messageTemplateDTO);
    }

    /**
     * {@code DELETE  /message-templates/:id} : delete the "id" messageTemplate.
     *
     * @param id the id of the messageTemplateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/message-templates/{id}")
    public ResponseEntity<Void> deleteMessageTemplate(@PathVariable Long id) {
        log.debug("REST request to delete MessageTemplate : {}", id);
        messageTemplateService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
