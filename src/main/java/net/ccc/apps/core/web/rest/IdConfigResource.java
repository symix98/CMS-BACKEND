package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import net.ccc.apps.core.repository.IdConfigRepository;
import net.ccc.apps.core.service.IdConfigQueryService;
import net.ccc.apps.core.service.IdConfigService;
import net.ccc.apps.core.service.dto.IdConfigDTO;
import net.ccc.apps.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.HeaderUtil;

@RestController
@RequestMapping("/api")
public class IdConfigResource {

    private final Logger log = LoggerFactory.getLogger(IdConfigResource.class);

    private static final String ENTITY_NAME = "idConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IdConfigService idConfigService;

    private final IdConfigRepository idConfigRepository;

    private final IdConfigQueryService idConfigQueryService;

    public IdConfigResource(
        IdConfigService idConfigService,
        IdConfigRepository idConfigRepository,
        IdConfigQueryService idConfigQueryService
    ) {
        this.idConfigService = idConfigService;
        this.idConfigRepository = idConfigRepository;
        this.idConfigQueryService = idConfigQueryService;
    }

    /**
     * {@code POST  /id-configs} : Create a new idConfig.
     *
     * @param idConfigDTO the idConfigDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new idConfigDTO, or with status {@code 400 (Bad Request)} if the idConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/id-configs")
    public ResponseEntity<IdConfigDTO> createIdConfig(@Valid @RequestBody IdConfigDTO idConfigDTO) throws URISyntaxException {
        log.debug("REST request to save IdConfig : {}", idConfigDTO);
        if (idConfigDTO.getName() == null) {
            throw new BadRequestAlertException("A new idConfig must have a name", ENTITY_NAME, "namemissing");
        }
        IdConfigDTO result = idConfigService.save(idConfigDTO);
        return ResponseEntity
            .created(new URI("/api/id-configs/" + result.getName()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getName().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /id-configs/:id} : Updates an existing idConfig.
     * @param idConfigDTO the idConfigDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated idConfigDTO,
     * or with status {@code 400 (Bad Request)} if the idConfigDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the idConfigDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/id-configs")
    public ResponseEntity<IdConfigDTO> updateIdConfig(@Valid @RequestBody IdConfigDTO idConfigDTO) throws URISyntaxException {
        log.debug("REST request to update IdConfig : {}", idConfigDTO);
        if (idConfigDTO.getName() == null) {
            throw new BadRequestAlertException("Invalid name", ENTITY_NAME, "namenull");
        } else if (!idConfigService.findOne(idConfigDTO.getName()).isPresent()) {
            throw new BadRequestAlertException("Name does not exist", ENTITY_NAME, "namedoesnotexist");
        }

        IdConfigDTO result = idConfigService.save(idConfigDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, idConfigDTO.getName().toString()))
            .body(result);
    }

    /**
     * {@code GET  /id-configs} : get all the idConfigs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of idConfigs in body.
     */
    @GetMapping("/id-configs")
    public List<IdConfigDTO> getAllIdConfigs() {
        log.debug("REST request to get All IdConfigs");
        return idConfigService.findAll();
    }

    /**
     * {@code GET  /id-configs/:id} : get the "id" idConfig.
     *
     * @param name the id of the idConfigDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the idConfigDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/id-configs/{name}")
    public ResponseEntity<IdConfigDTO> getIdConfig(@PathVariable String name) {
        log.debug("REST request to get IdConfig : {}", name);
        Optional<IdConfigDTO> idConfigDTO = idConfigService.findOne(name);
        IdConfigDTO dto = idConfigDTO.orElse(null);
        return ResponseEntity.ok().body(dto);
    }

    /**
     * {@code DELETE  /id-configs/:id} : delete the "id" idConfig.
     *
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/id-configs/{name}")
    public ResponseEntity<Void> deleteIdConfig(@PathVariable String name) {
        log.debug("REST request to delete IdConfig : {}", name);
        idConfigService.delete(name);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, name)).build();
    }
}
