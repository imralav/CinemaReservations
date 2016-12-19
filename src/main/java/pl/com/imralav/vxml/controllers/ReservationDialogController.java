package pl.com.imralav.vxml.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reservation")
public class ReservationDialogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationDialogController.class);

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
}
