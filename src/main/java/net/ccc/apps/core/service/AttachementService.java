package net.ccc.apps.core.service;

import java.util.Optional;

import net.ccc.apps.core.domain.AppUser;
import net.ccc.apps.core.domain.Attachement;
import net.ccc.apps.core.domain.enumeration.AttachementType;
import net.ccc.apps.core.repository.AttachementRepository;
import net.ccc.apps.core.service.dto.AttachementDTO;
import net.ccc.apps.core.service.mapper.AttachementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Attachement}.
 */
@Service
@Transactional
public class AttachementService {

    private final Logger log = LoggerFactory.getLogger(AttachementService.class);

    private final AttachementRepository attachementRepository;

    private final AttachementMapper attachementMapper;

    @Autowired
    private AppUserService appUserService;

    public AttachementService(AttachementRepository attachementRepository, AttachementMapper attachementMapper) {
        this.attachementRepository = attachementRepository;
        this.attachementMapper = attachementMapper;
    }

    /**
     * Save a attachement.
     *
     * @param attachementDTO the entity to save.
     * @return the persisted entity.
     */
    @Transactional
    public AttachementDTO save(AttachementDTO attachementDTO) {
        log.debug("Request to save Attachement : {}", attachementDTO);
        boolean isCreate = attachementDTO.getId() == null;
        AttachementType attachType=attachementDTO.getType();
        Attachement attachement = attachementMapper.toEntity(attachementDTO);
        attachement = attachementRepository.save(attachement);

        if(attachement!=null && isCreate && attachType==AttachementType.UserSignature){
            Long attachmentId = attachement.getId();
//            Optional<AppUser> loggedInUser = appUserService.findOne(UserService.currentLoggedInUser().getEmail());
            Optional<AppUser> loggedInUser = appUserService.findByEmail(UserService.currentLoggedInUser().getEmail());
            Attachement finalAttachement = attachement;
            loggedInUser.ifPresent(appUser -> {
                appUser.setAttachmentId(attachmentId);
                appUser.setAttachement(finalAttachement);
            });
        }

        return attachementMapper.toDto(attachement);
    }

    /**
     * Partially update a attachement.
     *
     * @param attachementDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AttachementDTO> partialUpdate(AttachementDTO attachementDTO) {
        log.debug("Request to partially update Attachement : {}", attachementDTO);

        return attachementRepository
            .findById(attachementDTO.getId())
            .map(existingAttachement -> {
                attachementMapper.partialUpdate(existingAttachement, attachementDTO);

                return existingAttachement;
            })
            .map(attachementRepository::save)
            .map(attachementMapper::toDto);
    }

    /**
     * Get all the attachements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AttachementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Attachements");
        return attachementRepository.findAll(pageable).map(attachementMapper::toDto);
    }

    /**
     * Get one attachement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AttachementDTO> findOne(Long id) {
        log.debug("Request to get Attachement : {}", id);
        return attachementRepository.findById(id).map(attachementMapper::toDto);
    }

    /**
     * Delete the attachement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Attachement : {}", id);
        attachementRepository.deleteById(id);
    }
}
