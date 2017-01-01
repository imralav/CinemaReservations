package pl.com.imralav.vxml.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.com.imralav.vxml.entities.Booking;
import pl.com.imralav.vxml.entities.Seat;
import pl.com.imralav.vxml.entities.Showing;
import pl.com.imralav.vxml.entities.dtos.BookingDto;
import pl.com.imralav.vxml.entities.dtos.ShowingDto;
import pl.com.imralav.vxml.services.BookingService;
import pl.com.imralav.vxml.services.DateTimeService;
import pl.com.imralav.vxml.services.ShowingService;
import pl.com.imralav.vxml.services.providers.BookingProvider;

@Controller
@RequestMapping("/reservation")
public class ReservationDialogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationDialogController.class);

    @Autowired
    private DateTimeService dateTimeService;

    @Autowired
    private ShowingService showingService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingProvider bookingProvider;

    @RequestMapping("/collectShowingDate")
    public String showingDatePrompt() {
        return "reservation/showingDatePrompt";
    }

    @RequestMapping("/choicePrompt")
    public String movieChoicePrompt(@RequestParam String date, Model model) {
        LOGGER.info("Displaying choice prompt for date {}", date);
        model.addAttribute("date", date);
        return "reservation/movieChoicePrompt";
    }

    @RequestMapping("/showRepertoire")
    public String showRepertoire(@RequestParam(name="date") String dateText, Model model) {
        LOGGER.info("Displaying repertoire prompt for date {}", dateText);
        LocalDate date = dateTimeService.reformat(dateText).from("d.M.uuuu").toDate();
        List<Showing> showings = showingService.findForDate(date);
        String movieTitles = showings.stream().map(Showing::getMovieTitle).distinct().collect(Collectors.joining(", "));
        model.addAttribute("date", dateTimeService.toReadable(date));
        model.addAttribute("showings", showings);
        model.addAttribute("movieTitles", movieTitles);
        if(showings.isEmpty()) {
            LOGGER.info("No repertoire for date {}", date);
            return "reservation/noRepertoire";
        }
        return "reservation/repertoirePrompt";
    }

    @RequestMapping("/collectShowingTime")
    public String collectShowingTime(@RequestParam(name="date") String dateText, @RequestParam(name="choice") Integer movieId, Model model) {
        LOGGER.info("Collecting showing time for date {} and movie id {}", dateText, movieId);
        LocalDate date = dateTimeService.reformat(dateText).fromReadable().toDate();
        LOGGER.info("Retrieving showings for date {} and movie id {}", date, movieId);
        List<Showing> showings = showingService.findForDateAndMovieId(date, movieId);
        LOGGER.info("Converting showings to dtos");
        List<ShowingDto> showingDtos = showingService.toDto(showings);
        model.addAttribute("date", dateText);
        model.addAttribute("showings", showingDtos);
        model.addAttribute("movieTitle", showingDtos.get(0).getMovieTitle());
        return "reservation/showingTimePrompt";
    }

    @RequestMapping("/seatsPrompt")
    public String seatsAmount(@RequestParam(name="choice") Integer showingId, Model model) {
        model.addAttribute("showingId", showingId);
        //TODO: check amount of empty seats, redirect to error dialog if there are none, otherwise set maxSeats value to available amount
        return "reservation/seatsPrompt";
    }

    @RequestMapping("/showSummary")
    public String showReservationSummary(@RequestParam Integer showingId, @RequestParam(name="choice") Integer selectedSeats, Model model) {
        Showing showing = showingService.findOne(showingId);
        Booking booking = bookingProvider.provideEmptyBooking();
        booking.setShowing(showing);
        List<Seat> availableSeats = prepareAvailableSeats(showingId, selectedSeats);
        booking.setSeats(availableSeats);
        BookingDto dto = bookingService.toDto(booking);
        model.addAttribute("bookingSummary", dto);
        return "";
    }

    private List<Seat> prepareAvailableSeats(Integer showingId, Integer selectedSeats) {
        List<Seat> emptySeats = showingService.findEmptySeatsForShowingId(showingId);
        emptySeats = emptySeats.stream().limit(selectedSeats).collect(Collectors.toList());
        return emptySeats;
    }
}
