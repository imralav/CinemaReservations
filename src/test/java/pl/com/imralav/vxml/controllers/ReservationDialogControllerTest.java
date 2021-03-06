package pl.com.imralav.vxml.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;

import pl.com.imralav.vxml.entities.Booking;
import pl.com.imralav.vxml.entities.Customer;
import pl.com.imralav.vxml.entities.Seat;
import pl.com.imralav.vxml.entities.Showing;
import pl.com.imralav.vxml.entities.dtos.BookingDto;
import pl.com.imralav.vxml.services.BookingService;
import pl.com.imralav.vxml.services.CustomerService;
import pl.com.imralav.vxml.services.MovieService;
import pl.com.imralav.vxml.services.ReservationDialogService;
import pl.com.imralav.vxml.services.SeatService;
import pl.com.imralav.vxml.services.ShowingService;
import pl.com.imralav.vxml.services.providers.BookingProvider;

@RunWith(MockitoJUnitRunner.class)
public class ReservationDialogControllerTest {

    @Spy
    private ExtendedModelMap modelSpy;

    @Mock
    private ShowingService showingServiceMock;

    @Mock
    private BookingService bookingServiceMock;

    @Mock
    private CustomerService customerServiceMock;

    @Mock
    private BookingProvider bookingProvider;

    @Mock
    private SeatService seatService;

    @Mock
    private MovieService movieService;

    @Mock
    private ReservationDialogService repertoireDialogService;

    @InjectMocks
    private ReservationDialogController instance;

    @Mock
    private Booking bookingMock;

    @Mock
    private Showing showingMock;

    @Mock
    private BookingDto bookingDtoMock;

    @Mock
    private Customer customerMock;

    private Integer showingId;

    private Integer selectedSeats;

    private Integer customerCode;

    private String readableDateText;

    @Before
    public void setup() {
        showingId = 1;
        selectedSeats = 3;
        readableDateText = "readableDate";
        given(showingServiceMock.findOne(showingId)).willReturn(showingMock);
        given(showingServiceMock.findEmptySeatsForShowingId(showingId)).willReturn(Collections.emptyList());
        given(bookingProvider.provideEmptyBooking()).willReturn(bookingMock);
        given(bookingServiceMock.toDto(any(Booking.class))).willReturn(bookingDtoMock);
        given(customerServiceMock.generateNewCustomer()).willReturn(customerMock);
    }

    @Test
    public void shouldInjectBookingDtoToModelWhenShowingReservationSummary() {
        instance.showReservationSummary(showingId, selectedSeats, readableDateText, modelSpy);
        BookingDto bookingSummary = (BookingDto)modelSpy.asMap().get("bookingSummary");
        //then
        assertThat(bookingSummary).isNotNull();
    }

    @Test
    public void shouldBuildBookingWithShowingAndSeatsListWhenShowingReservationSummary() {
        //given
        //when
        instance.showReservationSummary(showingId, selectedSeats, readableDateText, modelSpy);
        //then
        verify(bookingMock).setShowing(showingMock);
        verify(bookingMock).setSeats(anyListOf(Seat.class));
    }

    @Test
    public void shouldInjectSeatIdsToModelWhenShowingReservationSummar() {
        //given
        //when
        instance.showReservationSummary(showingId, selectedSeats, readableDateText, modelSpy);
        //then
        assertThat(modelSpy.containsAttribute("seatIds"));
    }

    @Test
    public void shouldGenerateAndInjectCustomerCodeWhenFinalizingReservation() {
        //given
        customerCode = 1234;
        given(customerMock.getCode()).willReturn(customerCode);
        //when
        instance.finalizeReservation(showingId, "1,2", modelSpy);
        String customerCode = (String)modelSpy.asMap().get("customerCode");
        //then
        assertThat(customerCode).isEqualTo(customerCode);
        verify(customerServiceMock).generateNewCustomer();
    }

    @Test
    public void shouldUpdateBookingWhenFinalizingReservation() {
        //given
        //when
        instance.finalizeReservation(showingId, "1,2", modelSpy);
        //then
        verify(bookingServiceMock).save(any(Booking.class));
    }
}
