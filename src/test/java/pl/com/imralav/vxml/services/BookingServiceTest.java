package pl.com.imralav.vxml.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
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

    @Spy
    private ReadableSeatsBuilder readableSeatsBuilder;

    @Spy
    private DateTimeService dateTimeService;

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
    public void shouldConvertToDto() {
        // given
        Booking booking = new Booking();
        booking.setId(1);
        booking.setCustomer(prepareDummyCustomer());
        booking.setShowing(prepareShowing());
        booking.setSeats(prepareSeats());
        // when
        BookingDto result = instance.toDto(booking);
        // then
        assertThat(result.getMovieTitle()).isEqualTo(movieTitle);
        assertThat(result.getReadableDate()).isEqualToIgnoringCase("10 stycznia 2000");
        assertThat(result.getReadableTime()).isEqualTo("10:10");
        assertThat(result.getReadableSeats()).isEqualTo("1, 2 w rzędzie 1, 1, 2 w rzędzie 2");
    }

    private Customer prepareDummyCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setCode(1234);
        return customer;
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

    private List<Seat> prepareSeats() {
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat(1, 1));
        seats.add(new Seat(2, 1));
        seats.add(new Seat(1, 2));
        seats.add(new Seat(2, 2));
        return seats;
    }
}
