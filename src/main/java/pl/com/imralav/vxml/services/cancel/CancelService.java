package pl.com.imralav.vxml.services.cancel;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.com.imralav.vxml.entities.Booking;
import pl.com.imralav.vxml.entities.dtos.BookingDto;
import pl.com.imralav.vxml.services.BookingService;
import pl.com.imralav.vxml.services.CustomerService;

@Service
public class CancelService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookingService bookingService;

    @Transactional
    public void cancelByCustomerCode(int customerCode) {
        bookingService.deleteByCustomerCode(customerCode);
        customerService.deleteByCustomerCode(customerCode);
    }

    public boolean doesCustomerAndBookingExistForCustomerCode(int customerCode) {
        return customerService.doesCustomerExistForCode(customerCode) && bookingService.doesExistForCustomerCode(customerCode);
    }

    public BookingDto getBookingDtoForCustomerCode(int customerCode) {
        Booking booking = bookingService.findByCustomerCode(customerCode);
        BookingDto bookingDto = bookingService.toDto(booking);
        return bookingDto;
    }
}
