package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.validation.Valid;

import net.ccc.apps.core.domain.AppUser;
import net.ccc.apps.core.domain.Role;
import net.ccc.apps.core.domain.SecurityObject;
import net.ccc.apps.core.domain.SecurityPermission;
import net.ccc.apps.core.domain.composite.SecurityPermissionId;
import net.ccc.apps.core.domain.enumeration.Permission;
import net.ccc.apps.core.repository.SecurityPermissionRepository;
import net.ccc.apps.core.service.queryService.SecurityObjectService;
import net.ccc.apps.core.service.SecurityPermissionService;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;


@RestController
@RequestMapping("/api")
@Transactional
public class SecurityPermissionResource {

    private final Logger log = LoggerFactory.getLogger(SecurityPermissionResource.class);

    private static final String ENTITY_NAME = "testSecurityPermission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecurityPermissionRepository securityPermissionRepository;
    private final SecurityPermissionService securityPermissionService;
    private final SecurityObjectService securityObjectService;

    public SecurityPermissionResource(SecurityPermissionRepository securityPermissionRepository, SecurityPermissionService securityPermissionService, SecurityObjectService securityObjectService) {
        this.securityPermissionRepository = securityPermissionRepository;
        this.securityPermissionService = securityPermissionService;
        this.securityObjectService = securityObjectService;
    }

    /**
     * {@code POST  /security-permissions} : Create a new securityPermission.
     *
     * @param securityPermission the securityPermission to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new securityPermission, or with status {@code 400 (Bad Request)} if the securityPermission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/security-permissions")
    public ResponseEntity<SecurityPermission> createSecurityPermission(@Valid @RequestBody SecurityPermission securityPermission)
        throws URISyntaxException {
        log.debug("REST request to save SecurityPermission : {}", securityPermission);
        if (securityPermission.getId() != null) {
            throw new BadRequestAlertException("A new securityPermission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SecurityPermission result = securityPermissionRepository.save(securityPermission);
        return ResponseEntity
            .created(new URI("/api/security-permissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /security-permissions/:id} : Updates an existing securityPermission.
     *
     * @param id the id of the securityPermission to save.
     * @param securityPermission the securityPermission to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated securityPermission,
     * or with status {@code 400 (Bad Request)} if the securityPermission is not valid,
     * or with status {@code 500 (Internal Server Error)} if the securityPermission couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/security-permissions/{id}")
    public ResponseEntity<SecurityPermission> updateSecurityPermission(
        @PathVariable(value = "id", required = false) final SecurityPermissionId id,
        @Valid @RequestBody SecurityPermission securityPermission
    ) throws URISyntaxException {
        log.debug("REST request to update SecurityPermission : {}, {}", id, securityPermission);
        if (securityPermission.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, securityPermission.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!securityPermissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SecurityPermission result = securityPermissionRepository.save(securityPermission);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, securityPermission.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /security-permissions/:id} : Partial updates given fields of an existing securityPermission, field will ignore if it is null
     *
     * @param id the id of the securityPermission to save.
     * @param securityPermission the securityPermission to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated securityPermission,
     * or with status {@code 400 (Bad Request)} if the securityPermission is not valid,
     * or with status {@code 404 (Not Found)} if the securityPermission is not found,
     * or with status {@code 500 (Internal Server Error)} if the securityPermission couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
//    @PatchMapping(value = "/security-permissions/{id}", consumes = { "application/json", "application/merge-patch+json" })
//    public ResponseEntity<SecurityPermission> partialUpdateSecurityPermission(
//        @PathVariable(value = "id", required = false) final Long id,
//        @NotNull @RequestBody SecurityPermission securityPermission
//    ) throws URISyntaxException {
//        log.debug("REST request to partial update SecurityPermission partially : {}, {}", id, securityPermission);
//        if (securityPermission.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        if (!Objects.equals(id, securityPermission.getId())) {
//            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
//        }
//
//        if (!securityPermissionRepository.existsById(id)) {
//            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
//        }
//
//        Optional<SecurityPermission> result = securityPermissionRepository
//            .findById(securityPermission.getId())
//            .map(existingSecurityPermission -> {
//                if (securityPermission.getRole() != null) {
//                    existingSecurityPermission.setRole(securityPermission.getRole());
//                }
//                if (securityPermission.getObject() != null) {
//                    existingSecurityPermission.setObject(securityPermission.getObject());
//                }
//                if (securityPermission.getPermission() != null) {
//                    existingSecurityPermission.setPermission(securityPermission.getPermission());
//                }
//
//                return existingSecurityPermission;
//            })
//            .map(securityPermissionRepository::save);
//
//        return ResponseUtil.wrapOrNotFound(
//            result,
//            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, securityPermission.getId().toString())
//        );
//    }

    /**
     * {@code GET  /security-permissions} : get all the securityPermissions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of securityPermissions in body.
     */
    @GetMapping("/security-permissions")
    public List<SecurityPermission> getAllSecurityPermissions() {
        log.debug("REST request to get all SecurityPermissions");
        return securityPermissionRepository.findAll();
    }

