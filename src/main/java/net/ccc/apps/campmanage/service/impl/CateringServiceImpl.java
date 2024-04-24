package net.ccc.apps.campmanage.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import net.ccc.apps.campmanage.domain.Catering;
import net.ccc.apps.campmanage.repository.CateringRepository;
import net.ccc.apps.campmanage.service.CateringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Catering}.
 */
@Service
@Transactional
public class CateringServiceImpl implements CateringService {

    private final Logger log = LoggerFactory.getLogger(CateringServiceImpl.class);

    private final CateringRepository cateringRepository;

    public CateringServiceImpl(CateringRepository cateringRepository) {
        this.cateringRepository = cateringRepository;
    }

    @Override
    public Catering save(Catering catering) {
        log.debug("Request to save Catering : {}", catering);
        return cateringRepository.save(catering);
    }

    @Override
    public Catering update(Catering catering) {
        log.debug("Request to save Catering : {}", catering);
        return cateringRepository.save(catering);
    }

    @Override
    public Optional<Catering> partialUpdate(Catering catering) {
        log.debug("Request to partially update Catering : {}", catering);

        return cateringRepository
            .findById(catering.getId())
            .map(existingCatering -> {
                if (catering.getCateringName() != null) {
                    existingCatering.setCateringName(catering.getCateringName());
                }
                if (catering.getLocation() != null) {
                    existingCatering.setLocation(catering.getLocation());
                }
                if (catering.getMessCategory() != null) {
                    existingCatering.setMessCategory(catering.getMessCategory());
                }
                if (catering.getMealType() != null) {
                    existingCatering.setMealType(catering.getMealType());
                }
                if (catering.getMenuType() != null) {
                    existingCatering.setMenuType(catering.getMenuType());
                }
                if (catering.getRate() != null) {
                    existingCatering.setRate(catering.getRate());
                }
                if (catering.getUpgradedRate() != null) {
                    existingCatering.setUpgradedRate(catering.getUpgradedRate());
                }
                if (catering.getRemarks() != null) {
                    existingCatering.setRemarks(catering.getRemarks());
                }
                if (catering.getCreatedBy() != null) {
                    existingCatering.setCreatedBy(catering.getCreatedBy());
                }
                if (catering.getCreatedAt() != null) {
                    existingCatering.setCreatedAt(catering.getCreatedAt());
                }
                if (catering.getModifyBy() != null) {
                    existingCatering.setModifyBy(catering.getModifyBy());
                }
                if (catering.getModifyAt() != null) {
                    existingCatering.setModifyAt(catering.getModifyAt());
                }

                return existingCatering;
            })
            .map(cateringRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Catering> findAll(Pageable pageable) {
        log.debug("Request to get all Caterings");
        return cateringRepository.findAll(pageable);
    }

    /**
     *  Get all the caterings where RoomDetails is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Catering> findAllWhereRoomDetailsIsNull() {
        log.debug("Request to get all caterings where RoomDetails is null");
        return StreamSupport
            .stream(cateringRepository.findAll().spliterator(), false)
            .filter(catering -> catering.getRoomDetails() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Catering> findOne(Long id) {
        log.debug("Request to get Catering : {}", id);
        return cateringRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Catering : {}", id);
        cateringRepository.deleteById(id);
    }
}
