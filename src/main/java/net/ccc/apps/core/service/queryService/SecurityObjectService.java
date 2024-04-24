package net.ccc.apps.core.service.queryService;

import java.util.List;
import java.util.Optional;
import net.ccc.apps.core.domain.SecurityObject;
import net.ccc.apps.core.repository.SecurityObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SecurityObject}.
 */
@Service
@Transactional
public class SecurityObjectService {

    private final Logger log = LoggerFactory.getLogger(SecurityObjectService.class);

    private final SecurityObjectRepository securityObjectRepository;

    public SecurityObjectService(SecurityObjectRepository securityObjectRepository) {
        this.securityObjectRepository = securityObjectRepository;
    }

    /**
     * Save a securityObject.
     *
     * @param securityObject the entity to save.
     * @return the persisted entity.
     */
    public SecurityObject save(SecurityObject securityObject) {
        log.debug("Request to save SecurityObject : {}", securityObject);
        return securityObjectRepository.save(securityObject);
    }

    /**
     * Partially update a securityObject.
     *
     * @param securityObject the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SecurityObject> partialUpdate(SecurityObject securityObject) {
        log.debug("Request to partially update SecurityObject : {}", securityObject);

        return securityObjectRepository
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
    }

    /**
     * Get all the securityObjects.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SecurityObject> findAll() {
        log.debug("Request to get all SecurityObjects");
        return securityObjectRepository.findAll();
    }

    /**
     * Get one securityObject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SecurityObject> findOne(Long id) {
        log.debug("Request to get SecurityObject : {}", id);
        return securityObjectRepository.findById(id);
    }

    /**
     * Delete the securityObject by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SecurityObject : {}", id);
        securityObjectRepository.deleteById(id);
    }
}
