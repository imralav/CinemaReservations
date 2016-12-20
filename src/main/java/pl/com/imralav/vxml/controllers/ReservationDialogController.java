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

import pl.com.imralav.vxml.entities.Showing;
import pl.com.imralav.vxml.services.DateTimeService;
import pl.com.imralav.vxml.services.ShowingService;

@Controller
@RequestMapping("/reservation")
public class ReservationDialogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationDialogController.class);

    @Autowired
    private DateTimeService dateTimeService;

    @Autowired
    private ShowingService showingService;

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
    public String collectShowingTime(@RequestParam(name="date") String dateText, @RequestParam(name="choice") Integer showingId, Model model) {
        return "";
    }
}
