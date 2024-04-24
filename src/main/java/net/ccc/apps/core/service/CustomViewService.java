package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.CustomView;
import net.ccc.apps.core.repository.CustomViewRepository;
import net.ccc.apps.core.service.dto.CustomViewDTO;
import net.ccc.apps.core.service.mapper.CustomViewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CustomView}.
 */
@Service
@Transactional
public class CustomViewService {

    private final Logger log = LoggerFactory.getLogger(CustomViewService.class);

    private final CustomViewRepository customViewRepository;

    private final CustomViewMapper customViewMapper;

    public CustomViewService(CustomViewRepository customViewRepository, CustomViewMapper customViewMapper) {
        this.customViewRepository = customViewRepository;
        this.customViewMapper = customViewMapper;
    }

    /**
     * Save a customView.
     *
     * @param customViewDTO the entity to save.
     * @return the persisted entity.
     */
    public CustomViewDTO save(CustomViewDTO customViewDTO) {
        log.debug("Request to save CustomView : {}", customViewDTO);
        CustomView customView = customViewMapper.toEntity(customViewDTO);
        customView = customViewRepository.save(customView);
        return customViewMapper.toDto(customView);
    }

    /**
     * Partially update a customView.
     *
     * @param customViewDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CustomViewDTO> partialUpdate(CustomViewDTO customViewDTO) {
        log.debug("Request to partially update CustomView : {}", customViewDTO);

        return customViewRepository
            .findById(customViewDTO.getId())
            .map(existingCustomView -> {
                customViewMapper.partialUpdate(existingCustomView, customViewDTO);

                return existingCustomView;
            })
            .map(customViewRepository::save)
            .map(customViewMapper::toDto);
    }

    /**
     * Get all the customViews.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CustomViewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomViews");
        return customViewRepository.findAll(pageable).map(customViewMapper::toDto);
    }

    /**
     * Get one customView by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CustomViewDTO> findOne(Long id) {
        log.debug("Request to get CustomView : {}", id);
        return customViewRepository.findById(id).map(customViewMapper::toDto);
    }

    /**
     * Delete the customView by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CustomView : {}", id);
        customViewRepository.deleteById(id);
    }
}
