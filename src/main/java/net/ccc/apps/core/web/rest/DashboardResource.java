package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import net.ccc.apps.core.repository.DashboardRepository;
import net.ccc.apps.core.service.DashboardService;
import net.ccc.apps.core.service.dto.DashboardDTO;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.Dashboard}.
 */
@RestController
@RequestMapping("/api")
public class DashboardResource {

    private final Logger log = LoggerFactory.getLogger(DashboardResource.class);

    private static final String ENTITY_NAME = "appscoreapiDashboard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DashboardService dashboardService;

    private final DashboardRepository dashboardRepository;

    public DashboardResource(DashboardService dashboardService, DashboardRepository dashboardRepository) {
        this.dashboardService = dashboardService;
        this.dashboardRepository = dashboardRepository;
    }

    /**
     * {@code POST  /dashboards} : Create a new dashboard.
     *
     * @param dashboardDTO the dashboardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dashboardDTO, or with status {@code 400 (Bad Request)} if the dashboard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dashboards")
    public ResponseEntity<DashboardDTO> createDashboard(@Valid @RequestBody DashboardDTO dashboardDTO) throws URISyntaxException {
        log.debug("REST request to save Dashboard : {}", dashboardDTO);
        if (dashboardDTO.getDashboardId() == null) {
            throw new BadRequestAlertException("A new dashboard must have a dashboard ID", ENTITY_NAME, "dashboardIDNeeded");
        }
        DashboardDTO result = dashboardService.save(dashboardDTO);
        return ResponseEntity
            .created(new URI("/api/dashboards/" + result.getDashboardId().replaceAll(" ", "%20")))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getDashboardId()))
            .body(result);
    }

    /**
     * {@code PUT  /dashboards} : Updates an existing dashboard.
     *
     * @param dashboardDTO the dashboardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dashboardDTO,
     * or with status {@code 400 (Bad Request)} if the dashboardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dashboardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dashboards")
    public ResponseEntity<DashboardDTO> updateDashboard(@Valid @RequestBody DashboardDTO dashboardDTO) throws URISyntaxException {
        log.debug("REST request to update Dashboard : {}", dashboardDTO);
        if (dashboardDTO.getDashboardId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DashboardDTO result = dashboardService.save(dashboardDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dashboardDTO.getDashboardId()))
            .body(result);
    }

    /**
     * {@code GET  /dashboards} : get all the dashboards.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dashboards in body.
     */
    @GetMapping("/dashboards")
    public List<DashboardDTO> getAllDashboards() {
        log.debug("REST request to get all Dashboards");
        return dashboardService.findAll();
    }

    /**
     * {@code GET  /dashboards/:dashboardId} : get the "id" dashboard.
     *
     * @param dashboardId the id of the dashboardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dashboardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dashboards/{dashboardId}")
    public ResponseEntity<DashboardDTO> getDashboard(@PathVariable String dashboardId) {
        log.debug("REST request to get Dashboard : {}", dashboardId);
        Optional<DashboardDTO> dashboardDTO = dashboardService.findOne(dashboardId);
        DashboardDTO dto = dashboardDTO.orElse(null);
        return ResponseEntity.ok().body(dto);
    }

    /**
     * {@code DELETE  /dashboards/:dashboardId} : delete the "id" dashboard.
     *
     * @param dashboardId the id of the dashboardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dashboards/{dashboardId}")
    public ResponseEntity<Void> deleteDashboard(@PathVariable String dashboardId) {
        log.debug("REST request to delete Dashboard : {}", dashboardId);
        dashboardService.delete(dashboardId);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, dashboardId))
            .build();
    }
}
