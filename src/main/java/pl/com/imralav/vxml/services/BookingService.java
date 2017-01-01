package pl.com.imralav.vxml.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.com.imralav.vxml.entities.Booking;
import pl.com.imralav.vxml.entities.dtos.BookingDto;
import pl.com.imralav.vxml.repositories.BookingRepository;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private DateTimeService dateTimeService;

    @Autowired
    private ReadableSeatsBuilder readableSeatsBuilder;

    public Booking findByCustomerCode(int customerCode) {
        return bookingRepository.findByCustomerCode(customerCode);
    }

    public BookingDto toDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setMovieTitle(booking.getMovieTitle());
        String readableDate = dateTimeService.toReadable(booking.getShowingDate());
        dto.setReadableDate(readableDate);
        String readableTime = dateTimeService.toReadable(booking.getShowingTime());
        dto.setReadableTime(readableTime);
        dto.setReadableSeats(readableSeatsBuilder.buildFor(booking.getSeats()));
        return dto;
    }

    public Booking toEntity(BookingDto dto) {
        return null;
    }

    public void deleteByCustomerCode(int customerCode) {
        bookingRepository.deleteByCustomerCode(customerCode);
    }

    public boolean doesExistForCustomerCode(int customerCode) {
        return bookingRepository.doesExistForCustomerCode(customerCode);
    }

    public Booking save(Booking entity) {
        return bookingRepository.save(entity);
    }
}
