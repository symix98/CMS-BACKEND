package net.ccc.apps.campmanage.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import net.ccc.apps.campmanage.domain.RefProject;
import net.ccc.apps.campmanage.repository.RefProjectRepository;
import net.ccc.apps.campmanage.service.RefProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RefProject}.
 */
@Service
@Transactional
public class RefProjectServiceImpl implements RefProjectService {

    private final Logger log = LoggerFactory.getLogger(RefProjectServiceImpl.class);

    private final RefProjectRepository refProjectRepository;

    public RefProjectServiceImpl(RefProjectRepository refProjectRepository) {
        this.refProjectRepository = refProjectRepository;
    }

    @Override
    public RefProject save(RefProject refProject) {
        log.debug("Request to save RefProject : {}", refProject);
        return refProjectRepository.save(refProject);
    }

    @Override
    public RefProject update(RefProject refProject) {
        log.debug("Request to save RefProject : {}", refProject);
        return refProjectRepository.save(refProject);
    }

    @Override
    public Optional<RefProject> partialUpdate(RefProject refProject) {
        log.debug("Request to partially update RefProject : {}", refProject);

        return refProjectRepository
            .findById(refProject.getId())
            .map(existingRefProject -> {
                if (refProject.getCode() != null) {
                    existingRefProject.setCode(refProject.getCode());
                }
                if (refProject.getName() != null) {
                    existingRefProject.setName(refProject.getName());
                }
                if (refProject.getDescription() != null) {
                    existingRefProject.setDescription(refProject.getDescription());
                }
                if (refProject.getCreatedBy() != null) {
                    existingRefProject.setCreatedBy(refProject.getCreatedBy());
                }
                if (refProject.getCreatedAt() != null) {
                    existingRefProject.setCreatedAt(refProject.getCreatedAt());
                }
                if (refProject.getModifyBy() != null) {
                    existingRefProject.setModifyBy(refProject.getModifyBy());
                }
                if (refProject.getModifyAt() != null) {
                    existingRefProject.setModifyAt(refProject.getModifyAt());
                }

                return existingRefProject;
            })
            .map(refProjectRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RefProject> findAll(Pageable pageable) {
        log.debug("Request to get all RefProjects");
        return refProjectRepository.findAll(pageable);
    }

    /**
     *  Get all the refProjects where EmployeeMaster is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RefProject> findAllWhereEmployeeMasterIsNull() {
        log.debug("Request to get all refProjects where EmployeeMaster is null");
        return StreamSupport
            .stream(refProjectRepository.findAll().spliterator(), false)
            .filter(refProject -> refProject.getEmployeeMaster() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefProject> findOne(Long id) {
        log.debug("Request to get RefProject : {}", id);
        return refProjectRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefProject : {}", id);
        refProjectRepository.deleteById(id);
    }
}
