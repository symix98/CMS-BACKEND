package net.ccc.apps.campmanage.service.impl;

import java.util.Optional;
import net.ccc.apps.campmanage.domain.Services;
import net.ccc.apps.campmanage.repository.ServicesRepository;
import net.ccc.apps.campmanage.service.ServicesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Services}.
 */
@Service
@Transactional
public class ServicesServiceImpl implements ServicesService {

    private final Logger log = LoggerFactory.getLogger(ServicesServiceImpl.class);

    private final ServicesRepository servicesRepository;

    public ServicesServiceImpl(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    @Override
    public Services save(Services services) {
        log.debug("Request to save Services : {}", services);
        return servicesRepository.save(services);
    }

    @Override
    public Services update(Services services) {
        log.debug("Request to save Services : {}", services);
        return servicesRepository.save(services);
    }

    @Override
    public Optional<Services> partialUpdate(Services services) {
        log.debug("Request to partially update Services : {}", services);

        return servicesRepository
            .findById(services.getId())
            .map(existingServices -> {
                if (services.getServiceName() != null) {
                    existingServices.setServiceName(services.getServiceName());
                }
                if (services.getDescription() != null) {
                    existingServices.setDescription(services.getDescription());
                }
                if (services.getCreatedBy() != null) {
                    existingServices.setCreatedBy(services.getCreatedBy());
                }
                if (services.getCreatedAt() != null) {
                    existingServices.setCreatedAt(services.getCreatedAt());
                }
                if (services.getModifyBy() != null) {
                    existingServices.setModifyBy(services.getModifyBy());
                }
                if (services.getModifyAt() != null) {
                    existingServices.setModifyAt(services.getModifyAt());
                }

                return existingServices;
            })
            .map(servicesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Services> findAll(Pageable pageable) {
        log.debug("Request to get all Services");
        return servicesRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Services> findOne(Long id) {
        log.debug("Request to get Services : {}", id);
        return servicesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Services : {}", id);
        servicesRepository.deleteById(id);
    }
}
