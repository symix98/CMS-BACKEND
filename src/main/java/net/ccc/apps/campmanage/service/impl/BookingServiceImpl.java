package net.ccc.apps.campmanage.service.impl;

import java.util.Optional;
import net.ccc.apps.campmanage.domain.Booking;
import net.ccc.apps.campmanage.repository.BookingRepository;
import net.ccc.apps.campmanage.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Booking}.
 */
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking save(Booking booking) {
        log.debug("Request to save Booking : {}", booking);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Booking booking) {
        log.debug("Request to save Booking : {}", booking);
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> partialUpdate(Booking booking) {
        log.debug("Request to partially update Booking : {}", booking);

        return bookingRepository
            .findById(booking.getId())
            .map(existingBooking -> {
                if (booking.getBadgeNo() != null) {
                    existingBooking.setBadgeNo(booking.getBadgeNo());
                }
                if (booking.getRoomId() != null) {
                    existingBooking.setRoomId(booking.getRoomId());
                }
                if (booking.getCheckInDate() != null) {
                    existingBooking.setCheckInDate(booking.getCheckInDate());
                }
                if (booking.getCheckOutDate() != null) {
                    existingBooking.setCheckOutDate(booking.getCheckOutDate());
                }
                if (booking.getGuestStatus() != null) {
                    existingBooking.setGuestStatus(booking.getGuestStatus());
                }
                if (booking.getLeaveStartDate() != null) {
                    existingBooking.setLeaveStartDate(booking.getLeaveStartDate());
                }
                if (booking.getLeaveEndDate() != null) {
                    existingBooking.setLeaveEndDate(booking.getLeaveEndDate());
                }
                if (booking.getBedNo() != null) {
                    existingBooking.setBedNo(booking.getBedNo());
                }
                if (booking.getRemarks() != null) {
                    existingBooking.setRemarks(booking.getRemarks());
                }
                if (booking.getDocuploaded() != null) {
                    existingBooking.setDocuploaded(booking.getDocuploaded());
                }

                return existingBooking;
            })
            .map(bookingRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Booking> findAll(Pageable pageable) {
        log.debug("Request to get all Bookings");
        return bookingRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Booking> findOne(Long id) {
        log.debug("Request to get Booking : {}", id);
        return bookingRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Booking : {}", id);
        bookingRepository.deleteById(id);
    }
}
