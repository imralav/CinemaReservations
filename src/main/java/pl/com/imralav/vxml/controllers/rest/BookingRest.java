package pl.com.imralav.vxml.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.com.imralav.vxml.entities.Booking;
import pl.com.imralav.vxml.repositories.BookingRepository;

@RestController
@RequestMapping("/bookings")
public class BookingRest {

    @Autowired
    private BookingRepository bookingRepository;

    @RequestMapping
    public ResponseEntity<Iterable<Booking>> getBookings() {
        return ResponseEntity.ok(bookingRepository.findAll());
    }

}
