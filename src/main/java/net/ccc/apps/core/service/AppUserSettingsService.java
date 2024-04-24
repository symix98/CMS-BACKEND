package net.ccc.apps.core.service;

import java.util.Optional;
import net.ccc.apps.core.domain.AppUser;
import net.ccc.apps.core.domain.AppUserSettings;
import net.ccc.apps.core.repository.AppUserSettingsRepository;
import net.ccc.apps.core.service.dto.AppUserDTO;
import net.ccc.apps.core.service.dto.AppUserSettingsDTO;
import net.ccc.apps.core.service.mapper.AppUserMapper;
import net.ccc.apps.core.service.mapper.AppUserSettingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AppUserSettings}.
 */
@Service
@Transactional
public class AppUserSettingsService {

    private final Logger log = LoggerFactory.getLogger(AppUserSettingsService.class);

    private final AppUserSettingsRepository appUserSettingsRepository;

    private final AppUserSettingsMapper appUserSettingsMapper;

    public AppUserSettingsService(AppUserSettingsRepository appUserSettingsRepository, AppUserSettingsMapper appUserSettingsMapper) {
        this.appUserSettingsRepository = appUserSettingsRepository;
        this.appUserSettingsMapper = appUserSettingsMapper;
    }

    /**
     * Save a appUserSettings.
     *
     * @param appUserSettingsDTO the entity to save.
     * @return the persisted entity.
     */
    public AppUserSettingsDTO save(AppUserSettingsDTO appUserSettingsDTO) {
        log.debug("Request to save AppUserSettings : {}", appUserSettingsDTO);
        AppUserSettings appUserSettings = appUserSettingsMapper.toEntity(appUserSettingsDTO);
        appUserSettings = appUserSettingsRepository.save(appUserSettings);
        return appUserSettingsMapper.toDto(appUserSettings);
    }

    /**
     * Partially update a appUserSettings.
     *
     * @param appUserSettingsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AppUserSettingsDTO> partialUpdate(AppUserSettingsDTO appUserSettingsDTO) {
        log.debug("Request to partially update AppUserSettings : {}", appUserSettingsDTO);

        return appUserSettingsRepository
            .findById(appUserSettingsDTO.getId())
            .map(existingAppUserSettings -> {
                appUserSettingsMapper.partialUpdate(existingAppUserSettings, appUserSettingsDTO);

                return existingAppUserSettings;
            })
            .map(appUserSettingsRepository::save)
            .map(appUserSettingsMapper::toDto);
    }

    /**
     * Get all the appUserSettings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AppUserSettingsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AppUserSettings");
        return appUserSettingsRepository.findAll(pageable).map(appUserSettingsMapper::toDto);
    }

    /**
     * Get one appUserSettings by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AppUserSettingsDTO> findOne(Long id) {
        log.debug("Request to get AppUserSettings : {}", id);
        return appUserSettingsRepository.findById(id).map(appUserSettingsMapper::toDto);
    }

    /**
     * Delete the appUserSettings by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AppUserSettings : {}", id);
        appUserSettingsRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<AppUserSettingsDTO> getAppUserSettingByUserId(Pageable page, String userId) {
        log.debug("Get AppUserSettingByUserId : {}", userId);
        return appUserSettingsRepository.findByAppUserId(page, userId).map(appUserSettingsMapper::toDto);
    }

    @Transactional(readOnly = true)
    public String getSupervisorByUserId(String userId) {
        log.debug("Get AppUserSettingByUserId : {}", userId);
        return appUserSettingsRepository.findSupervisorByUserId(userId);
    }
}
