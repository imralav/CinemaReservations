package pl.com.imralav.vxml.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.com.imralav.vxml.entities.Movie;
import pl.com.imralav.vxml.entities.dtos.MovieDto;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @InjectMocks
    private MovieService instance;

    @Mock
    private Movie movieMock;

    @Test
    public void shouldConvertToDto() {
        //given
        given(movieMock.getId()).willReturn(1);
        given(movieMock.getTitle()).willReturn("title1");
        //when
        MovieDto result = instance.toDto(movieMock);
        //then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
    }
}
