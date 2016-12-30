package pl.com.imralav.vxml.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import pl.com.imralav.vxml.entities.Movie;
import pl.com.imralav.vxml.entities.Showing;
import pl.com.imralav.vxml.entities.dtos.ShowingDto;
import pl.com.imralav.vxml.repositories.ShowingRepository;

@RunWith(MockitoJUnitRunner.class)
public class ShowingServiceTest {

    @Mock
    private ShowingRepository showingRepositoryMock;

    @Spy
    private DateTimeService dateTimeService;

    @Mock
    private Showing showingMock1;

    @Mock
    private Showing showingMock2;

    @Mock
    private Movie movieMock;

    @InjectMocks
    private ShowingService instance;

    @Test
    public void shouldFindShowingsForDate() {
        // given
        LocalDate date = LocalDate.of(2016, 1, 10);
        LocalDateTime from = date.atStartOfDay();
        LocalDateTime to = from.plusDays(1);
        given(showingRepositoryMock.findByShowingDatetimeBetween(from, to)).willReturn(Arrays.asList(showingMock1, showingMock2));
        // when
        List<Showing> result = instance.findForDate(date);
        // then
        assertThat(result).hasSize(2);
        assertThat(result).contains(showingMock1, showingMock2);
    }

    @Test
    public void shouldFindShowingsForDateAndMovieId() {
        // given
        LocalDate date = LocalDate.of(2016, 1, 10);
        LocalDateTime from = date.atStartOfDay();
        LocalDateTime to = from.plusDays(1);
        Integer movieId = 1;
        given(showingRepositoryMock.findByShowingDatetimeBetweenAndMovieId(from, to, movieId)).willReturn(Arrays.asList(showingMock1, showingMock2));
        // when
        List<Showing> result = instance.findForDateAndMovieId(date, movieId);
        // then
        assertThat(result).hasSize(2);
        assertThat(result).contains(showingMock1, showingMock2);
    }

    @Test
    public void shouldConvertToSingleDto() {
        //given
        int showingId = 1;
        String movieTitle = "title";
        given(movieMock.getTitle()).willReturn(movieTitle);
        Showing showing = new Showing();
        showing.setId(showingId);
        showing.setMovie(movieMock);
        showing.setShowingDatetime(LocalDateTime.of(2016, 1, 10, 21, 00));
        //when
        ShowingDto result = instance.toDto(showing);
        //then
        assertThat(result.getId()).isEqualTo(showingId);
        assertThat(result.getMovieTitle()).isEqualTo(movieTitle);
        assertThat(result.getReadableDate()).isEqualToIgnoringCase("10 stycznia 2016");
        assertThat(result.getReadableTime()).isEqualTo("21:00");
    }

    @Test
    public void shouldConvertToListOfDtos() {
        //given
        int showingId = 1;
        String movieTitle = "title";
        given(movieMock.getTitle()).willReturn(movieTitle);
        Showing showing = new Showing();
        showing.setId(showingId);
        showing.setMovie(movieMock);
        showing.setShowingDatetime(LocalDateTime.of(2016, 1, 10, 21, 00));
        List<Showing> showings = Arrays.asList(showing);
        //when
        List<ShowingDto> results = instance.toDto(showings);
        ShowingDto result = results.get(0);
        //then
        assertThat(result.getId()).isEqualTo(showingId);
        assertThat(result.getMovieTitle()).isEqualTo(movieTitle);
        assertThat(result.getReadableDate()).isEqualToIgnoringCase("10 stycznia 2016");
        assertThat(result.getReadableTime()).isEqualTo("21:00");
    }
}
