package net.ccc.apps.campmanage.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import net.ccc.apps.campmanage.domain.RefTrade;
import net.ccc.apps.campmanage.repository.RefTradeRepository;
import net.ccc.apps.campmanage.service.RefTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RefTrade}.
 */
@Service
@Transactional
public class RefTradeServiceImpl implements RefTradeService {

    private final Logger log = LoggerFactory.getLogger(RefTradeServiceImpl.class);

    private final RefTradeRepository refTradeRepository;

    public RefTradeServiceImpl(RefTradeRepository refTradeRepository) {
        this.refTradeRepository = refTradeRepository;
    }

    @Override
    public RefTrade save(RefTrade refTrade) {
        log.debug("Request to save RefTrade : {}", refTrade);
        return refTradeRepository.save(refTrade);
    }

    @Override
    public RefTrade update(RefTrade refTrade) {
        log.debug("Request to save RefTrade : {}", refTrade);
        return refTradeRepository.save(refTrade);
    }

    @Override
    public Optional<RefTrade> partialUpdate(RefTrade refTrade) {
        log.debug("Request to partially update RefTrade : {}", refTrade);

        return refTradeRepository
            .findById(refTrade.getId())
            .map(existingRefTrade -> {
                if (refTrade.getCode() != null) {
                    existingRefTrade.setCode(refTrade.getCode());
                }
                if (refTrade.getName() != null) {
                    existingRefTrade.setName(refTrade.getName());
                }
                if (refTrade.getDescription() != null) {
                    existingRefTrade.setDescription(refTrade.getDescription());
                }
                if (refTrade.getCreatedBy() != null) {
                    existingRefTrade.setCreatedBy(refTrade.getCreatedBy());
                }
                if (refTrade.getCreatedAt() != null) {
                    existingRefTrade.setCreatedAt(refTrade.getCreatedAt());
                }
                if (refTrade.getModifyBy() != null) {
                    existingRefTrade.setModifyBy(refTrade.getModifyBy());
                }
                if (refTrade.getModifyAt() != null) {
                    existingRefTrade.setModifyAt(refTrade.getModifyAt());
                }

                return existingRefTrade;
            })
            .map(refTradeRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RefTrade> findAll(Pageable pageable) {
        log.debug("Request to get all RefTrades");
        return refTradeRepository.findAll(pageable);
    }

    /**
     *  Get all the refTrades where EmployeeMaster is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RefTrade> findAllWhereEmployeeMasterIsNull() {
        log.debug("Request to get all refTrades where EmployeeMaster is null");
        return StreamSupport
            .stream(refTradeRepository.findAll().spliterator(), false)
            .filter(refTrade -> refTrade.getEmployeeMaster() == null)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefTrade> findOne(Long id) {
        log.debug("Request to get RefTrade : {}", id);
        return refTradeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefTrade : {}", id);
        refTradeRepository.deleteById(id);
    }
}
