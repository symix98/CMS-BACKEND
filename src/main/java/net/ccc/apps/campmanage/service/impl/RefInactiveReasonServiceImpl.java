package net.ccc.apps.campmanage.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import net.ccc.apps.campmanage.domain.RefInactiveReason;
import net.ccc.apps.campmanage.repository.RefInactiveReasonRepository;
import net.ccc.apps.campmanage.service.RefInactiveReasonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RefInactiveReason}.
 */
@Service
@Transactional
public class RefInactiveReasonServiceImpl implements RefInactiveReasonService {

    private final Logger log = LoggerFactory.getLogger(RefInactiveReasonServiceImpl.class);

    private final RefInactiveReasonRepository refInactiveReasonRepository;

    public RefInactiveReasonServiceImpl(RefInactiveReasonRepository refInactiveReasonRepository) {
        this.refInactiveReasonRepository = refInactiveReasonRepository;
    }

    @Override
    public RefInactiveReason save(RefInactiveReason refInactiveReason) {
        log.debug("Request to save RefInactiveReason : {}", refInactiveReason);
        return refInactiveReasonRepository.save(refInactiveReason);
    }

    @Override
    public RefInactiveReason update(RefInactiveReason refInactiveReason) {
        log.debug("Request to save RefInactiveReason : {}", refInactiveReason);
        return refInactiveReasonRepository.save(refInactiveReason);
    }

    @Override
    public Optional<RefInactiveReason> partialUpdate(RefInactiveReason refInactiveReason) {
        log.debug("Request to partially update RefInactiveReason : {}", refInactiveReason);

        return refInactiveReasonRepository
            .findById(refInactiveReason.getId())
            .map(existingRefInactiveReason -> {
                if (refInactiveReason.getCode() != null) {
                    existingRefInactiveReason.setCode(refInactiveReason.getCode());
                }
                if (refInactiveReason.getName() != null) {
                    existingRefInactiveReason.setName(refInactiveReason.getName());
                }
                if (refInactiveReason.getDescription() != null) {
                    existingRefInactiveReason.setDescription(refInactiveReason.getDescription());
                }
                if (refInactiveReason.getCategory() != null) {
                    existingRefInactiveReason.setCategory(refInactiveReason.getCategory());
                }
                if (refInactiveReason.getCreatedBy() != null) {
                    existingRefInactiveReason.setCreatedBy(refInactiveReason.getCreatedBy());
                }
                if (refInactiveReason.getCreatedAt() != null) {
                    existingRefInactiveReason.setCreatedAt(refInactiveReason.getCreatedAt());
                }
                if (refInactiveReason.getModifyBy() != null) {
                    existingRefInactiveReason.setModifyBy(refInactiveReason.getModifyBy());
                }
                if (refInactiveReason.getModifyAt() != null) {
                    existingRefInactiveReason.setModifyAt(refInactiveReason.getModifyAt());
                }

                return existingRefInactiveReason;
            })
            .map(refInactiveReasonRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RefInactiveReason> findAll(Pageable pageable) {
        log.debug("Request to get all RefInactiveReasons");
        return refInactiveReasonRepository.findAll(pageable);
    }

    /**
     *  Get all the refInactiveReasons where EmployeeMaster is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RefInactiveReason> findAllWhereEmployeeMasterIsNull() {
        log.debug("Request to get all refInactiveReasons where EmployeeMaster is null");
        return StreamSupport
            .stream(refInactiveReasonRepository.findAll().spliterator(), false)
            .filter(refInactiveReason -> refInactiveReason.getEmployeeMaster() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefInactiveReason> findOne(Long id) {
        log.debug("Request to get RefInactiveReason : {}", id);
        return refInactiveReasonRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefInactiveReason : {}", id);
        refInactiveReasonRepository.deleteById(id);
    }
}
