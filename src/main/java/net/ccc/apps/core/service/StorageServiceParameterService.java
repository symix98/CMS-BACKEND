package net.ccc.apps.core.service;

import net.ccc.apps.core.domain.StorageServiceParameter;
import net.ccc.apps.core.repository.StorageServiceParameterRepository;
import net.ccc.apps.core.service.dto.StorageServiceParameterDTO;
import net.ccc.apps.core.service.mapper.StorageServiceParameterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link StorageServiceParameter}.
 */
@Service
@Transactional
public class StorageServiceParameterService {

    private final Logger log = LoggerFactory.getLogger(StorageServiceParameterService.class);

    private final StorageServiceParameterRepository storageServiceParameterRepository;

    private final StorageServiceParameterMapper storageServiceParameterMapper;

    public StorageServiceParameterService(StorageServiceParameterRepository storageServiceParameterRepository, StorageServiceParameterMapper storageServiceParameterMapper) {
        this.storageServiceParameterRepository = storageServiceParameterRepository;
        this.storageServiceParameterMapper = storageServiceParameterMapper;
    }

    /**
     * Save a storageServiceParameter.
     *
     * @param storageServiceParameterDTO the entity to save.
     * @return the persisted entity.
     */
    public StorageServiceParameterDTO save(StorageServiceParameterDTO storageServiceParameterDTO) {
        log.debug("Request to save StorageServiceParameter : {}", storageServiceParameterDTO);
        StorageServiceParameter storageServiceParameter = storageServiceParameterMapper.toEntity(storageServiceParameterDTO);
        storageServiceParameter = storageServiceParameterRepository.save(storageServiceParameter);
        return storageServiceParameterMapper.toDto(storageServiceParameter);
    }

    /**
     * Get all the storageServiceParameters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<StorageServiceParameterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StorageServiceParameters");
        return storageServiceParameterRepository.findAll(pageable)
            .map(storageServiceParameterMapper::toDto);
    }


    /**
     * Get one storageServiceParameter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StorageServiceParameterDTO> findOne(Long id) {
        log.debug("Request to get StorageServiceParameter : {}", id);
        return storageServiceParameterRepository.findById(id)
            .map(storageServiceParameterMapper::toDto);
    }

    /**
     * Delete the storageServiceParameter by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete StorageServiceParameter : {}", id);
        storageServiceParameterRepository.deleteById(id);
    }
}
