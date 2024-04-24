package net.ccc.apps.campmanage.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import net.ccc.apps.campmanage.domain.RefNationality;
import net.ccc.apps.campmanage.repository.RefNationalityRepository;
import net.ccc.apps.campmanage.service.RefNationalityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RefNationality}.
 */
@Service
@Transactional
public class RefNationalityServiceImpl implements RefNationalityService {

    private final Logger log = LoggerFactory.getLogger(RefNationalityServiceImpl.class);

    private final RefNationalityRepository refNationalityRepository;

    public RefNationalityServiceImpl(RefNationalityRepository refNationalityRepository) {
        this.refNationalityRepository = refNationalityRepository;
    }

    @Override
    public RefNationality save(RefNationality refNationality) {
        log.debug("Request to save RefNationality : {}", refNationality);
        return refNationalityRepository.save(refNationality);
    }

    @Override
    public RefNationality update(RefNationality refNationality) {
        log.debug("Request to save RefNationality : {}", refNationality);
        return refNationalityRepository.save(refNationality);
    }

    @Override
    public Optional<RefNationality> partialUpdate(RefNationality refNationality) {
        log.debug("Request to partially update RefNationality : {}", refNationality);

        return refNationalityRepository
            .findById(refNationality.getId())
            .map(existingRefNationality -> {
                if (refNationality.getCode() != null) {
                    existingRefNationality.setCode(refNationality.getCode());
                }
                if (refNationality.getName() != null) {
                    existingRefNationality.setName(refNationality.getName());
                }
                if (refNationality.getDescription() != null) {
                    existingRefNationality.setDescription(refNationality.getDescription());
                }
                if (refNationality.getCreatedBy() != null) {
                    existingRefNationality.setCreatedBy(refNationality.getCreatedBy());
                }
                if (refNationality.getCreatedAt() != null) {
                    existingRefNationality.setCreatedAt(refNationality.getCreatedAt());
                }
                if (refNationality.getModifyBy() != null) {
                    existingRefNationality.setModifyBy(refNationality.getModifyBy());
                }
                if (refNationality.getModifyAt() != null) {
                    existingRefNationality.setModifyAt(refNationality.getModifyAt());
                }

                return existingRefNationality;
            })
            .map(refNationalityRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RefNationality> findAll(Pageable pageable) {
        log.debug("Request to get all RefNationalities");
        return refNationalityRepository.findAll(pageable);
    }

    /**
     *  Get all the refNationalities where EmployeeMaster is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RefNationality> findAllWhereEmployeeMasterIsNull() {
        log.debug("Request to get all refNationalities where EmployeeMaster is null");
        return StreamSupport
            .stream(refNationalityRepository.findAll().spliterator(), false)
            .filter(refNationality -> refNationality.getEmployeeMaster() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefNationality> findOne(Long id) {
        log.debug("Request to get RefNationality : {}", id);
        return refNationalityRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefNationality : {}", id);
        refNationalityRepository.deleteById(id);
    }
}
