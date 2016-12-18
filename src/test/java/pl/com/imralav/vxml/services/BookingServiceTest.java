package pl.com.imralav.vxml.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import pl.com.imralav.vxml.entities.Booking;
import pl.com.imralav.vxml.entities.Customer;
import pl.com.imralav.vxml.entities.Genre;
import pl.com.imralav.vxml.entities.Movie;
import pl.com.imralav.vxml.entities.Seat;
import pl.com.imralav.vxml.entities.Showing;
import pl.com.imralav.vxml.entities.dtos.BookingDto;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingService instance;
    private String movieTitle;
    private LocalDateTime showingDateTime;

    @Before
    public void setup() {
        movieTitle = "title";
        showingDateTime = LocalDateTime.of(2000, 1, 10, 10, 10);
    }

    @Test
    @Ignore
    public void shouldConvertToDto() {
        // given
        Booking booking = new Booking();
        booking.setId(1);
        booking.setCustomer(prepareDummyCustomer());
        booking.setSeat(prepareSeat());
        booking.setShowing(prepareShowing());
        // when
        BookingDto result = instance.toDto(booking);
        // then
        assertThat(result.getMovieTitle()).isEqualTo(movieTitle);
        assertThat(result.getReadableDate()).isEqualToIgnoringCase("10 styczeń 2000");
        assertThat(result.getReadableTime()).isEqualTo("10:10");
    }

    private Customer prepareDummyCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setCode(1234);
        return customer;
    }

    private Seat prepareSeat() {
        Seat seat = new Seat();
        seat.setId(1);
        seat.setRowNumber(1);
        seat.setSeatNumber(1);
        return seat;
    }

    private Showing prepareShowing() {
        Showing showing = new Showing();
        showing.setId(1);
        showing.setShowingDatetime(showingDateTime);
        showing.setMovie(prepareMovie());
        return showing;
    }

    private Movie prepareMovie() {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setTitle(movieTitle);
        movie.setGenre(prepareGenre());
        return movie;
    }

    private Genre prepareGenre() {
        Genre genre = new Genre();
        genre.setId(1);
        genre.setName("horror");
        return genre;
    }
}
