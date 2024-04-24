package net.ccc.apps.campmanage.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import net.ccc.apps.campmanage.domain.RefMealCategory;
import net.ccc.apps.campmanage.repository.RefMealCategoryRepository;
import net.ccc.apps.campmanage.service.RefMealCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RefMealCategory}.
 */
@Service
@Transactional
public class RefMealCategoryServiceImpl implements RefMealCategoryService {

    private final Logger log = LoggerFactory.getLogger(RefMealCategoryServiceImpl.class);

    private final RefMealCategoryRepository refMealCategoryRepository;

    public RefMealCategoryServiceImpl(RefMealCategoryRepository refMealCategoryRepository) {
        this.refMealCategoryRepository = refMealCategoryRepository;
    }

    @Override
    public RefMealCategory save(RefMealCategory refMealCategory) {
        log.debug("Request to save RefMealCategory : {}", refMealCategory);
        return refMealCategoryRepository.save(refMealCategory);
    }

    @Override
    public RefMealCategory update(RefMealCategory refMealCategory) {
        log.debug("Request to save RefMealCategory : {}", refMealCategory);
        return refMealCategoryRepository.save(refMealCategory);
    }

    @Override
    public Optional<RefMealCategory> partialUpdate(RefMealCategory refMealCategory) {
        log.debug("Request to partially update RefMealCategory : {}", refMealCategory);

        return refMealCategoryRepository
            .findById(refMealCategory.getId())
            .map(existingRefMealCategory -> {
                if (refMealCategory.getCode() != null) {
                    existingRefMealCategory.setCode(refMealCategory.getCode());
                }
                if (refMealCategory.getName() != null) {
                    existingRefMealCategory.setName(refMealCategory.getName());
                }
                if (refMealCategory.getDescription() != null) {
                    existingRefMealCategory.setDescription(refMealCategory.getDescription());
                }
                if (refMealCategory.getCreatedBy() != null) {
                    existingRefMealCategory.setCreatedBy(refMealCategory.getCreatedBy());
                }
                if (refMealCategory.getCreatedAt() != null) {
                    existingRefMealCategory.setCreatedAt(refMealCategory.getCreatedAt());
                }
                if (refMealCategory.getModifyBy() != null) {
                    existingRefMealCategory.setModifyBy(refMealCategory.getModifyBy());
                }
                if (refMealCategory.getModifyAt() != null) {
                    existingRefMealCategory.setModifyAt(refMealCategory.getModifyAt());
                }

                return existingRefMealCategory;
            })
            .map(refMealCategoryRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RefMealCategory> findAll(Pageable pageable) {
        log.debug("Request to get all RefMealCategories");
        return refMealCategoryRepository.findAll(pageable);
    }

    /**
     *  Get all the refMealCategories where EmployeeMaster is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RefMealCategory> findAllWhereEmployeeMasterIsNull() {
        log.debug("Request to get all refMealCategories where EmployeeMaster is null");
        return StreamSupport
            .stream(refMealCategoryRepository.findAll().spliterator(), false)
            .filter(refMealCategory -> refMealCategory.getEmployeeMaster() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefMealCategory> findOne(Long id) {
        log.debug("Request to get RefMealCategory : {}", id);
        return refMealCategoryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefMealCategory : {}", id);
        refMealCategoryRepository.deleteById(id);
    }
}
