package pl.com.imralav.vxml.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.com.imralav.vxml.entities.Booking;
import pl.com.imralav.vxml.entities.Customer;
import pl.com.imralav.vxml.entities.Seat;
import pl.com.imralav.vxml.entities.Showing;
import pl.com.imralav.vxml.entities.dtos.BookingDto;
import pl.com.imralav.vxml.entities.dtos.ShowingDto;
import pl.com.imralav.vxml.services.BookingService;
import pl.com.imralav.vxml.services.CustomerService;
import pl.com.imralav.vxml.services.MovieService;
import pl.com.imralav.vxml.services.SeatService;
import pl.com.imralav.vxml.services.ShowingService;
import pl.com.imralav.vxml.services.datetime.DateTimeService;
import pl.com.imralav.vxml.services.providers.BookingProvider;

@Controller
@RequestMapping("/reservation")
public class ReservationDialogController {
    private static final String REGULAR_DATE_PATTERN = "d.M.uuuu";

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationDialogController.class);

    @Autowired
    private DateTimeService dateTimeService;

    @Autowired
    private ShowingService showingService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookingProvider bookingProvider;

    @Autowired
    private MovieService movieService;

    @Autowired
    private SeatService seatService;

    @RequestMapping("/chooseOrSearch")
    public String chooseOrSearch() {
        return "reservation/chooseOrSearchPrompt";
    }

    @RequestMapping("/collectShowingDate")
    public String showingDatePrompt() {
        return "reservation/showingDatePrompt";
    }

    @RequestMapping("/showRepertoire")
    public String showRepertoire(@RequestParam(name="date") String dateText, @RequestParam(name="readableDate") String readableDateText, Model model) {
        LOGGER.info("Displaying repertoire prompt for date {}", dateText);
        LocalDate date = dateTimeService.reformatDate(dateText).from(REGULAR_DATE_PATTERN).toDate();
        List<Showing> showings = showingService.findForDate(date);
        String movieTitles = showings.stream().map(Showing::getMovieTitle).distinct().collect(Collectors.joining(", "));
        model.addAttribute("date", dateTimeService.toReadable(date));
        model.addAttribute("readableDate", readableDateText);
        model.addAttribute("showings", showings);
        model.addAttribute("movieTitles", movieTitles);
        if(showings.isEmpty()) {
            LOGGER.info("No repertoire for date {}", date);
            return "reservation/noRepertoire";
        }
        return "reservation/repertoirePrompt";
    }

    @RequestMapping("/collectShowingTime")
    public String collectShowingTime(@RequestParam(name="date") String dateText, @RequestParam(name="readableDate") String readableDateText, @RequestParam Integer movieId, Model model) {
        LOGGER.info("Collecting showing time for date {} and movie id {}", dateText, movieId);
        LocalDate date = dateTimeService.reformatDate(dateText).fromReadable().toDate();
        LOGGER.info("Retrieving showings for date {} and movie id {}", date, movieId);
        List<Showing> showings = showingService.findForDateAndMovieId(date, movieId);
        LOGGER.info("Converting showings to dtos");
        List<ShowingDto> showingDtos = showingService.toDto(showings);
        model.addAttribute("date", dateText);
        model.addAttribute("readableDate", readableDateText);
        model.addAttribute("showings", showingDtos);
        model.addAttribute("timeToShowingIdMap", showingService.getTimeToShowingIdMap(showingDtos));
        model.addAttribute("movieTitle", showingDtos.get(0).getMovieTitle());
        return "reservation/showingTimePrompt";
    }

    @RequestMapping("/seatsPrompt")
    public String seatsPrompt(@RequestParam Integer showingId, @RequestParam(name="readableDate") String readableDateText, Model model) {
        model.addAttribute("showingId", showingId);
        int emptySeatsAmount = showingService.findEmptySeatsAmountForShowingId(showingId);
        if(emptySeatsAmount > 0) {
            model.addAttribute("maxSeats", emptySeatsAmount);
            model.addAttribute("readableDate", readableDateText);
            return "reservation/seatsPrompt";
        } else {
            return "reservation/notEnoughSeats";
        }
    }

    @RequestMapping("/showSummary")
    public String showReservationSummary(@RequestParam Integer showingId, @RequestParam(name="choice") Integer selectedSeatsAmount, @RequestParam(name="readableDate") String readableDateText, Model model) {
        Showing showing = showingService.findOne(showingId);
        Booking booking = bookingProvider.provideEmptyBooking();
        booking.setShowing(showing);
        List<Seat> selectedSeats = prepareSelectedSeats(showingId, selectedSeatsAmount);
        booking.setSeats(selectedSeats);
        BookingDto dto = bookingService.toDto(booking);
        String unparsedSeatIds = seatService.extractIds(selectedSeats);
        model.addAttribute("bookingSummary", dto);
        model.addAttribute("seatIds", unparsedSeatIds);
        model.addAttribute("showingId", showingId);
        model.addAttribute("readableDate", readableDateText);
        return "reservation/summary";
    }

    private List<Seat> prepareSelectedSeats(Integer showingId, Integer selectedSeats) {
        List<Seat> emptySeats = showingService.findEmptySeatsForShowingId(showingId);
        emptySeats = emptySeats.stream().limit(selectedSeats).collect(Collectors.toList());
        return emptySeats;
    }

    @RequestMapping(path = "/finalize", method=RequestMethod.POST)
    @Transactional
    public String finalizeReservation(@RequestParam Integer showingId, @RequestParam(name="seatIds") String unparsedSeatIds, Model model) {
        LOGGER.info("Finalizing reservation of showing id {} with seat ids {}", showingId, unparsedSeatIds);
        Customer customer = customerService.generateNewCustomer();
        model.addAttribute("customerCode", Integer.toString(customer.getCode()));
        List<Seat> seats = seatService.findAll(unparsedSeatIds);
        Showing showing = showingService.findOne(showingId);
        Booking booking = bookingProvider.provideFor(seats, customer, showing);
        LOGGER.info("Attempting to save the following booking: {}", booking);
        bookingService.save(booking);
        return "reservation/customerCodeSummary";
    }

    @RequestMapping("/mixedInitiativePrompt")
    public String mixedInitiativePrompt(Model model) {
        model.addAttribute("titles", movieService.findAllTitles());
        return "reservation/mixedInitiative";
    }

    @RequestMapping("/checkMixedInitiativeData")
    public String checkMixedInitiativeData(@RequestParam(name="date") String dateText, @RequestParam(name="readableDate") String readableDateText, @RequestParam(name="title") String movieTitle, @RequestParam(name="time") String timeText, Model model) {
        LOGGER.info("Checking if showing exists for date {}, time {} and title {}", dateText, timeText, movieTitle);
        LocalDateTime showingDatetime = parseDateTimeTexts(dateText, timeText);
        if(showingService.existsForDatetimeAndMovieTitle(showingDatetime, movieTitle)) {
            Showing showing = showingService.findByDatetimeAndMovieTitle(showingDatetime, movieTitle);
            return seatsPrompt(showing.getId(), readableDateText, model);
        } else {
            return "reservation/showingDoesntExist";
        }
    }

    private LocalDateTime parseDateTimeTexts(String dateText, String timeText) {
        LocalDate date = dateTimeService.reformatDate(dateText).from(REGULAR_DATE_PATTERN).toDate();
        LocalTime time = dateTimeService.toTime(timeText);
        LocalDateTime showingDatetime = LocalDateTime.of(date, time);
        return showingDatetime;
    }
}
