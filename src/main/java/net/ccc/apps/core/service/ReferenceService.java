package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.Reference;
import net.ccc.apps.core.repository.ReferenceRepository;
import net.ccc.apps.core.service.dto.ReferenceDTO;
import net.ccc.apps.core.service.mapper.ReferenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Reference}.
 */
@Service
@Transactional
public class ReferenceService {

    private final Logger log = LoggerFactory.getLogger(ReferenceService.class);

    private final ReferenceRepository referenceRepository;

    private final ReferenceMapper referenceMapper;

    public ReferenceService(ReferenceRepository referenceRepository, ReferenceMapper referenceMapper) {
        this.referenceRepository = referenceRepository;
        this.referenceMapper = referenceMapper;
    }

    /**
     * Save a reference.
     *
     * @param referenceDTO the entity to save.
     * @return the persisted entity.
     */
    public ReferenceDTO save(ReferenceDTO referenceDTO) {
        log.debug("Request to save Reference : {}", referenceDTO);
        Reference reference = referenceMapper.toEntity(referenceDTO);
        reference = referenceRepository.save(reference);
        return referenceMapper.toDto(reference);
    }

    /**
     * Partially update a reference.
     *
     * @param referenceDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ReferenceDTO> partialUpdate(ReferenceDTO referenceDTO) {
        log.debug("Request to partially update Reference : {}", referenceDTO);

        return referenceRepository
            .findById(referenceDTO.getId())
            .map(existingReference -> {
                referenceMapper.partialUpdate(existingReference, referenceDTO);

                return existingReference;
            })
            .map(referenceRepository::save)
            .map(referenceMapper::toDto);
    }

    /**
     * Get all the references.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ReferenceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all References");
        return referenceRepository.findAll(pageable).map(referenceMapper::toDto);
    }

    /**
     * Get one reference by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReferenceDTO> findOne(Long id) {
        log.debug("Request to get Reference : {}", id);
        return referenceRepository.findById(id).map(referenceMapper::toDto);
    }

    /**
     * Delete the reference by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Reference : {}", id);
        referenceRepository.deleteById(id);
    }
}
