package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import net.ccc.apps.core.service.RoleService;
import net.ccc.apps.core.service.dto.RoleDTO;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.Role}.
 */
@RestController
@RequestMapping("/api")
public class RoleResource {

    private final Logger log = LoggerFactory.getLogger(RoleResource.class);

    private static final String ENTITY_NAME = "role";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private RoleService roleService;

    /**
     * {@code POST  /roles} : Create a new role.
     *
     * @param roleDTO the roleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new roleDTO, or with status {@code 400 (Bad Request)} if the role has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/roles")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) throws URISyntaxException {
        log.debug("REST request to save Role : {}", roleDTO);
        if (roleDTO.getRoleId() != null) {
            throw new BadRequestAlertException("A new role cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoleDTO result = roleService.save(roleDTO);
        return ResponseEntity.created(new URI("/api/roles/" + result.getRoleId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getRoleId())).body(result);
    }

    /**
     * {@code PUT  /roles} : Updates an existing role.
     *
     * @param roleDTO the roleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated roleDTO,
     * or with status {@code 400 (Bad Request)} if the roleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the roleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/roles")
    public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO roleDTO) throws URISyntaxException {
        log.debug("REST request to update Role : {}", roleDTO);
        if (roleDTO.getRoleId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RoleDTO result = roleService.save(roleDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, roleDTO.getRoleId())).body(result);
    }

    /**
     * {@code GET  /roles} : get all the roles.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of roles in body.
     */
    @GetMapping("/roles")
    public List<RoleDTO> getAllRoles(@RequestParam(required = false) String filter) {
        if ("appuser-is-null".equals(filter)) {
            log.debug("REST request to get all Roles where appUser is null");
            return roleService.findAllWhereAppUserIsNull();
        }
        log.debug("REST request to get all Roles");
        return roleService.findAll();
    }

    /**
     * {@code GET  /roles/:roleId} : get the "id" role.
     *
     * @param roleId the id of the roleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the roleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/roles/{roleId}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable String roleId) {
        log.debug("REST request to get Role : {}", roleId);
        Optional<RoleDTO> roleDTO = roleService.findOne(roleId);
        return ResponseUtil.wrapOrNotFound(roleDTO);
    }

    /**
     * {@code DELETE  /roles/:roleId} : delete the "roleId" role.
     *
     * @param roleId the id of the roleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable String roleId) {
        log.debug("REST request to delete Role : {}", roleId);
        roleService.delete(roleId);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, roleId)).build();
    }
}
