package pl.com.imralav.vxml.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import pl.com.imralav.vxml.entities.Seat;

@RunWith(MockitoJUnitRunner.class)
public class ReadableSeatsBuilderTest {

    @InjectMocks
    private ReadableSeatsBuilder instance;

    @Test
    public void shouldBuildCorrectReadableString() {
        //given
        //when
        String result = instance.buildFor(prepareSeats());
        //then
        assertThat(result).isEqualTo("1, 2 w rzędzie 1, 1, 2 w rzędzie 2");
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
