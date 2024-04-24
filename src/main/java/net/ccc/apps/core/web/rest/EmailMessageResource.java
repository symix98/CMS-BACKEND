package net.ccc.apps.core.web.rest;


import net.ccc.apps.core.repository.EmailMessageRepository;
import net.ccc.apps.core.service.EmailMessageService;
import net.ccc.apps.core.service.criteria.EmailMessageCriteria;
import net.ccc.apps.core.service.dto.EmailMessageDTO;
import net.ccc.apps.core.service.queryService.EmailMessageQueryService;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * REST controller for managing {@link com.ccc.app.lms.domain.EmailMessage}.
 */
@RestController
@RequestMapping("/api/emailsToSend")

public class EmailMessageResource {

    private final Logger log = LoggerFactory.getLogger(EmailMessageResource.class);

    private static final String ENTITY_NAME = "emailMessage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmailMessageService emailMessageService;

    private final EmailMessageRepository emailMessageRepository;

    private final EmailMessageQueryService emailMessageQueryService;

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> future;
    CountDownLatch countDownLatch = new CountDownLatch(1);

    public EmailMessageResource(
        EmailMessageService emailMessageService,
        EmailMessageRepository emailMessageRepository,
        EmailMessageQueryService emailMessageQueryService
    ) {
        this.emailMessageService = emailMessageService;
        this.emailMessageRepository = emailMessageRepository;
        this.emailMessageQueryService = emailMessageQueryService;
    }

    /**
     * {@code POST  /email-messages} : Create a new emailMessage.
     *
     * @param emailMessageDTO the emailMessageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new emailMessageDTO, or with status {@code 400 (Bad Request)} if the emailMessage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/email-messages")
    public ResponseEntity<EmailMessageDTO> createEmailMessage(@RequestBody EmailMessageDTO emailMessageDTO) throws URISyntaxException {
        log.debug("REST request to save EmailMessage : {}", emailMessageDTO);
        if (emailMessageDTO.getId() != null) {
            throw new BadRequestAlertException("A new emailMessage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmailMessageDTO result = emailMessageService.save(emailMessageDTO);
        return ResponseEntity
            .created(new URI("/api/email-messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /email-messages/:id} : Updates an existing emailMessage.
     *
     * @param id the id of the emailMessageDTO to save.
     * @param emailMessageDTO the emailMessageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emailMessageDTO,
     * or with status {@code 400 (Bad Request)} if the emailMessageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the emailMessageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/email-messages/{id}")
    public ResponseEntity<EmailMessageDTO> updateEmailMessage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EmailMessageDTO emailMessageDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EmailMessage : {}, {}", id, emailMessageDTO);
        if (emailMessageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, emailMessageDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!emailMessageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EmailMessageDTO result = emailMessageService.save(emailMessageDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, emailMessageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /email-messages/:id} : Partial updates given fields of an existing emailMessage, field will ignore if it is null
     *
     * @param id the id of the emailMessageDTO to save.
     * @param emailMessageDTO the emailMessageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated emailMessageDTO,
     * or with status {@code 400 (Bad Request)} if the emailMessageDTO is not valid,
     * or with status {@code 404 (Not Found)} if the emailMessageDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the emailMessageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/email-messages/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<EmailMessageDTO> partialUpdateEmailMessage(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EmailMessageDTO emailMessageDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EmailMessage partially : {}, {}", id, emailMessageDTO);
        if (emailMessageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, emailMessageDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!emailMessageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EmailMessageDTO> result = emailMessageService.partialUpdate(emailMessageDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, emailMessageDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /email-messages} : get all the emailMessages.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of emailMessages in body.
     */
    @GetMapping("/email-messages")
    public ResponseEntity<List<EmailMessageDTO>> getAllEmailMessages(EmailMessageCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EmailMessages by criteria: {}", criteria);
        Page<EmailMessageDTO> page = emailMessageQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /email-messages/count} : count all the emailMessages.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/email-messages/count")
    public ResponseEntity<Long> countEmailMessages(EmailMessageCriteria criteria) {
        log.debug("REST request to count EmailMessages by criteria: {}", criteria);
        return ResponseEntity.ok().body(emailMessageQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /email-messages/:id} : get the "id" emailMessage.
     *
     * @param id the id of the emailMessageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the emailMessageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/email-messages/{id}")
    public ResponseEntity<EmailMessageDTO> getEmailMessage(@PathVariable Long id) {
        log.debug("REST request to get EmailMessage : {}", id);
        Optional<EmailMessageDTO> emailMessageDTO = emailMessageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(emailMessageDTO);
    }

    /**
     * {@code DELETE  /email-messages/:id} : delete the "id" emailMessage.
     *
     * @param id the id of the emailMessageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/email-messages/{id}")
    public ResponseEntity<Void> deleteEmailMessage(@PathVariable Long id) {
        log.debug("REST request to delete EmailMessage : {}", id);
        emailMessageService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }






}
