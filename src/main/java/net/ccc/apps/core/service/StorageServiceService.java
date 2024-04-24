package net.ccc.apps.core.service;

import liquibase.pro.packaged.S;
import net.ccc.apps.core.domain.StorageService;
import net.ccc.apps.core.repository.StorageServiceRepository;
import net.ccc.apps.core.service.dto.StorageServiceDTO;
import net.ccc.apps.core.service.mapper.StorageServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link StorageService}.
 */
@Service
@Transactional
public class StorageServiceService {

    private final Logger log = LoggerFactory.getLogger(StorageServiceService.class);

    private final StorageServiceRepository storageServiceRepository;

    private final StorageServiceMapper storageServiceMapper;

    public StorageServiceService(StorageServiceRepository storageServiceRepository, StorageServiceMapper storageServiceMapper) {
        this.storageServiceRepository = storageServiceRepository;
        this.storageServiceMapper = storageServiceMapper;
    }

    /**
     * Save a storageService.
     *
     * @param storageServiceDTO the entity to save.
     * @return the persisted entity.
     */
    public StorageServiceDTO save(StorageServiceDTO storageServiceDTO) {
        log.debug("Request to save StorageService : {}", storageServiceDTO);
        StorageService storageService = storageServiceMapper.toEntity(storageServiceDTO);
        storageService = storageServiceRepository.save(storageService);
        return storageServiceMapper.toDto(storageService);
    }

    /**
     * Get all the storageServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<StorageServiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StorageServices");
        return storageServiceRepository.findAll(pageable)
            .map(storageServiceMapper::toDto);
    }


    /**
     * Get one storageService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StorageServiceDTO> findOne(String id) {
        log.debug("Request to get StorageService : {}", id);
        return storageServiceRepository.findById(id)
            .map(storageServiceMapper::toDto);
    }

    /**
     * Delete the storageService by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete StorageService : {}", id);
        storageServiceRepository.deleteById(id);
    }
}
