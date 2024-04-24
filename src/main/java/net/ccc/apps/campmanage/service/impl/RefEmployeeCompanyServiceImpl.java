package net.ccc.apps.campmanage.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import net.ccc.apps.campmanage.domain.RefEmployeeCompany;
import net.ccc.apps.campmanage.repository.RefEmployeeCompanyRepository;
import net.ccc.apps.campmanage.service.RefEmployeeCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RefEmployeeCompany}.
 */
@Service
@Transactional
public class RefEmployeeCompanyServiceImpl implements RefEmployeeCompanyService {

    private final Logger log = LoggerFactory.getLogger(RefEmployeeCompanyServiceImpl.class);

    private final RefEmployeeCompanyRepository refEmployeeCompanyRepository;

    public RefEmployeeCompanyServiceImpl(RefEmployeeCompanyRepository refEmployeeCompanyRepository) {
        this.refEmployeeCompanyRepository = refEmployeeCompanyRepository;
    }

    @Override
    public RefEmployeeCompany save(RefEmployeeCompany refEmployeeCompany) {
        log.debug("Request to save RefEmployeeCompany : {}", refEmployeeCompany);
        return refEmployeeCompanyRepository.save(refEmployeeCompany);
    }

    @Override
    public RefEmployeeCompany update(RefEmployeeCompany refEmployeeCompany) {
        log.debug("Request to save RefEmployeeCompany : {}", refEmployeeCompany);
        return refEmployeeCompanyRepository.save(refEmployeeCompany);
    }

    @Override
    public Optional<RefEmployeeCompany> partialUpdate(RefEmployeeCompany refEmployeeCompany) {
        log.debug("Request to partially update RefEmployeeCompany : {}", refEmployeeCompany);

        return refEmployeeCompanyRepository
            .findById(refEmployeeCompany.getId())
            .map(existingRefEmployeeCompany -> {
                if (refEmployeeCompany.getCode() != null) {
                    existingRefEmployeeCompany.setCode(refEmployeeCompany.getCode());
                }
                if (refEmployeeCompany.getName() != null) {
                    existingRefEmployeeCompany.setName(refEmployeeCompany.getName());
                }
                if (refEmployeeCompany.getDescription() != null) {
                    existingRefEmployeeCompany.setDescription(refEmployeeCompany.getDescription());
                }
                if (refEmployeeCompany.getCreatedBy() != null) {
                    existingRefEmployeeCompany.setCreatedBy(refEmployeeCompany.getCreatedBy());
                }
                if (refEmployeeCompany.getCreatedAt() != null) {
                    existingRefEmployeeCompany.setCreatedAt(refEmployeeCompany.getCreatedAt());
                }
                if (refEmployeeCompany.getModifyBy() != null) {
                    existingRefEmployeeCompany.setModifyBy(refEmployeeCompany.getModifyBy());
                }
                if (refEmployeeCompany.getModifyAt() != null) {
                    existingRefEmployeeCompany.setModifyAt(refEmployeeCompany.getModifyAt());
                }

                return existingRefEmployeeCompany;
            })
            .map(refEmployeeCompanyRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RefEmployeeCompany> findAll(Pageable pageable) {
        log.debug("Request to get all RefEmployeeCompanies");
        return refEmployeeCompanyRepository.findAll(pageable);
    }

    /**
     *  Get all the refEmployeeCompanies where EmployeeMaster is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RefEmployeeCompany> findAllWhereEmployeeMasterIsNull() {
        log.debug("Request to get all refEmployeeCompanies where EmployeeMaster is null");
        return StreamSupport
            .stream(refEmployeeCompanyRepository.findAll().spliterator(), false)
            .filter(refEmployeeCompany -> refEmployeeCompany.getEmployeeMaster() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefEmployeeCompany> findOne(Long id) {
        log.debug("Request to get RefEmployeeCompany : {}", id);
        return refEmployeeCompanyRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefEmployeeCompany : {}", id);
        refEmployeeCompanyRepository.deleteById(id);
    }
}
