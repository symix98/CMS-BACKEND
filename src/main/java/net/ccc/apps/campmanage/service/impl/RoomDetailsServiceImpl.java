package net.ccc.apps.campmanage.service.impl;

import java.util.Optional;
import net.ccc.apps.campmanage.domain.RoomDetails;
import net.ccc.apps.campmanage.repository.RoomDetailsRepository;
import net.ccc.apps.campmanage.service.RoomDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RoomDetails}.
 */
@Service
@Transactional
public class RoomDetailsServiceImpl implements RoomDetailsService {

    private final Logger log = LoggerFactory.getLogger(RoomDetailsServiceImpl.class);

    private final RoomDetailsRepository roomDetailsRepository;

    public RoomDetailsServiceImpl(RoomDetailsRepository roomDetailsRepository) {
        this.roomDetailsRepository = roomDetailsRepository;
    }

    @Override
    public RoomDetails save(RoomDetails roomDetails) {
        log.debug("Request to save RoomDetails : {}", roomDetails);
        return roomDetailsRepository.save(roomDetails);
    }

    @Override
    public RoomDetails update(RoomDetails roomDetails) {
        log.debug("Request to save RoomDetails : {}", roomDetails);
        return roomDetailsRepository.save(roomDetails);
    }

    @Override
    public Optional<RoomDetails> partialUpdate(RoomDetails roomDetails) {
        log.debug("Request to partially update RoomDetails : {}", roomDetails);

        return roomDetailsRepository
            .findById(roomDetails.getId())
            .map(existingRoomDetails -> {
                if (roomDetails.getRoomDescription() != null) {
                    existingRoomDetails.setRoomDescription(roomDetails.getRoomDescription());
                }
                if (roomDetails.getBlock() != null) {
                    existingRoomDetails.setBlock(roomDetails.getBlock());
                }
                if (roomDetails.getFloor() != null) {
                    existingRoomDetails.setFloor(roomDetails.getFloor());
                }
                if (roomDetails.getRoomCategory() != null) {
                    existingRoomDetails.setRoomCategory(roomDetails.getRoomCategory());
                }
                if (roomDetails.getBedCount() != null) {
                    existingRoomDetails.setBedCount(roomDetails.getBedCount());
                }
                if (roomDetails.getRoomNo() != null) {
                    existingRoomDetails.setRoomNo(roomDetails.getRoomNo());
                }
                if (roomDetails.getRoomAllocation() != null) {
                    existingRoomDetails.setRoomAllocation(roomDetails.getRoomAllocation());
                }
                if (roomDetails.getRoomConfiguration() != null) {
                    existingRoomDetails.setRoomConfiguration(roomDetails.getRoomConfiguration());
                }
                if (roomDetails.getAvailableFrom() != null) {
                    existingRoomDetails.setAvailableFrom(roomDetails.getAvailableFrom());
                }
                if (roomDetails.getRoomStatus() != null) {
                    existingRoomDetails.setRoomStatus(roomDetails.getRoomStatus());
                }
                if (roomDetails.getBedOnly() != null) {
                    existingRoomDetails.setBedOnly(roomDetails.getBedOnly());
                }
                if (roomDetails.getMonthlyRate() != null) {
                    existingRoomDetails.setMonthlyRate(roomDetails.getMonthlyRate());
                }
                if (roomDetails.getDailyRate() != null) {
                    existingRoomDetails.setDailyRate(roomDetails.getDailyRate());
                }
                if (roomDetails.getBedRate() != null) {
                    existingRoomDetails.setBedRate(roomDetails.getBedRate());
                }
                if (roomDetails.getReservationRate() != null) {
                    existingRoomDetails.setReservationRate(roomDetails.getReservationRate());
                }
                if (roomDetails.getRemarks() != null) {
                    existingRoomDetails.setRemarks(roomDetails.getRemarks());
                }
                if (roomDetails.getCreatedBy() != null) {
                    existingRoomDetails.setCreatedBy(roomDetails.getCreatedBy());
                }
                if (roomDetails.getCreatedAt() != null) {
                    existingRoomDetails.setCreatedAt(roomDetails.getCreatedAt());
                }
                if (roomDetails.getModifyBy() != null) {
                    existingRoomDetails.setModifyBy(roomDetails.getModifyBy());
                }
                if (roomDetails.getModifyAt() != null) {
                    existingRoomDetails.setModifyAt(roomDetails.getModifyAt());
                }

                return existingRoomDetails;
            })
            .map(roomDetailsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RoomDetails> findAll(Pageable pageable) {
        log.debug("Request to get all RoomDetails");
        return roomDetailsRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoomDetails> findOne(Long id) {
        log.debug("Request to get RoomDetails : {}", id);
        return roomDetailsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RoomDetails : {}", id);
        roomDetailsRepository.deleteById(id);
    }
}
