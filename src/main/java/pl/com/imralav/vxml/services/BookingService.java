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

    public Booking findByCustomerCode(int customerCode) {
        return bookingRepository.findByCustomerCode(customerCode);
    }

    public BookingDto toDto(Booking booking) {
        return null;
    }
}
