package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import net.ccc.apps.core.domain.AppUser;
import net.ccc.apps.core.service.AppUserService;
import net.ccc.apps.core.service.criteria.AppUserCriteria;
import net.ccc.apps.core.service.dto.AppUserDTO;
import net.ccc.apps.core.service.queryService.AppUserQueryService;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * REST controller for managing {@link net.ccc.apps.core.domain.AppUser}.
 */
@RestController
@RequestMapping("/api")
public class AppUserResource {

    private final Logger log = LoggerFactory.getLogger(AppUserResource.class);

    private static final String ENTITY_NAME = "appUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserQueryService appUserQueryService;

    /**
     * {@code POST  /app-users} : Create a new appUser.
     *
     * @param appUserDTO the appUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appUserDTO, or with status {@code 400 (Bad Request)} if the appUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/app-users")
    public ResponseEntity<AppUserDTO> createAppUser(@Valid @RequestBody AppUserDTO appUserDTO) throws URISyntaxException {
        log.debug("REST request to save AppUser : {}", appUserDTO);
        if (appUserDTO.getUserId() != null) {
            throw new BadRequestAlertException("A new appUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppUserDTO result = appUserService.save(appUserDTO);
        return ResponseEntity
            .created(new URI("/api/app-users/" + result.getUserId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getUserId()))
            .body(result);
    }

    /**
     * {@code PUT  /app-users} : Updates an existing appUser.
     *
     * @param appUserDTO the appUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appUserDTO,
     * or with status {@code 400 (Bad Request)} if the appUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/app-users")
    public ResponseEntity<AppUserDTO> updateAppUser(@Valid @RequestBody AppUserDTO appUserDTO) throws URISyntaxException {
        log.debug("REST request to update AppUser : {}", appUserDTO);
        if (appUserDTO.getUserId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AppUserDTO result = appUserService.save(appUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, appUserDTO.getUserId()))
            .body(result);
    }

    /**
     * {@code GET  /app-users} : get all the appUsers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appUsers in body.
     */
    @GetMapping("/app-users")
    public ResponseEntity<List<AppUserDTO>> getAllAppUsers(AppUserCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AppUsers by criteria: {}", criteria);
        Page<AppUserDTO> page = appUserQueryService.findByCriteria(false, criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/app-users/search")
    public ResponseEntity<List<AppUserDTO>> searchAllAppUsers(AppUserCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AppUsers by criteria: {}", criteria);
        Page<AppUserDTO> page = appUserQueryService.findByCriteria(true, criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /app-users/count} : count all the appUsers.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/app-users/count")
    public ResponseEntity<Long> countAppUsers(AppUserCriteria criteria) {
        log.debug("REST request to count AppUsers by criteria: {}", criteria);
        return ResponseEntity.ok().body(appUserQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /app-users/:id} : get the "id" appUser.
     *
     * @param userId the id of the appUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/app-users/{userId}")
    public ResponseEntity<AppUser> getAppUser(@PathVariable String userId) {
        log.debug("REST request to get AppUser : {}", userId);
        Optional<AppUser> appUser = appUserService.findOne(userId);
        return ResponseUtil.wrapOrNotFound(appUser);
    }

    /**
     * {@code DELETE  /app-users/:id} : delete the "id" appUser.
     *
     * @param userId the id of the appUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/app-users/{userId}")
    public ResponseEntity<Void> deleteAppUser(@PathVariable String userId) {
        log.debug("REST request to delete AppUser : {}", userId);
        appUserService.delete(userId);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, userId))
            .build();
    }

    /**
     * {@code GET  /app-users/:id} : get the "id" appUser.
     *
     * @param roleId the id of the appUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/app-users/byRole/{roleId}")
    public List<AppUserDTO> getAppUsersByRoleId(@PathVariable String roleId) {
        log.debug("REST request to get AppUser : {}", roleId);
        return appUserService.findAllUsersByRoleId(roleId);
    }

    @GetMapping("/orgunit/user/{groupNo}")
    ResponseEntity<Map<String, Object>> fetchEmployee(@PathVariable String groupNo) {
        log.debug("REST request to find user from orgunit service by groupno : {}", groupNo);
        return ResponseEntity.ok(appUserService.findEmployeeByGroupNo(groupNo));
    }
}
