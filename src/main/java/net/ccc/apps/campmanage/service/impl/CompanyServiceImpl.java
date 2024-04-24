package net.ccc.apps.campmanage.service.impl;

import java.util.Optional;
import net.ccc.apps.campmanage.domain.Company;
import net.ccc.apps.campmanage.repository.CompanyRepository;
import net.ccc.apps.campmanage.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Company}.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company save(Company company) {
        log.debug("Request to save Company : {}", company);
        return companyRepository.save(company);
    }

    @Override
    public Company update(Company company) {
        log.debug("Request to save Company : {}", company);
        return companyRepository.save(company);
    }

    @Override
    public Optional<Company> partialUpdate(Company company) {
        log.debug("Request to partially update Company : {}", company);

        return companyRepository
            .findById(company.getId())
            .map(existingCompany -> {
                if (company.getCompanyName() != null) {
                    existingCompany.setCompanyName(company.getCompanyName());
                }
                if (company.getLocation() != null) {
                    existingCompany.setLocation(company.getLocation());
                }
                if (company.getServicesProvided() != null) {
                    existingCompany.setServicesProvided(company.getServicesProvided());
                }
                if (company.getContactPerson() != null) {
                    existingCompany.setContactPerson(company.getContactPerson());
                }
                if (company.getContactNo() != null) {
                    existingCompany.setContactNo(company.getContactNo());
                }
                if (company.getContactEmail() != null) {
                    existingCompany.setContactEmail(company.getContactEmail());
                }
                if (company.getRemarks() != null) {
                    existingCompany.setRemarks(company.getRemarks());
                }
                if (company.getCreatedBy() != null) {
                    existingCompany.setCreatedBy(company.getCreatedBy());
                }
                if (company.getCreatedAt() != null) {
                    existingCompany.setCreatedAt(company.getCreatedAt());
                }
                if (company.getModifyBy() != null) {
                    existingCompany.setModifyBy(company.getModifyBy());
                }
                if (company.getModifyAt() != null) {
                    existingCompany.setModifyAt(company.getModifyAt());
                }

                return existingCompany;
            })
            .map(companyRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Company> findAll(Pageable pageable) {
        log.debug("Request to get all Companies");
        return companyRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Company> findOne(Long id) {
        log.debug("Request to get Company : {}", id);
        return companyRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Company : {}", id);
        companyRepository.deleteById(id);
    }
}
