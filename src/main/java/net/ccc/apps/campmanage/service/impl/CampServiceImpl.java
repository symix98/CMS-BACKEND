package net.ccc.apps.campmanage.service.impl;

import java.util.Optional;
import net.ccc.apps.campmanage.domain.Camp;
import net.ccc.apps.campmanage.repository.CampRepository;
import net.ccc.apps.campmanage.service.CampService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Camp}.
 */
@Service
@Transactional
public class CampServiceImpl implements CampService {

    private final Logger log = LoggerFactory.getLogger(CampServiceImpl.class);

    private final CampRepository campRepository;

    public CampServiceImpl(CampRepository campRepository) {
        this.campRepository = campRepository;
    }

    @Override
    public Camp save(Camp camp) {
        log.debug("Request to save Camp : {}", camp);
        return campRepository.save(camp);
    }

    @Override
    public Camp update(Camp camp) {
        log.debug("Request to save Camp : {}", camp);
        return campRepository.save(camp);
    }

    @Override
    public Optional<Camp> partialUpdate(Camp camp) {
        log.debug("Request to partially update Camp : {}", camp);

        return campRepository
            .findById(camp.getId())
            .map(existingCamp -> {
                if (camp.getCampName() != null) {
                    existingCamp.setCampName(camp.getCampName());
                }
                if (camp.getLocation() != null) {
                    existingCamp.setLocation(camp.getLocation());
                }
                if (camp.getRemarks() != null) {
                    existingCamp.setRemarks(camp.getRemarks());
                }
                if (camp.getCreatedBy() != null) {
                    existingCamp.setCreatedBy(camp.getCreatedBy());
                }
                if (camp.getCreatedAt() != null) {
                    existingCamp.setCreatedAt(camp.getCreatedAt());
                }
                if (camp.getModifyBy() != null) {
                    existingCamp.setModifyBy(camp.getModifyBy());
                }
                if (camp.getModifyAt() != null) {
                    existingCamp.setModifyAt(camp.getModifyAt());
                }

                return existingCamp;
            })
            .map(campRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Camp> findAll(Pageable pageable) {
        log.debug("Request to get all Camps");
        return campRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Camp> findOne(Long id) {
        log.debug("Request to get Camp : {}", id);
        return campRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Camp : {}", id);
        campRepository.deleteById(id);
    }
}
