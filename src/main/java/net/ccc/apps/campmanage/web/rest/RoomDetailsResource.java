package net.ccc.apps.campmanage.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.ccc.apps.campmanage.domain.RoomDetails;
import net.ccc.apps.campmanage.repository.RoomDetailsRepository;
import net.ccc.apps.campmanage.service.RoomDetailsQueryService;
import net.ccc.apps.campmanage.service.RoomDetailsService;
import net.ccc.apps.campmanage.service.criteria.RoomDetailsCriteria;
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
 * REST controller for managing {@link net.ccc.apps.campmanage.domain.RoomDetails}.
 */
@RestController
@RequestMapping("/api")
public class RoomDetailsResource {

    private final Logger log = LoggerFactory.getLogger(RoomDetailsResource.class);

    private static final String ENTITY_NAME = "campmanagRoomDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RoomDetailsService roomDetailsService;

    private final RoomDetailsRepository roomDetailsRepository;

    private final RoomDetailsQueryService roomDetailsQueryService;

    public RoomDetailsResource(
        RoomDetailsService roomDetailsService,
        RoomDetailsRepository roomDetailsRepository,
        RoomDetailsQueryService roomDetailsQueryService
    ) {
        this.roomDetailsService = roomDetailsService;
        this.roomDetailsRepository = roomDetailsRepository;
        this.roomDetailsQueryService = roomDetailsQueryService;
    }

    /**
     * {@code POST  /room-details} : Create a new roomDetails.
     *
     * @param roomDetails the roomDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roomDetails, or with status {@code 400 (Bad Request)} if the roomDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/room-details")
    public ResponseEntity<RoomDetails> createRoomDetails(@RequestBody RoomDetails roomDetails) throws URISyntaxException {
        log.debug("REST request to save RoomDetails : {}", roomDetails);
        if (roomDetails.getId() != null) {
            throw new BadRequestAlertException("A new roomDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoomDetails result = roomDetailsService.save(roomDetails);
        return ResponseEntity
            .created(new URI("/api/room-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /room-details/:id} : Updates an existing roomDetails.
     *
     * @param id the id of the roomDetails to save.
     * @param roomDetails the roomDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roomDetails,
     * or with status {@code 400 (Bad Request)} if the roomDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the roomDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/room-details/{id}")
    public ResponseEntity<RoomDetails> updateRoomDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RoomDetails roomDetails
    ) throws URISyntaxException {
        log.debug("REST request to update RoomDetails : {}, {}", id, roomDetails);
        if (roomDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, roomDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roomDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RoomDetails result = roomDetailsService.update(roomDetails);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, roomDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /room-details/:id} : Partial updates given fields of an existing roomDetails, field will ignore if it is null
     *
     * @param id the id of the roomDetails to save.
     * @param roomDetails the roomDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roomDetails,
     * or with status {@code 400 (Bad Request)} if the roomDetails is not valid,
     * or with status {@code 404 (Not Found)} if the roomDetails is not found,
     * or with status {@code 500 (Internal Server Error)} if the roomDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/room-details/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RoomDetails> partialUpdateRoomDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RoomDetails roomDetails
    ) throws URISyntaxException {
        log.debug("REST request to partial update RoomDetails partially : {}, {}", id, roomDetails);
        if (roomDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, roomDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roomDetailsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RoomDetails> result = roomDetailsService.partialUpdate(roomDetails);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, roomDetails.getId().toString())
        );
    }

    /**
     * {@code GET  /room-details} : get all the roomDetails.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roomDetails in body.
     */
    @GetMapping("/room-details")
    public ResponseEntity<List<RoomDetails>> getAllRoomDetails(
        RoomDetailsCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RoomDetails by criteria: {}", criteria);
        Page<RoomDetails> page = roomDetailsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /room-details/count} : count all the roomDetails.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/room-details/count")
    public ResponseEntity<Long> countRoomDetails(RoomDetailsCriteria criteria) {
        log.debug("REST request to count RoomDetails by criteria: {}", criteria);
        return ResponseEntity.ok().body(roomDetailsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /room-details/:id} : get the "id" roomDetails.
     *
     * @param id the id of the roomDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roomDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/room-details/{id}")
    public ResponseEntity<RoomDetails> getRoomDetails(@PathVariable Long id) {
        log.debug("REST request to get RoomDetails : {}", id);
        Optional<RoomDetails> roomDetails = roomDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(roomDetails);
    }

    /**
     * {@code DELETE  /room-details/:id} : delete the "id" roomDetails.
     *
     * @param id the id of the roomDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/room-details/{id}")
    public ResponseEntity<Void> deleteRoomDetails(@PathVariable Long id) {
        log.debug("REST request to delete RoomDetails : {}", id);
        roomDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
