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
import org.mockito.runners.MockitoJUnitRunner;

import pl.com.imralav.vxml.entities.Showing;
import pl.com.imralav.vxml.repositories.ShowingRepository;

@RunWith(MockitoJUnitRunner.class)
public class ShowingServiceTest {

    @Mock
    private ShowingRepository showingRepositoryMock;

    @Mock
    private Showing showingMock1;

    @Mock
    private Showing showingMock2;

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
}
