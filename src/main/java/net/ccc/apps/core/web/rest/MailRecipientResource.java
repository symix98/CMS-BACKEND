package net.ccc.apps.core.web.rest;


import net.ccc.apps.core.repository.MailRecipientRepository;
import net.ccc.apps.core.service.MailRecipientService;
import net.ccc.apps.core.service.criteria.MailRecipientCriteria;
import net.ccc.apps.core.service.dto.MailRecipientDTO;
import net.ccc.apps.core.service.queryService.MailRecipientQueryService;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.MailRecipient}.
 */
@RestController
@RequestMapping("/api")
public class MailRecipientResource {

    private final Logger log = LoggerFactory.getLogger(MailRecipientResource.class);

    private static final String ENTITY_NAME = "mailRecipient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MailRecipientService mailRecipientService;

    private final MailRecipientRepository mailRecipientRepository;

    private final MailRecipientQueryService mailRecipientQueryService;

    public MailRecipientResource(
        MailRecipientService mailRecipientService,
        MailRecipientRepository mailRecipientRepository,
        MailRecipientQueryService mailRecipientQueryService
    ) {
        this.mailRecipientService = mailRecipientService;
        this.mailRecipientRepository = mailRecipientRepository;
        this.mailRecipientQueryService = mailRecipientQueryService;
    }

    /**
     * {@code POST  /mail-recipients} : Create a new mailRecipient.
     *
     * @param mailRecipientDTO the mailRecipientDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mailRecipientDTO, or with status {@code 400 (Bad Request)} if the mailRecipient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mail-recipients")
    public ResponseEntity<MailRecipientDTO> createMailRecipient(@RequestBody MailRecipientDTO mailRecipientDTO) throws URISyntaxException {
        log.debug("REST request to save MailRecipient : {}", mailRecipientDTO);
        if (mailRecipientDTO.getId() != null) {
            throw new BadRequestAlertException("A new mailRecipient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MailRecipientDTO result = mailRecipientService.save(mailRecipientDTO);
        return ResponseEntity
            .created(new URI("/api/mail-recipients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mail-recipients/:id} : Updates an existing mailRecipient.
     *
     * @param id the id of the mailRecipientDTO to save.
     * @param mailRecipientDTO the mailRecipientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mailRecipientDTO,
     * or with status {@code 400 (Bad Request)} if the mailRecipientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mailRecipientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mail-recipients/{id}")
    public ResponseEntity<MailRecipientDTO> updateMailRecipient(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MailRecipientDTO mailRecipientDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MailRecipient : {}, {}", id, mailRecipientDTO);
        if (mailRecipientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mailRecipientDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mailRecipientRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MailRecipientDTO result = mailRecipientService.save(mailRecipientDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mailRecipientDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /mail-recipients/:id} : Partial updates given fields of an existing mailRecipient, field will ignore if it is null
     *
     * @param id the id of the mailRecipientDTO to save.
     * @param mailRecipientDTO the mailRecipientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mailRecipientDTO,
     * or with status {@code 400 (Bad Request)} if the mailRecipientDTO is not valid,
     * or with status {@code 404 (Not Found)} if the mailRecipientDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the mailRecipientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/mail-recipients/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<MailRecipientDTO> partialUpdateMailRecipient(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MailRecipientDTO mailRecipientDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update MailRecipient partially : {}, {}", id, mailRecipientDTO);
        if (mailRecipientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mailRecipientDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mailRecipientRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MailRecipientDTO> result = mailRecipientService.partialUpdate(mailRecipientDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mailRecipientDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /mail-recipients} : get all the mailRecipients.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mailRecipients in body.
     */
    @GetMapping("/mail-recipients")
    public ResponseEntity<List<MailRecipientDTO>> getAllMailRecipients(MailRecipientCriteria criteria) {
        log.debug("REST request to get MailRecipients by criteria: {}", criteria);
        List<MailRecipientDTO> entityList = mailRecipientQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /mail-recipients/count} : count all the mailRecipients.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/mail-recipients/count")
    public ResponseEntity<Long> countMailRecipients(MailRecipientCriteria criteria) {
        log.debug("REST request to count MailRecipients by criteria: {}", criteria);
        return ResponseEntity.ok().body(mailRecipientQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /mail-recipients/:id} : get the "id" mailRecipient.
     *
     * @param id the id of the mailRecipientDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mailRecipientDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mail-recipients/{id}")
    public ResponseEntity<MailRecipientDTO> getMailRecipient(@PathVariable Long id) {
        log.debug("REST request to get MailRecipient : {}", id);
        Optional<MailRecipientDTO> mailRecipientDTO = mailRecipientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mailRecipientDTO);
    }

    /**
     * {@code DELETE  /mail-recipients/:id} : delete the "id" mailRecipient.
     *
     * @param id the id of the mailRecipientDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mail-recipients/{id}")
    public ResponseEntity<Void> deleteMailRecipient(@PathVariable Long id) {
        log.debug("REST request to delete MailRecipient : {}", id);
        mailRecipientService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
