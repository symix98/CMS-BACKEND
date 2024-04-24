package net.ccc.apps.core.web.rest;

import net.ccc.apps.core.repository.AppUserSettingsRepository;
import net.ccc.apps.core.service.queryService.AppUserSettingsQueryService;
import net.ccc.apps.core.service.AppUserSettingsService;
import net.ccc.apps.core.service.criteria.AppUserSettingsCriteria;
import net.ccc.apps.core.service.dto.AppUserSettingsDTO;
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.AppUserSettings}.
 */
@RestController
@RequestMapping("/api")
public class AppUserSettingsResource {

    private final Logger log = LoggerFactory.getLogger(AppUserSettingsResource.class);

    private static final String ENTITY_NAME = "oneToManyAppUserSettings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppUserSettingsService appUserSettingsService;

    private final AppUserSettingsRepository appUserSettingsRepository;

    private final AppUserSettingsQueryService appUserSettingsQueryService;

    public AppUserSettingsResource(
        AppUserSettingsService appUserSettingsService,
        AppUserSettingsRepository appUserSettingsRepository,
        AppUserSettingsQueryService appUserSettingsQueryService
    ) {
        this.appUserSettingsService = appUserSettingsService;
        this.appUserSettingsRepository = appUserSettingsRepository;
        this.appUserSettingsQueryService = appUserSettingsQueryService;
    }

    /**
     * {@code POST  /app-user-settings} : Create a new appUserSettings.
     *
     * @param appUserSettingsDTO the appUserSettingsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appUserSettingsDTO, or with status {@code 400 (Bad Request)} if the appUserSettings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/app-user-settings")
    public ResponseEntity<AppUserSettingsDTO> createAppUserSettings(@Valid @RequestBody AppUserSettingsDTO appUserSettingsDTO)
        throws URISyntaxException {
        log.debug("REST request to save AppUserSettings : {}", appUserSettingsDTO);
        if (appUserSettingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new appUserSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppUserSettingsDTO result = appUserSettingsService.save(appUserSettingsDTO);
        return ResponseEntity
            .created(new URI("/api/app-user-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /app-user-settings/:id} : Updates an existing appUserSettings.
     *
     * @param id the id of the appUserSettingsDTO to save.
     * @param appUserSettingsDTO the appUserSettingsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appUserSettingsDTO,
     * or with status {@code 400 (Bad Request)} if the appUserSettingsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appUserSettingsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/app-user-settings/{id}")
    public ResponseEntity<AppUserSettingsDTO> updateAppUserSettings(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AppUserSettingsDTO appUserSettingsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AppUserSettings : {}, {}", id, appUserSettingsDTO);
        if (appUserSettingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, appUserSettingsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!appUserSettingsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AppUserSettingsDTO result = appUserSettingsService.save(appUserSettingsDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, appUserSettingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /app-user-settings/:id} : Partial updates given fields of an existing appUserSettings, field will ignore if it is null
     *
     * @param id the id of the appUserSettingsDTO to save.
     * @param appUserSettingsDTO the appUserSettingsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appUserSettingsDTO,
     * or with status {@code 400 (Bad Request)} if the appUserSettingsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the appUserSettingsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the appUserSettingsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/app-user-settings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AppUserSettingsDTO> partialUpdateAppUserSettings(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AppUserSettingsDTO appUserSettingsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AppUserSettings partially : {}, {}", id, appUserSettingsDTO);
        if (appUserSettingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, appUserSettingsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!appUserSettingsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AppUserSettingsDTO> result = appUserSettingsService.partialUpdate(appUserSettingsDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, appUserSettingsDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /app-user-settings} : get all the appUserSettings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appUserSettings in body.
     */
    @GetMapping("/app-user-settings")
    public ResponseEntity<List<AppUserSettingsDTO>> getAllAppUserSettings(
        AppUserSettingsCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get AppUserSettings by criteria: {}", criteria);
        Page<AppUserSettingsDTO> page = appUserSettingsQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /app-user-settings/count} : count all the appUserSettings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/app-user-settings/count")
    public ResponseEntity<Long> countAppUserSettings(AppUserSettingsCriteria criteria) {
        log.debug("REST request to count AppUserSettings by criteria: {}", criteria);
        return ResponseEntity.ok().body(appUserSettingsQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /app-user-settings/:id} : get the "id" appUserSettings.
     *
     * @param id the id of the appUserSettingsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appUserSettingsDTO, or with status {@code 404 (Not Found)}.
     */
//    @GetMapping("/app-user-settings/{id}")
//    public ResponseEntity<AppUserSettingsDTO> getAppUserSettings(@PathVariable Long id) {
//        log.debug("REST request to get AppUserSettings : {}", id);
//        Optional<AppUserSettingsDTO> appUserSettingsDTO = appUserSettingsService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(appUserSettingsDTO);
//    }

    /**
     * {@code DELETE  /app-user-settings/:id} : delete the "id" appUserSettings.
     *
     * @param id the id of the appUserSettingsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/app-user-settings/{id}")
    public ResponseEntity<Void> deleteAppUserSettings(@PathVariable Long id) {
        log.debug("REST request to delete AppUserSettings : {}", id);
        appUserSettingsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/app-user-settings/{userId}")
    public ResponseEntity<List<AppUserSettingsDTO>>  getAppUserSettings(@org.springdoc.api.annotations.ParameterObject Pageable pageable,
                                                                        @PathVariable String userId) {
        log.debug("REST request to get AppUserSettings : {}", userId);
        Page<AppUserSettingsDTO> page = appUserSettingsService.getAppUserSettingByUserId(pageable, userId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
