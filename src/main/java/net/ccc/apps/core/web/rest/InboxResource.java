package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import net.ccc.apps.core.repository.InboxRepository;
import net.ccc.apps.core.service.InboxService;
import net.ccc.apps.core.service.criteria.InboxCriteria;
import net.ccc.apps.core.service.dto.InboxDTO;
import net.ccc.apps.core.service.queryService.InboxQueryService;
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

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.Inbox}.
 */
@RestController
@RequestMapping("/api")
public class InboxResource {

    private final Logger log = LoggerFactory.getLogger(InboxResource.class);

    private static final String ENTITY_NAME = "appscoreapiInbox";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InboxService inboxService;

    private final InboxRepository inboxRepository;

    private final InboxQueryService inboxQueryService;

    public InboxResource(InboxService inboxService, InboxRepository inboxRepository, InboxQueryService inboxQueryService) {
        this.inboxService = inboxService;
        this.inboxRepository = inboxRepository;
        this.inboxQueryService = inboxQueryService;
    }

    /**
     * {@code POST  /inboxes} : Create a new inbox.
     *
     * @param inboxDTO the inboxDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inboxDTO, or with status {@code 400 (Bad Request)} if the inbox has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inboxes")
    public ResponseEntity<InboxDTO> createInbox(@Valid @RequestBody InboxDTO inboxDTO) throws URISyntaxException {
        log.debug("REST request to save Inbox : {}", inboxDTO);
        if (inboxDTO.getId() != null) {
            throw new BadRequestAlertException("A new inbox cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InboxDTO result = inboxService.save(inboxDTO);
        return ResponseEntity
            .created(new URI("/api/inboxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inboxes/:id} : Updates an existing inbox.
     *
     * @param id the id of the inboxDTO to save.
     * @param inboxDTO the inboxDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inboxDTO,
     * or with status {@code 400 (Bad Request)} if the inboxDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inboxDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inboxes")
    public ResponseEntity<InboxDTO> updateInbox(@RequestBody InboxDTO inboxDTO) throws URISyntaxException {
        log.debug("REST request to update Inbox : {}", inboxDTO);
        if (inboxDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InboxDTO result = inboxService.save(inboxDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inboxDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /inboxes/:id} : Partial updates given fields of an existing inbox, field will ignore if it is null
     *
     * @param id the id of the inboxDTO to save.
     * @param inboxDTO the inboxDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inboxDTO,
     * or with status {@code 400 (Bad Request)} if the inboxDTO is not valid,
     * or with status {@code 404 (Not Found)} if the inboxDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the inboxDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/inboxes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InboxDTO> partialUpdateInbox(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody InboxDTO inboxDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Inbox partially : {}, {}", id, inboxDTO);
        if (inboxDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, inboxDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!inboxRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InboxDTO> result = inboxService.partialUpdate(inboxDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inboxDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /inboxes} : get all the inboxes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inboxes in body.
     */
    @GetMapping("/inboxes")
    public ResponseEntity<List<InboxDTO>> getAllInboxes(InboxCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Inboxes by criteria: {}", criteria);
        Page<InboxDTO> page = inboxQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inboxes/count} : count all the inboxes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/inboxes/count")
    public ResponseEntity<Long> countInboxes(InboxCriteria criteria) {
        log.debug("REST request to count Inboxes by criteria: {}", criteria);
        return ResponseEntity.ok().body(inboxQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /inboxes/:id} : get the "id" inbox.
     *
     * @param id the id of the inboxDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inboxDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inboxes/{id}")
    public ResponseEntity<InboxDTO> getInbox(@PathVariable Long id) {
        log.debug("REST request to get Inbox : {}", id);
        Optional<InboxDTO> inboxDTO = inboxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inboxDTO);
    }

    /**
     * {@code DELETE  /inboxes/:id} : delete the "id" inbox.
     *
     * @param id the id of the inboxDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inboxes/{id}")
    public ResponseEntity<Void> deleteInbox(@PathVariable Long id) {
        log.debug("REST request to delete Inbox : {}", id);
        inboxService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
