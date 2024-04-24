package net.ccc.apps.campmanage.service.impl;

import java.util.Optional;
import net.ccc.apps.campmanage.domain.RoomAdvanceBooking;
import net.ccc.apps.campmanage.repository.RoomAdvanceBookingRepository;
import net.ccc.apps.campmanage.service.RoomAdvanceBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RoomAdvanceBooking}.
 */
@Service
@Transactional
public class RoomAdvanceBookingServiceImpl implements RoomAdvanceBookingService {

    private final Logger log = LoggerFactory.getLogger(RoomAdvanceBookingServiceImpl.class);

    private final RoomAdvanceBookingRepository roomAdvanceBookingRepository;

    public RoomAdvanceBookingServiceImpl(RoomAdvanceBookingRepository roomAdvanceBookingRepository) {
        this.roomAdvanceBookingRepository = roomAdvanceBookingRepository;
    }

    @Override
    public RoomAdvanceBooking save(RoomAdvanceBooking roomAdvanceBooking) {
        log.debug("Request to save RoomAdvanceBooking : {}", roomAdvanceBooking);
        return roomAdvanceBookingRepository.save(roomAdvanceBooking);
    }

    @Override
    public RoomAdvanceBooking update(RoomAdvanceBooking roomAdvanceBooking) {
        log.debug("Request to save RoomAdvanceBooking : {}", roomAdvanceBooking);
        return roomAdvanceBookingRepository.save(roomAdvanceBooking);
    }

    @Override
    public Optional<RoomAdvanceBooking> partialUpdate(RoomAdvanceBooking roomAdvanceBooking) {
        log.debug("Request to partially update RoomAdvanceBooking : {}", roomAdvanceBooking);

        return roomAdvanceBookingRepository
            .findById(roomAdvanceBooking.getId())
            .map(existingRoomAdvanceBooking -> {
                if (roomAdvanceBooking.getBookingStartDate() != null) {
                    existingRoomAdvanceBooking.setBookingStartDate(roomAdvanceBooking.getBookingStartDate());
                }
                if (roomAdvanceBooking.getBookingEndDate() != null) {
                    existingRoomAdvanceBooking.setBookingEndDate(roomAdvanceBooking.getBookingEndDate());
                }
                if (roomAdvanceBooking.getRemarks() != null) {
                    existingRoomAdvanceBooking.setRemarks(roomAdvanceBooking.getRemarks());
                }
                if (roomAdvanceBooking.getCreatedBy() != null) {
                    existingRoomAdvanceBooking.setCreatedBy(roomAdvanceBooking.getCreatedBy());
                }
                if (roomAdvanceBooking.getCreatedAt() != null) {
                    existingRoomAdvanceBooking.setCreatedAt(roomAdvanceBooking.getCreatedAt());
                }
                if (roomAdvanceBooking.getModifyBy() != null) {
                    existingRoomAdvanceBooking.setModifyBy(roomAdvanceBooking.getModifyBy());
                }
                if (roomAdvanceBooking.getModifyAt() != null) {
                    existingRoomAdvanceBooking.setModifyAt(roomAdvanceBooking.getModifyAt());
                }

                return existingRoomAdvanceBooking;
            })
            .map(roomAdvanceBookingRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RoomAdvanceBooking> findAll(Pageable pageable) {
        log.debug("Request to get all RoomAdvanceBookings");
        return roomAdvanceBookingRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RoomAdvanceBooking> findOne(Long id) {
        log.debug("Request to get RoomAdvanceBooking : {}", id);
        return roomAdvanceBookingRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RoomAdvanceBooking : {}", id);
        roomAdvanceBookingRepository.deleteById(id);
    }
}
