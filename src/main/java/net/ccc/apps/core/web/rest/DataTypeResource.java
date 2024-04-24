package net.ccc.apps.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import net.ccc.apps.core.repository.DataTypeRepository;
import net.ccc.apps.core.service.queryService.DataTypeQueryService;
import net.ccc.apps.core.service.DataTypeService;
import net.ccc.apps.core.service.criteria.DataTypeCriteria;
import net.ccc.apps.core.service.dto.DataTypeDTO;
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

/**
 * REST controller for managing {@link net.ccc.apps.core.domain.DataType}.
 */
@RestController
@RequestMapping("/api")
public class DataTypeResource {

    private final Logger log = LoggerFactory.getLogger(DataTypeResource.class);

    private static final String ENTITY_NAME = "appscoreapiDataType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataTypeService dataTypeService;

    private final DataTypeRepository dataTypeRepository;

    private final DataTypeQueryService dataTypeQueryService;

    public DataTypeResource(
        DataTypeService dataTypeService,
        DataTypeRepository dataTypeRepository,
        DataTypeQueryService dataTypeQueryService
    ) {
        this.dataTypeService = dataTypeService;
        this.dataTypeRepository = dataTypeRepository;
        this.dataTypeQueryService = dataTypeQueryService;
    }

    /**
     * {@code POST  /data-types} : Create a new dataType.
     *
     * @param dataTypeDTO the dataTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataTypeDTO, or with status {@code 400 (Bad Request)} if the dataType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-types")
    public ResponseEntity<DataTypeDTO> createDataType(@Valid @RequestBody DataTypeDTO dataTypeDTO) throws URISyntaxException {
        log.debug("REST request to save DataType : {}", dataTypeDTO);
        if (dataTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new dataType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataTypeDTO result = dataTypeService.save(dataTypeDTO);
        return ResponseEntity
            .created(new URI("/api/data-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-types/:id} : Updates an existing dataType.
     *
     * @param id the id of the dataTypeDTO to save.
     * @param dataTypeDTO the dataTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataTypeDTO,
     * or with status {@code 400 (Bad Request)} if the dataTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-types/{id}")
    public ResponseEntity<DataTypeDTO> updateDataType(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DataTypeDTO dataTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DataType : {}, {}", id, dataTypeDTO);
        if (dataTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dataTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dataTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DataTypeDTO result = dataTypeService.save(dataTypeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dataTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /data-types/:id} : Partial updates given fields of an existing dataType, field will ignore if it is null
     *
     * @param id the id of the dataTypeDTO to save.
     * @param dataTypeDTO the dataTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataTypeDTO,
     * or with status {@code 400 (Bad Request)} if the dataTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dataTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dataTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/data-types/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DataTypeDTO> partialUpdateDataType(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DataTypeDTO dataTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DataType partially : {}, {}", id, dataTypeDTO);
        if (dataTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dataTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dataTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DataTypeDTO> result = dataTypeService.partialUpdate(dataTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dataTypeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /data-types} : get all the dataTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataTypes in body.
     */
    @GetMapping("/data-types")
    public ResponseEntity<List<DataTypeDTO>> getAllDataTypes(
        DataTypeCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get DataTypes by criteria: {}", criteria);
        Page<DataTypeDTO> page = dataTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /data-types/count} : count all the dataTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/data-types/count")
    public ResponseEntity<Long> countDataTypes(DataTypeCriteria criteria) {
        log.debug("REST request to count DataTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(dataTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /data-types/:id} : get the "id" dataType.
     *
     * @param id the id of the dataTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-types/{id}")
    public ResponseEntity<DataTypeDTO> getDataType(@PathVariable Long id) {
        log.debug("REST request to get DataType : {}", id);
        Optional<DataTypeDTO> dataTypeDTO = dataTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dataTypeDTO);
    }

    /**
     * {@code DELETE  /data-types/:id} : delete the "id" dataType.
     *
     * @param id the id of the dataTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-types/{id}")
    public ResponseEntity<Void> deleteDataType(@PathVariable Long id) {
        log.debug("REST request to delete DataType : {}", id);
        dataTypeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
