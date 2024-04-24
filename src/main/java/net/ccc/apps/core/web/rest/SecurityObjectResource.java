package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import net.ccc.apps.core.domain.SecurityObject;
import net.ccc.apps.core.repository.SecurityObjectRepository;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.SecurityObject}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SecurityObjectResource {

    private final Logger log = LoggerFactory.getLogger(SecurityObjectResource.class);

    private static final String ENTITY_NAME = "testSecurityObject";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SecurityObjectRepository securityObjectRepository;

    public SecurityObjectResource(SecurityObjectRepository securityObjectRepository) {
        this.securityObjectRepository = securityObjectRepository;
    }

    /**
     * {@code POST  /security-objects} : Create a new securityObject.
     *
     * @param securityObject the securityObject to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new securityObject, or with status {@code 400 (Bad Request)} if the securityObject has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/security-objects")
    public ResponseEntity<SecurityObject> createSecurityObject(@Valid @RequestBody SecurityObject securityObject)
        throws URISyntaxException {
        log.debug("REST request to save SecurityObject : {}", securityObject);
        if (securityObject.getId() != null) {
            throw new BadRequestAlertException("A new securityObject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SecurityObject result = securityObjectRepository.save(securityObject);
        return ResponseEntity
            .created(new URI("/api/security-objects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /security-objects/:id} : Updates an existing securityObject.
     *
     * @param id the id of the securityObject to save.
     * @param securityObject the securityObject to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated securityObject,
     * or with status {@code 400 (Bad Request)} if the securityObject is not valid,
     * or with status {@code 500 (Internal Server Error)} if the securityObject couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/security-objects/{id}")
    public ResponseEntity<SecurityObject> updateSecurityObject(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SecurityObject securityObject
    ) throws URISyntaxException {
        log.debug("REST request to update SecurityObject : {}, {}", id, securityObject);
        if (securityObject.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, securityObject.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!securityObjectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SecurityObject result = securityObjectRepository.save(securityObject);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, securityObject.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /security-objects/:id} : Partial updates given fields of an existing securityObject, field will ignore if it is null
     *
     * @param id the id of the securityObject to save.
     * @param securityObject the securityObject to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated securityObject,
     * or with status {@code 400 (Bad Request)} if the securityObject is not valid,
     * or with status {@code 404 (Not Found)} if the securityObject is not found,
     * or with status {@code 500 (Internal Server Error)} if the securityObject couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/security-objects/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SecurityObject> partialUpdateSecurityObject(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SecurityObject securityObject
    ) throws URISyntaxException {
        log.debug("REST request to partial update SecurityObject partially : {}, {}", id, securityObject);
        if (securityObject.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, securityObject.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!securityObjectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SecurityObject> result = securityObjectRepository
            .findById(securityObject.getId())
            .map(existingSecurityObject -> {
                if (securityObject.getName() != null) {
                    existingSecurityObject.setName(securityObject.getName());
                }
                if (securityObject.getDescription() != null) {
                    existingSecurityObject.setDescription(securityObject.getDescription());
                }

                return existingSecurityObject;
            })
            .map(securityObjectRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, securityObject.getId().toString())
        );
    }

    /**
     * {@code GET  /security-objects} : get all the securityObjects.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of securityObjects in body.
     */
    @GetMapping("/security-objects")
    public List<SecurityObject> getAllSecurityObjects() {
        log.debug("REST request to get all SecurityObjects");
        return securityObjectRepository.findAll();
    }

    /**
     * {@code GET  /security-objects/:id} : get the "id" securityObject.
     *
     * @param id the id of the securityObject to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the securityObject, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/security-objects/{id}")
    public ResponseEntity<SecurityObject> getSecurityObject(@PathVariable Long id) {
        log.debug("REST request to get SecurityObject : {}", id);
        Optional<SecurityObject> securityObject = securityObjectRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(securityObject);
    }

    /**
     * {@code DELETE  /security-objects/:id} : delete the "id" securityObject.
     *
     * @param id the id of the securityObject to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/security-objects/{id}")
    public ResponseEntity<Void> deleteSecurityObject(@PathVariable Long id) {
        log.debug("REST request to delete SecurityObject : {}", id);
        securityObjectRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
