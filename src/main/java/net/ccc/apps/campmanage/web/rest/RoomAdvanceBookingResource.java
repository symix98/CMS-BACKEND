package net.ccc.apps.campmanage.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.ccc.apps.campmanage.domain.RoomAdvanceBooking;
import net.ccc.apps.campmanage.repository.RoomAdvanceBookingRepository;
import net.ccc.apps.campmanage.service.RoomAdvanceBookingQueryService;
import net.ccc.apps.campmanage.service.RoomAdvanceBookingService;
import net.ccc.apps.campmanage.service.criteria.RoomAdvanceBookingCriteria;
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
 * REST controller for managing {@link net.ccc.apps.campmanage.domain.RoomAdvanceBooking}.
 */
@RestController
@RequestMapping("/api")
public class RoomAdvanceBookingResource {

    private final Logger log = LoggerFactory.getLogger(RoomAdvanceBookingResource.class);

    private static final String ENTITY_NAME = "campmanagRoomAdvanceBooking";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RoomAdvanceBookingService roomAdvanceBookingService;

    private final RoomAdvanceBookingRepository roomAdvanceBookingRepository;

    private final RoomAdvanceBookingQueryService roomAdvanceBookingQueryService;

    public RoomAdvanceBookingResource(
        RoomAdvanceBookingService roomAdvanceBookingService,
        RoomAdvanceBookingRepository roomAdvanceBookingRepository,
        RoomAdvanceBookingQueryService roomAdvanceBookingQueryService
    ) {
        this.roomAdvanceBookingService = roomAdvanceBookingService;
        this.roomAdvanceBookingRepository = roomAdvanceBookingRepository;
        this.roomAdvanceBookingQueryService = roomAdvanceBookingQueryService;
    }

    /**
     * {@code POST  /room-advance-bookings} : Create a new roomAdvanceBooking.
     *
     * @param roomAdvanceBooking the roomAdvanceBooking to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roomAdvanceBooking, or with status {@code 400 (Bad Request)} if the roomAdvanceBooking has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/room-advance-bookings")
    public ResponseEntity<RoomAdvanceBooking> createRoomAdvanceBooking(@RequestBody RoomAdvanceBooking roomAdvanceBooking)
        throws URISyntaxException {
        log.debug("REST request to save RoomAdvanceBooking : {}", roomAdvanceBooking);
        if (roomAdvanceBooking.getId() != null) {
            throw new BadRequestAlertException("A new roomAdvanceBooking cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoomAdvanceBooking result = roomAdvanceBookingService.save(roomAdvanceBooking);
        return ResponseEntity
            .created(new URI("/api/room-advance-bookings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /room-advance-bookings/:id} : Updates an existing roomAdvanceBooking.
     *
     * @param id the id of the roomAdvanceBooking to save.
     * @param roomAdvanceBooking the roomAdvanceBooking to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roomAdvanceBooking,
     * or with status {@code 400 (Bad Request)} if the roomAdvanceBooking is not valid,
     * or with status {@code 500 (Internal Server Error)} if the roomAdvanceBooking couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/room-advance-bookings/{id}")
    public ResponseEntity<RoomAdvanceBooking> updateRoomAdvanceBooking(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RoomAdvanceBooking roomAdvanceBooking
    ) throws URISyntaxException {
        log.debug("REST request to update RoomAdvanceBooking : {}, {}", id, roomAdvanceBooking);
        if (roomAdvanceBooking.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, roomAdvanceBooking.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roomAdvanceBookingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RoomAdvanceBooking result = roomAdvanceBookingService.update(roomAdvanceBooking);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, roomAdvanceBooking.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /room-advance-bookings/:id} : Partial updates given fields of an existing roomAdvanceBooking, field will ignore if it is null
     *
     * @param id the id of the roomAdvanceBooking to save.
     * @param roomAdvanceBooking the roomAdvanceBooking to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roomAdvanceBooking,
     * or with status {@code 400 (Bad Request)} if the roomAdvanceBooking is not valid,
     * or with status {@code 404 (Not Found)} if the roomAdvanceBooking is not found,
     * or with status {@code 500 (Internal Server Error)} if the roomAdvanceBooking couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/room-advance-bookings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RoomAdvanceBooking> partialUpdateRoomAdvanceBooking(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RoomAdvanceBooking roomAdvanceBooking
    ) throws URISyntaxException {
        log.debug("REST request to partial update RoomAdvanceBooking partially : {}, {}", id, roomAdvanceBooking);
        if (roomAdvanceBooking.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, roomAdvanceBooking.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!roomAdvanceBookingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RoomAdvanceBooking> result = roomAdvanceBookingService.partialUpdate(roomAdvanceBooking);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, roomAdvanceBooking.getId().toString())
        );
    }

    /**
     * {@code GET  /room-advance-bookings} : get all the roomAdvanceBookings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roomAdvanceBookings in body.
     */
    @GetMapping("/room-advance-bookings")
    public ResponseEntity<List<RoomAdvanceBooking>> getAllRoomAdvanceBookings(
        RoomAdvanceBookingCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get RoomAdvanceBookings by criteria: {}", criteria);
        Page<RoomAdvanceBooking> page = roomAdvanceBookingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /room-advance-bookings/count} : count all the roomAdvanceBookings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/room-advance-bookings/count")
    public ResponseEntity<Long> countRoomAdvanceBookings(RoomAdvanceBookingCriteria criteria) {
        log.debug("REST request to count RoomAdvanceBookings by criteria: {}", criteria);
        return ResponseEntity.ok().body(roomAdvanceBookingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /room-advance-bookings/:id} : get the "id" roomAdvanceBooking.
     *
     * @param id the id of the roomAdvanceBooking to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roomAdvanceBooking, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/room-advance-bookings/{id}")
    public ResponseEntity<RoomAdvanceBooking> getRoomAdvanceBooking(@PathVariable Long id) {
        log.debug("REST request to get RoomAdvanceBooking : {}", id);
        Optional<RoomAdvanceBooking> roomAdvanceBooking = roomAdvanceBookingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(roomAdvanceBooking);
    }

    /**
     * {@code DELETE  /room-advance-bookings/:id} : delete the "id" roomAdvanceBooking.
     *
     * @param id the id of the roomAdvanceBooking to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/room-advance-bookings/{id}")
    public ResponseEntity<Void> deleteRoomAdvanceBooking(@PathVariable Long id) {
        log.debug("REST request to delete RoomAdvanceBooking : {}", id);
        roomAdvanceBookingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
