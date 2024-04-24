package net.ccc.apps.campmanage.service.impl;

import java.util.Optional;
import net.ccc.apps.campmanage.domain.BedDetails;
import net.ccc.apps.campmanage.repository.BedDetailsRepository;
import net.ccc.apps.campmanage.service.BedDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BedDetails}.
 */
@Service
@Transactional
public class BedDetailsServiceImpl implements BedDetailsService {

    private final Logger log = LoggerFactory.getLogger(BedDetailsServiceImpl.class);

    private final BedDetailsRepository bedDetailsRepository;

    public BedDetailsServiceImpl(BedDetailsRepository bedDetailsRepository) {
        this.bedDetailsRepository = bedDetailsRepository;
    }

    @Override
    public BedDetails save(BedDetails bedDetails) {
        log.debug("Request to save BedDetails : {}", bedDetails);
        return bedDetailsRepository.save(bedDetails);
    }

    @Override
    public BedDetails update(BedDetails bedDetails) {
        log.debug("Request to save BedDetails : {}", bedDetails);
        return bedDetailsRepository.save(bedDetails);
    }

    @Override
    public Optional<BedDetails> partialUpdate(BedDetails bedDetails) {
        log.debug("Request to partially update BedDetails : {}", bedDetails);

        return bedDetailsRepository
            .findById(bedDetails.getId())
            .map(existingBedDetails -> {
                if (bedDetails.getBedNo() != null) {
                    existingBedDetails.setBedNo(bedDetails.getBedNo());
                }
                if (bedDetails.getBedStatus() != null) {
                    existingBedDetails.setBedStatus(bedDetails.getBedStatus());
                }
                if (bedDetails.getRemarks() != null) {
                    existingBedDetails.setRemarks(bedDetails.getRemarks());
                }

                return existingBedDetails;
            })
            .map(bedDetailsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BedDetails> findAll(Pageable pageable) {
        log.debug("Request to get all BedDetails");
        return bedDetailsRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BedDetails> findOne(Long id) {
        log.debug("Request to get BedDetails : {}", id);
        return bedDetailsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BedDetails : {}", id);
        bedDetailsRepository.deleteById(id);
    }
}
