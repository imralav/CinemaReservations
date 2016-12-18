package pl.com.imralav.vxml.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.com.imralav.vxml.entities.Booking;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookingRepositoryIntegrationTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    public void shouldGetExistingBooking() {
        //given
        //when
        Booking findOne = bookingRepository.findOne(1);
        //then
        assertThat(findOne).isNotNull();
        assertThat(findOne.getCustomer().getId()).isEqualTo(1);
        assertThat(findOne.getShowing().getId()).isEqualTo(1);
    }

    @Test
    public void shouldHaveManySeats() {
        //given
        //when
        Booking findOne = bookingRepository.findOne(1);
        //then
        assertThat(findOne.getSeats()).hasSize(2);
        assertThat(findOne.getSeats()).extracting(seat -> {
            return seat.getSeatNumber();
        }).containsExactly(2,3);
        assertThat(findOne.getSeats()).extracting(seat -> {
            return seat.getRowNumber();
        }).containsExactly(2,2);
    }

    @Test
    public void shouldFindByCustomerId() {
        //given
        //when
        Booking result = bookingRepository.findByCustomerCode(1234);
        //then
        assertThat(result.getCustomer().getId()).isEqualTo(1);
    }
}
