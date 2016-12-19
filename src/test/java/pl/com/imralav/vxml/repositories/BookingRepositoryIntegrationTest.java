package pl.com.imralav.vxml.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.com.imralav.vxml.entities.Booking;
import pl.com.imralav.vxml.entities.Customer;
import pl.com.imralav.vxml.entities.Seat;
import pl.com.imralav.vxml.entities.Showing;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookingRepositoryIntegrationTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShowingRepository showingRepository;

    @Autowired
    private SeatRepository seatRepository;

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
    public void shouldFindByCustomerCode() {
        //given
        //when
        Booking result = bookingRepository.findByCustomerCode(1234);
        //then
        assertThat(result.getCustomer().getId()).isEqualTo(1);
    }

    @Test
    public void shouldDeleteByCustomerCode() {
        //given
        Customer customer = customerRepository.findOne(1);
        Showing showing = showingRepository.findOne(1);
        Seat seat = seatRepository.findOne(1);
        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setShowing(showing);
        booking.addSeat(seat);
        booking = bookingRepository.save(booking);
        assertThat(bookingRepository.exists(booking.getId())).isTrue();
        //when
        bookingRepository.deleteByCustomerCode(1234);
        //then
        assertThat(customerRepository.doesExistForCode(1234));
        assertThat(seatRepository.exists(seat.getId()));
        assertThat(showingRepository.exists(showing.getId()));
        assertThat(bookingRepository.exists(booking.getId())).isFalse();
    }
}
