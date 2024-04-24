package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.DataType;
import net.ccc.apps.core.repository.DataTypeRepository;
import net.ccc.apps.core.service.dto.DataTypeDTO;
import net.ccc.apps.core.service.mapper.DataTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DataType}.
 */
@Service
@Transactional
public class DataTypeService {

    private final Logger log = LoggerFactory.getLogger(DataTypeService.class);

    private final DataTypeRepository dataTypeRepository;

    private final DataTypeMapper dataTypeMapper;

    public DataTypeService(DataTypeRepository dataTypeRepository, DataTypeMapper dataTypeMapper) {
        this.dataTypeRepository = dataTypeRepository;
        this.dataTypeMapper = dataTypeMapper;
    }

    /**
     * Save a dataType.
     *
     * @param dataTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public DataTypeDTO save(DataTypeDTO dataTypeDTO) {
        log.debug("Request to save DataType : {}", dataTypeDTO);
        DataType dataType = dataTypeMapper.toEntity(dataTypeDTO);
        dataType = dataTypeRepository.save(dataType);
        return dataTypeMapper.toDto(dataType);
    }

    /**
     * Partially update a dataType.
     *
     * @param dataTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DataTypeDTO> partialUpdate(DataTypeDTO dataTypeDTO) {
        log.debug("Request to partially update DataType : {}", dataTypeDTO);

        return dataTypeRepository
            .findById(dataTypeDTO.getId())
            .map(existingDataType -> {
                dataTypeMapper.partialUpdate(existingDataType, dataTypeDTO);

                return existingDataType;
            })
            .map(dataTypeRepository::save)
            .map(dataTypeMapper::toDto);
    }

    /**
     * Get all the dataTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DataTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DataTypes");
        return dataTypeRepository.findAll(pageable).map(dataTypeMapper::toDto);
    }

    /**
     * Get one dataType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DataTypeDTO> findOne(Long id) {
        log.debug("Request to get DataType : {}", id);
        return dataTypeRepository.findById(id).map(dataTypeMapper::toDto);
    }

    /**
     * Delete the dataType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DataType : {}", id);
        dataTypeRepository.deleteById(id);
    }
}
