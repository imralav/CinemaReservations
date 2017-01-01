package pl.com.imralav.vxml.services.providers;

import org.springframework.stereotype.Service;

import pl.com.imralav.vxml.entities.Booking;

@Service
public class BookingProvider {

    public Booking provideEmptyBooking() {
        return new Booking();
    }

}
