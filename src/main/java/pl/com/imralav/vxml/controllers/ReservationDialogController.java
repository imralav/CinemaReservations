package pl.com.imralav.vxml.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationDialogController {

    @RequestMapping("/collectShowingDate")
    public String showingDatePrompt() {
        return "reservation/showingDatePrompt";
    }
}
