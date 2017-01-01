package pl.com.imralav.vxml.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import org.assertj.core.internal.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;

import pl.com.imralav.vxml.entities.Booking;
import pl.com.imralav.vxml.entities.Seat;
import pl.com.imralav.vxml.entities.Showing;
import pl.com.imralav.vxml.entities.dtos.BookingDto;
import pl.com.imralav.vxml.services.BookingService;
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
    private BookingProvider bookingProvider;

    @InjectMocks
    private ReservationDialogController instance;

    @Mock
    private Booking bookingMock;

    @Mock
    private Showing showingMock;

    @Mock
    private BookingDto bookingDtoMock;

    private Integer showingId;

    private Integer selectedSeats;

    @Before
    public void setup() {
        showingId = 1;
        selectedSeats = 3;
        given(showingServiceMock.findOne(showingId)).willReturn(showingMock);
        given(showingServiceMock.findEmptySeatsForShowingId(showingId)).willReturn(Collections.emptyList());
        given(bookingProvider.provideEmptyBooking()).willReturn(bookingMock);
        given(bookingServiceMock.toDto(any(Booking.class))).willReturn(bookingDtoMock);
    }

    @Test
    public void shouldInjectBookingDtoToModelWhenShowingReservationSummary() {
        //when
        instance.showReservationSummary(showingId, selectedSeats, modelSpy);
        BookingDto bookingSummary = (BookingDto)modelSpy.asMap().get("bookingSummary");
        //then
        assertThat(bookingSummary).isNotNull();
    }

    @Test
    public void shouldBuildBookingWithShowingAndSeatsListWhenShowingReservationSummary() {
        //given
        Integer showingId = 1;
        //when
        instance.showReservationSummary(showingId, selectedSeats, modelSpy);
        //then
        verify(bookingMock).setShowing(showingMock);
        verify(bookingMock).setSeats(anyListOf(Seat.class));
    }
}
