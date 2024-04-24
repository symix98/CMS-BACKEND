package net.ccc.apps.core.service;

import java.util.List;
import java.util.Optional;

import net.ccc.apps.core.domain.AppUser;
import net.ccc.apps.core.domain.SecurityPermission;
import net.ccc.apps.core.domain.composite.SecurityPermissionId;
import net.ccc.apps.core.repository.AppUserRepository;
import net.ccc.apps.core.repository.SecurityPermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SecurityPermission}.
 */
@Service
@Transactional
public class SecurityPermissionService {

    private final Logger log = LoggerFactory.getLogger(SecurityPermissionService.class);

    private final SecurityPermissionRepository securityPermissionRepository;

    private final AppUserRepository appUserRepository;

    public SecurityPermissionService(SecurityPermissionRepository securityPermissionRepository, AppUserRepository appUserRepository) {
        this.securityPermissionRepository = securityPermissionRepository;
        this.appUserRepository = appUserRepository;
    }

    /**
     * Save a securityPermission.
     *
     * @param securityPermission the entity to save.
     * @return the persisted entity.
     */
    public SecurityPermission save(SecurityPermission securityPermission) {
        log.debug("Request to save SecurityPermission : {}", securityPermission);
        return securityPermissionRepository.save(securityPermission);
    }

    /**
     * Partially update a securityPermission.
     *
     * @param securityPermission the entity to update partially.
     * @return the persisted entity.
     */
//    public Optional<SecurityPermission> partialUpdate(SecurityPermission securityPermission) {
//        log.debug("Request to partially update SecurityPermission : {}", securityPermission);
//
//        return securityPermissionRepository
//            .findById(securityPermission.getId())
//            .map(existingSecurityPermission -> {
////                if (securityPermission.getRole() != null) {
////                    existingSecurityPermission.setRole(securityPermission.getRole());
////                }
////                if (securityPermission.getObject() != null) {
////                    existingSecurityPermission.setObject(securityPermission.getObject());
////                }
//                if (securityPermission.getPermission() != null) {
//                    existingSecurityPermission.setPermission(securityPermission.getPermission());
//                }
//
//                return existingSecurityPermission;
//            })
//            .map(securityPermissionRepository::save);
//    }

    /**
     * Get all the securityPermissions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SecurityPermission> findAll() {
        log.debug("Request to get all SecurityPermissions");
        return securityPermissionRepository.findAll();
    }

    /**
     * Get one securityPermission by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SecurityPermission> findOne(SecurityPermissionId id) {
        log.debug("Request to get SecurityPermission : {}", id);
        return securityPermissionRepository.findById(id);
    }

    /**
     * Delete the securityPermission by id.
     *
     * @param id the id of the entity.
     */
    public void delete(SecurityPermissionId id) {
        log.debug("Request to delete SecurityPermission : {}", id);
        securityPermissionRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public List<SecurityPermission> findByRole(String role){
        return  securityPermissionRepository.findByIdRole(role);
    }

    public Optional<AppUser> getAppUser(String userId) {
        log.debug("Get AppUser : {}", userId);
        return appUserRepository.findById(userId);
    }
}
