package pl.com.imralav.vxml.services.datetime;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DateTimeServiceTest {
    @InjectMocks
    private DateTimeService instance;

    @Test
    public void shouldProperlyReformatDateToReadable() {
        //given
        String initialDate = "10.1.2016";
        String initialFormat = "d.M.uuuu";
        //when
        String result = instance.reformatDate(initialDate).from(initialFormat).toReadable();
        //then
        assertThat(result).isEqualTo("10 stycznia 2016");
    }

    @Test
    public void shouldProperlyParseTime() {
        //given
        String initialTime = "12:00";
        //when
        LocalTime result = instance.toTime(initialTime);
        //then
        assertThat(result).isEqualTo(LocalTime.of(12, 0));
    }

    @Test
    public void shouldConvertToDatetime() {
        //given
        //when
        LocalDateTime result = instance.toDatetime("10 stycznia 2016", "12:00");
        //then
        assertThat(result).isEqualTo(LocalDateTime.of(2016, 1, 10, 12, 0));
    }
}
