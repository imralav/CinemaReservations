package pl.com.imralav.vxml.services;

import static org.assertj.core.api.Assertions.assertThat;

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
        String result = instance.reformat(initialDate).from(initialFormat).toReadable();
        //then
        assertThat(result).isEqualTo("dziesiąty stycznia 2016");
    }
}
