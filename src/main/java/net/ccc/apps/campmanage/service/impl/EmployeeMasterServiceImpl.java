package net.ccc.apps.campmanage.service.impl;

import java.util.Optional;
import net.ccc.apps.campmanage.domain.EmployeeMaster;
import net.ccc.apps.campmanage.repository.EmployeeMasterRepository;
import net.ccc.apps.campmanage.service.EmployeeMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EmployeeMaster}.
 */
@Service
@Transactional
public class EmployeeMasterServiceImpl implements EmployeeMasterService {

    private final Logger log = LoggerFactory.getLogger(EmployeeMasterServiceImpl.class);

    private final EmployeeMasterRepository employeeMasterRepository;

    public EmployeeMasterServiceImpl(EmployeeMasterRepository employeeMasterRepository) {
        this.employeeMasterRepository = employeeMasterRepository;
    }

    @Override
    public EmployeeMaster save(EmployeeMaster employeeMaster) {
        log.debug("Request to save EmployeeMaster : {}", employeeMaster);
        return employeeMasterRepository.save(employeeMaster);
    }

    @Override
    public EmployeeMaster update(EmployeeMaster employeeMaster) {
        log.debug("Request to save EmployeeMaster : {}", employeeMaster);
        return employeeMasterRepository.save(employeeMaster);
    }

    @Override
    public Optional<EmployeeMaster> partialUpdate(EmployeeMaster employeeMaster) {
        log.debug("Request to partially update EmployeeMaster : {}", employeeMaster);

        return employeeMasterRepository
            .findById(employeeMaster.getId())
            .map(existingEmployeeMaster -> {
                if (employeeMaster.getBadgeNo() != null) {
                    existingEmployeeMaster.setBadgeNo(employeeMaster.getBadgeNo());
                }
                if (employeeMaster.getEmployeeName() != null) {
                    existingEmployeeMaster.setEmployeeName(employeeMaster.getEmployeeName());
                }
                if (employeeMaster.getJobTitle() != null) {
                    existingEmployeeMaster.setJobTitle(employeeMaster.getJobTitle());
                }
                if (employeeMaster.getDepartment() != null) {
                    existingEmployeeMaster.setDepartment(employeeMaster.getDepartment());
                }
                if (employeeMaster.getNationality() != null) {
                    existingEmployeeMaster.setNationality(employeeMaster.getNationality());
                }
                if (employeeMaster.getCategory() != null) {
                    existingEmployeeMaster.setCategory(employeeMaster.getCategory());
                }
                if (employeeMaster.getContractBase() != null) {
                    existingEmployeeMaster.setContractBase(employeeMaster.getContractBase());
                }
                if (employeeMaster.getBand() != null) {
                    existingEmployeeMaster.setBand(employeeMaster.getBand());
                }
                if (employeeMaster.getEqvBand() != null) {
                    existingEmployeeMaster.setEqvBand(employeeMaster.getEqvBand());
                }
                if (employeeMaster.getProject() != null) {
                    existingEmployeeMaster.setProject(employeeMaster.getProject());
                }
                if (employeeMaster.getIsCcc() != null) {
                    existingEmployeeMaster.setIsCcc(employeeMaster.getIsCcc());
                }
                if (employeeMaster.getCompany() != null) {
                    existingEmployeeMaster.setCompany(employeeMaster.getCompany());
                }
                if (employeeMaster.getWorkLocation() != null) {
                    existingEmployeeMaster.setWorkLocation(employeeMaster.getWorkLocation());
                }
                if (employeeMaster.getMessEntitlment() != null) {
                    existingEmployeeMaster.setMessEntitlment(employeeMaster.getMessEntitlment());
                }
                if (employeeMaster.getMealCategory() != null) {
                    existingEmployeeMaster.setMealCategory(employeeMaster.getMealCategory());
                }
                if (employeeMaster.getMealType() != null) {
                    existingEmployeeMaster.setMealType(employeeMaster.getMealType());
                }
                if (employeeMaster.getReligion() != null) {
                    existingEmployeeMaster.setReligion(employeeMaster.getReligion());
                }
                if (employeeMaster.getEmployeeActive() != null) {
                    existingEmployeeMaster.setEmployeeActive(employeeMaster.getEmployeeActive());
                }
                if (employeeMaster.getInactiveReason() != null) {
                    existingEmployeeMaster.setInactiveReason(employeeMaster.getInactiveReason());
                }
                if (employeeMaster.getMobileNo() != null) {
                    existingEmployeeMaster.setMobileNo(employeeMaster.getMobileNo());
                }
                if (employeeMaster.getPassportNo() != null) {
                    existingEmployeeMaster.setPassportNo(employeeMaster.getPassportNo());
                }
                if (employeeMaster.getQidNo() != null) {
                    existingEmployeeMaster.setQidNo(employeeMaster.getQidNo());
                }
                if (employeeMaster.getEmail() != null) {
                    existingEmployeeMaster.setEmail(employeeMaster.getEmail());
                }
                if (employeeMaster.getMessCard() != null) {
                    existingEmployeeMaster.setMessCard(employeeMaster.getMessCard());
                }
                if (employeeMaster.getMilkCard() != null) {
                    existingEmployeeMaster.setMilkCard(employeeMaster.getMilkCard());
                }
                if (employeeMaster.getCreatedBy() != null) {
                    existingEmployeeMaster.setCreatedBy(employeeMaster.getCreatedBy());
                }
                if (employeeMaster.getCreatedAt() != null) {
                    existingEmployeeMaster.setCreatedAt(employeeMaster.getCreatedAt());
                }
                if (employeeMaster.getModifyBy() != null) {
                    existingEmployeeMaster.setModifyBy(employeeMaster.getModifyBy());
                }
                if (employeeMaster.getModifyAt() != null) {
                    existingEmployeeMaster.setModifyAt(employeeMaster.getModifyAt());
                }

                return existingEmployeeMaster;
            })
            .map(employeeMasterRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeMaster> findAll(Pageable pageable) {
        log.debug("Request to get all EmployeeMasters");
        return employeeMasterRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeMaster> findOne(Long id) {
        log.debug("Request to get EmployeeMaster : {}", id);
        return employeeMasterRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EmployeeMaster : {}", id);
        employeeMasterRepository.deleteById(id);
    }
}
