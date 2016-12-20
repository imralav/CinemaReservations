package pl.com.imralav.vxml.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.com.imralav.vxml.entities.Showing;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShowingRepositoryIntegrationTest {

    @Autowired
    private ShowingRepository showingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void shouldGetExistingShowing() {
        //given
        //when
        Showing findOne = showingRepository.findOne(1);
        //then
        assertThat(findOne).isNotNull();
    }

    @Test
    public void shouldSave() {
        //given
        Showing showing = new Showing();
        showing.setMovie(movieRepository.findOne(1));
        showing.setShowingDatetime(LocalDateTime.now());
        //when
        Showing save = showingRepository.save(showing);
        //then
        assertThat(save.getId()).isNotNull();
    }

    @Test
    public void shouldFindShowingBetweenDates() {
        //given
        LocalDateTime from = LocalDate.of(2016, 12, 25).atStartOfDay();
        LocalDateTime to = from.plusDays(1);
        //when
        List<Showing> findByShowingDatetimeBetween = showingRepository.findByShowingDatetimeBetween(from, to);
        //then
        assertThat(findByShowingDatetimeBetween).hasSize(2);
        assertThat(findByShowingDatetimeBetween).extracting(Showing::getMovieTitle).contains("Koszmar z ulicy Wiązów", "Nie zadzieraj z fryzjerem");
    }
}