    /**
     * {@code GET  /security-permissions/:id} : get the "id" securityPermission.
     *
     * @param id the id of the securityPermission to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the securityPermission, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/security-permissions/{id}")
    public ResponseEntity<SecurityPermission> getSecurityPermission(@PathVariable SecurityPermissionId id) {
        log.debug("REST request to get SecurityPermission : {}", id);
        Optional<SecurityPermission> securityPermission = securityPermissionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(securityPermission);
    }

    /**
     * {@code DELETE  /security-permissions/:id} : delete the "id" securityPermission.
     *
     * @param id the id of the securityPermission to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/security-permissions/{id}")
    public ResponseEntity<Void> deleteSecurityPermission(@PathVariable SecurityPermissionId id) {
        log.debug("REST request to delete SecurityPermission : {}", id);
        securityPermissionRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }




    @GetMapping("/security-permissions/user/{userId}")
    HttpEntity<HashMap<String, String>> findPermissionsByRole(@PathVariable String userId){
        /*
        Frontend objects permission depends on user role
        user may have many roles
        permissions: manage > create > read
        For the same Frontend object, the strongest user's role permission dominates
        the Frontend sub-object permission follows its parent permission only if the parent permission dominates
        */

        log.debug("REST request to findPermissionsByRole : {}", userId);
        HashMap<String, String> result = new HashMap<>();
        List<SecurityPermission> securityPermissionList = null;
        List<List<SecurityPermission>> PermissionListByRole = new ArrayList<>();
        Optional<AppUser> appUser = securityPermissionService.getAppUser(userId);
        if(appUser.isPresent()){
            List<SecurityObject> securityObjectList = securityObjectService.findAll();
            if(appUser.get().getIsAdministrator()){
                for(SecurityObject o : securityObjectList){
                    result.put(o.getName(), Permission.manage.name());
                }
            }else{
                for(Role r : appUser.get().getRoles()){
                    securityPermissionList = securityPermissionService.findByRole(r.getRoleId());
                    if(securityPermissionList!=null && securityPermissionList.size()>0){
                        PermissionListByRole.add(securityPermissionList);
                    }
                }
                for(SecurityObject o : securityObjectList){
                    result.put(o.getName(), null);
                }
                if(PermissionListByRole!=null && PermissionListByRole.size()>0){
                    for (List<SecurityPermission> securityPermissions : PermissionListByRole) {
                        for (SecurityPermission L : securityPermissions) {
                            String UIObject = L.getId().getObject();
                            String UserPermission = L.getPermission();
                            if (result.get(UIObject) == null) {
                                result.put(UIObject, UserPermission);
                            } else {
                                if (UserPermission.equals(Permission.manage.name())) {
                                    result.put(UIObject, UserPermission);
                                } else if (UserPermission.equals(Permission.create.name())) {
                                    if (!result.get(UIObject).equals(Permission.manage.name())) {
                                        result.put(UIObject, UserPermission);
                                    }
                                }
                            }
                        }
                    }
                    for(String key : result.keySet()){
                        String childPermission = result.get(key);
                        if(key.contains(".") && null!=childPermission){
                            String parentObject = key.substring(0, key.indexOf("."));
                            String parentPermission = result.get(parentObject);
                            if(null!=parentPermission) {
                                if (!Objects.equals(parentPermission, Permission.read.name()) && !Objects.equals(childPermission, Permission.manage.name()) && !Objects.equals(childPermission, parentPermission)) {
                                    result.put(key, parentPermission);
                                }
                            }
//                            else{
//                                result.put(parentObject, Permission.read.name());
//                            }
                        }
                    }
                }
            }
        }
        return ResponseEntity.ok().body( result );
    }
}
