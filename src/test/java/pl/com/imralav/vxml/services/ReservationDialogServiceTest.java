package pl.com.imralav.vxml.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

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
import pl.com.imralav.vxml.entities.dtos.MovieDto;

@RunWith(MockitoJUnitRunner.class)
public class ReservationDialogServiceTest {

    @Spy
    private MovieService movieService;

    @InjectMocks
    private ReservationDialogService instance;

    @Mock
    private Showing showingMock;

    @Mock
    private Movie movieMock;

    @Test
    public void shouldGetDistinctMovieDtosFromShowings() {
        //given
        given(showingMock.getMovie()).willReturn(movieMock);
        given(movieMock.getId()).willReturn(1);
        given(movieMock.getTitle()).willReturn("title1");
        //when
        List<MovieDto> movieDtos = instance.extractMovieDtosFromShowings(Arrays.asList(showingMock, showingMock));
        //then
        assertThat(movieDtos).hasSize(1);
        assertThat(movieDtos).extracting(MovieDto::getTitle).containsExactly("title1");
    }

    @Test
    public void shouldGetDistinctMovieDtosFromShowingsWithUniqueMovies() {
        //given
        given(showingMock.getMovie()).willReturn(movieMock);
        given(movieMock.getId()).willReturn(1,2);
        given(movieMock.getTitle()).willReturn("title1", "title2");
        //when
        List<MovieDto> movieDtos = instance.extractMovieDtosFromShowings(Arrays.asList(showingMock, showingMock));
        //then
        assertThat(movieDtos).hasSize(2);
        assertThat(movieDtos).extracting(MovieDto::getTitle).containsExactly("title1","title2");
    }
}
