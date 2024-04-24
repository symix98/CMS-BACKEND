package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import net.ccc.apps.core.repository.DashboardLayoutRepository;
import net.ccc.apps.core.service.DashboardLayoutService;
import net.ccc.apps.core.service.dto.DashboardLayoutDTO;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.DashboardLayout}.
 */
@RestController
@RequestMapping("/api")
public class DashboardLayoutResource {

    private final Logger log = LoggerFactory.getLogger(DashboardLayoutResource.class);

    private static final String ENTITY_NAME = "appscoreapiDashboardLayout";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DashboardLayoutService dashboardLayoutService;

    private final DashboardLayoutRepository dashboardLayoutRepository;

    public DashboardLayoutResource(DashboardLayoutService dashboardLayoutService, DashboardLayoutRepository dashboardLayoutRepository) {
        this.dashboardLayoutService = dashboardLayoutService;
        this.dashboardLayoutRepository = dashboardLayoutRepository;
    }

    /**
     * {@code POST  /dashboard-layouts} : Create a new dashboardLayout.
     *
     * @param dashboardLayoutDTO the dashboardLayoutDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dashboardLayoutDTO, or with status {@code 400 (Bad Request)} if the dashboardLayout has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dashboard-layouts")
    public ResponseEntity<DashboardLayoutDTO> createDashboardLayout(@Valid @RequestBody DashboardLayoutDTO dashboardLayoutDTO)
        throws URISyntaxException {
        log.debug("REST request to save DashboardLayout : {}", dashboardLayoutDTO);
        if (dashboardLayoutDTO.getId() != null) {
            throw new BadRequestAlertException("A new dashboardLayout cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DashboardLayoutDTO result = dashboardLayoutService.save(dashboardLayoutDTO);
        return ResponseEntity
            .created(new URI("/api/dashboard-layouts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dashboard-layouts} : Updates an existing dashboardLayout.
     *
     * @param dashboardLayoutDTO the dashboardLayoutDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dashboardLayoutDTO,
     * or with status {@code 400 (Bad Request)} if the dashboardLayoutDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dashboardLayoutDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dashboard-layouts")
    public ResponseEntity<DashboardLayoutDTO> updateDashboardLayout(@Valid @RequestBody DashboardLayoutDTO dashboardLayoutDTO)
        throws URISyntaxException {
        log.debug("REST request to update DashboardLayout : {}", dashboardLayoutDTO);
        if (dashboardLayoutDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DashboardLayoutDTO result = dashboardLayoutService.save(dashboardLayoutDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dashboardLayoutDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dashboard-layouts} : get all the dashboardLayouts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dashboardLayouts in body.
     */
    @GetMapping("/dashboard-layouts")
    public List<DashboardLayoutDTO> getAllDashboardLayouts() {
        log.debug("REST request to get all DashboardLayouts");
        return dashboardLayoutService.findAll();
    }

    /**
     * {@code GET  /dashboard-layouts/:id} : get the "id" dashboardLayout.
     *
     * @param id the id of the dashboardLayoutDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dashboardLayoutDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dashboard-layouts/{id}")
    public ResponseEntity<DashboardLayoutDTO> getDashboardLayout(@PathVariable Long id) {
        log.debug("REST request to get DashboardLayout : {}", id);
        Optional<DashboardLayoutDTO> dashboardLayoutDTO = dashboardLayoutService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dashboardLayoutDTO);
    }

    /**
     * {@code DELETE  /dashboard-layouts/:id} : delete the "id" dashboardLayout.
     *
     * @param id the id of the dashboardLayoutDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dashboard-layouts/{id}")
    public ResponseEntity<Void> deleteDashboardLayout(@PathVariable Long id) {
        log.debug("REST request to delete DashboardLayout : {}", id);
        dashboardLayoutService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /dashboard-layouts} : get all the dashboardLayouts.
     *
     * @param dashboardLayoutId the id of the dashboardLayoutDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dashboardLayouts in body.
     */
    @GetMapping("/dashboard-layout-id/{dashboardLayoutId}")
    public List<DashboardLayoutDTO> getDashboardLayoutByDashboardLayoutId(@PathVariable String dashboardLayoutId) {
        log.debug("REST request to get all DashboardLayouts");
        return dashboardLayoutService.findByDashboardLayoutId(dashboardLayoutId);
    }
}
