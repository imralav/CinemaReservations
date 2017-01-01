package pl.com.imralav.vxml.services.providers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import pl.com.imralav.vxml.entities.Booking;

public class BookingProviderTest {

    private BookingProvider instance = new BookingProvider();

    @Test
    public void shouldProvideEmptyBooking() {
        //given
        Booking expected = new Booking();
        //when
        Booking result = instance.provideEmptyBooking();
        //then
        assertThat(result).isEqualTo(expected);
    }
}
