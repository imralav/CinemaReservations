package pl.com.imralav.vxml.services;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.com.imralav.vxml.entities.Booking;
import pl.com.imralav.vxml.entities.dtos.BookingDto;
import pl.com.imralav.vxml.repositories.BookingRepository;

@Service
public class BookingService {

    private static final DateTimeFormatter READABLE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter READABLE_DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMMM uuuu",
                                                                                                 Locale.forLanguageTag("pl-pl"));
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ReadableSeatsBuilder readableSeatsBuilder;

    public Booking findByCustomerCode(int customerCode) {
        return bookingRepository.findByCustomerCode(customerCode);
    }

    public BookingDto toDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setMovieTitle(booking.getMovieTitle());
        dto.setReadableDate(booking.getShowingDate().format(READABLE_DATE_FORMATTER));
        dto.setReadableTime(booking.getShowingTime().format(READABLE_TIME_FORMATTER));
        dto.setReadableSeats(readableSeatsBuilder.buildFor(booking.getSeats()));
        return dto;
    }

    public void deleteByCustomerCode(int customerCode) {
        bookingRepository.deleteByCustomerCode(customerCode);
    }

    public boolean doesExistForCustomerCode(int customerCode) {
        return bookingRepository.doesExistForCustomerCode(customerCode);
    }
}
