package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;

import net.ccc.apps.core.repository.DashboardDetailsRepository;
import net.ccc.apps.core.service.DashboardDetailsService;
import net.ccc.apps.core.service.dto.DashboardDetailsDTO;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.DashboardDetails}.
 */
@RestController
@RequestMapping("/api")
public class DashboardDetailsResource {

    private final Logger log = LoggerFactory.getLogger(DashboardDetailsResource.class);

    private static final String ENTITY_NAME = "appscoreapiDashboardDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DashboardDetailsService dashboardDetailsService;

    private final DashboardDetailsRepository dashboardDetailsRepository;

    public DashboardDetailsResource(
        DashboardDetailsService dashboardDetailsService,
        DashboardDetailsRepository dashboardDetailsRepository
    ) {
        this.dashboardDetailsService = dashboardDetailsService;
        this.dashboardDetailsRepository = dashboardDetailsRepository;
    }

    /**
     * {@code POST  /dashboard-details} : Create a new dashboardDetails.
     *
     * @param dashboardDetailsDTO the dashboardDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dashboardDetailsDTO, or with status {@code 400 (Bad Request)} if the dashboardDetails has already
     * an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dashboard-details")
    public ResponseEntity<DashboardDetailsDTO> createDashboardDetails(@Valid @RequestBody DashboardDetailsDTO dashboardDetailsDTO)
        throws URISyntaxException {
        log.debug("REST request to save DashboardDetails : {}", dashboardDetailsDTO);
        if (dashboardDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new dashboardDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DashboardDetailsDTO result = dashboardDetailsService.save(dashboardDetailsDTO);
        return ResponseEntity
            .created(new URI("/api/dashboard-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dashboard-details} : Updates an existing dashboardDetails.
     *
     * @param dashboardDetailsDTO the dashboardDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dashboardDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the dashboardDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dashboardDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dashboard-details")
    public ResponseEntity<DashboardDetailsDTO> updateDashboardDetails(@Valid @RequestBody DashboardDetailsDTO dashboardDetailsDTO)
        throws URISyntaxException {
        log.debug("REST request to update DashboardDetails : {}", dashboardDetailsDTO);
        if (dashboardDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DashboardDetailsDTO result = dashboardDetailsService.save(dashboardDetailsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dashboardDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dashboard-details} : get all the dashboardDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dashboardDetails in body.
     */
    @GetMapping("/dashboard-details")
    public List<DashboardDetailsDTO> getAllDashboardDetails() {
        log.debug("REST request to get all DashboardDetails");
        return dashboardDetailsService.findAll();
    }

    /**
     * {@code GET  /dashboard-details/:id} : get the "id" dashboardDetails.
     *
     * @param dashboardId the id of the dashboardDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dashboardDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dashboard-details/{dashboardId}")
    public ResponseEntity<List<DashboardDetailsDTO>> getAllDashboardDetailsByDashboardId(@PathVariable String dashboardId) {
        log.debug("REST request to get DashboardDetails : {}", dashboardId);
        List<DashboardDetailsDTO> result = dashboardDetailsService.findDetailsByDashBoardId(dashboardId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code DELETE  /dashboard-details/:id} : delete the "id" dashboardDetails.
     *
     * @param id the id of the dashboardDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dashboard-details/{id}")
    public ResponseEntity<Void> deleteDashboardDetails(@PathVariable Long id) {
        log.debug("REST request to delete DashboardDetails : {}", id);
        dashboardDetailsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
