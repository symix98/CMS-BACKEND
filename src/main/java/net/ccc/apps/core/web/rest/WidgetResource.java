package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import net.ccc.apps.core.repository.WidgetRepository;
import net.ccc.apps.core.service.WidgetService;
import net.ccc.apps.core.service.dto.WidgetDTO;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.Widget}.
 */
@RestController
@RequestMapping("/api")
public class WidgetResource {

    private final Logger log = LoggerFactory.getLogger(WidgetResource.class);

    private static final String ENTITY_NAME = "appscoreapiWidget";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WidgetService widgetService;

    private final WidgetRepository widgetRepository;

    public WidgetResource(WidgetService widgetService, WidgetRepository widgetRepository) {
        this.widgetService = widgetService;
        this.widgetRepository = widgetRepository;
    }

    /**
     * {@code POST  /widgets} : Create a new widget.
     *
     * @param widgetDTO the widgetDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new widgetDTO, or with status {@code 400 (Bad Request)} if the widget has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/widgets")
    public ResponseEntity<WidgetDTO> createWidget(@Valid @RequestBody WidgetDTO widgetDTO) throws URISyntaxException {
        log.debug("REST request to save Widget : {}", widgetDTO);
        if (widgetDTO.getId() != null) {
            throw new BadRequestAlertException("A new widget cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WidgetDTO result = widgetService.save(widgetDTO);
        return ResponseEntity
            .created(new URI("/api/widgets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /widgets} : Updates an existing widget.
     *
     * @param widgetDTO the widgetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated widgetDTO,
     * or with status {@code 400 (Bad Request)} if the widgetDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the widgetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/widgets")
    public ResponseEntity<WidgetDTO> updateWidget(@Valid @RequestBody WidgetDTO widgetDTO) throws URISyntaxException {
        log.debug("REST request to update Widget : {}", widgetDTO);
        if (widgetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WidgetDTO result = widgetService.save(widgetDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, widgetDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /widgets} : get all the widgets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of widgets in body.
     */
    @GetMapping("/widgets")
    public List<WidgetDTO> getAllWidgets() {
        log.debug("REST request to get all Widgets");
        return widgetService.findAll();
    }

    /**
     * {@code GET  /widgets/:id} : get the "id" widget.
     *
     * @param id the id of the widgetDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the widgetDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/widgets/{id}")
    public ResponseEntity<WidgetDTO> getWidget(@PathVariable Long id) {
        log.debug("REST request to get Widget : {}", id);
        Optional<WidgetDTO> widgetDTO = widgetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(widgetDTO);
    }

    /**
     * {@code DELETE  /widgets/:id} : delete the "id" widget.
     *
     * @param id the id of the widgetDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/widgets/{id}")
    public ResponseEntity<Void> deleteWidget(@PathVariable Long id) {
        log.debug("REST request to delete Widget : {}", id);
        widgetService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
