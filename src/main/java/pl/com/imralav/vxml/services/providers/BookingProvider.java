package pl.com.imralav.vxml.services.providers;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.com.imralav.vxml.entities.Booking;
import pl.com.imralav.vxml.entities.Customer;
import pl.com.imralav.vxml.entities.Seat;
import pl.com.imralav.vxml.entities.Showing;

@Service
public class BookingProvider {

    public Booking provideEmptyBooking() {
        return new Booking();
    }

    public Booking provideFor(List<Seat> seats, Customer customer, Showing showing) {
        Booking entity = provideEmptyBooking();
        entity.setCustomer(customer);
        entity.setSeats(seats);
        entity.setShowing(showing);
        return entity;
    }

}
