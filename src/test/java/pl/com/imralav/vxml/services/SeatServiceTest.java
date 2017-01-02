package pl.com.imralav.vxml.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.com.imralav.vxml.entities.Seat;
import pl.com.imralav.vxml.repositories.SeatRepository;

@RunWith(MockitoJUnitRunner.class)
public class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private SeatService instance;

    @Mock
    private Seat seat1Mock;

    @Test
    public void shouldExtractIds() {
        // given
        given(seat1Mock.getId()).willReturn(1, 2, 3);
        List<Seat> seats = Arrays.asList(seat1Mock, seat1Mock, seat1Mock);
        // when
        String results = instance.extractIds(seats);
        // then
        assertThat(results).isEqualTo("1,2,3");
    }

    @Test
    public void shouldParseIdsFromStringWhenFindAll() {
        //given
        List<Seat> seats = Arrays.asList(seat1Mock, seat1Mock, seat1Mock);
        given(seatRepository.findAll(Arrays.asList(1,2,3))).willReturn(seats);
        //when
        List<Seat> results = instance.findAll("1,2,3");
        //then
        assertThat(results).isEqualTo(seats);
    }
}
