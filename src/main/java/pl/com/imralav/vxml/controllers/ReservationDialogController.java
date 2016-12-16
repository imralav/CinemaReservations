package pl.com.imralav.vxml.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReservationDialogController {

    @RequestMapping("/reservation")
    public String mainDialog() {
        return "reservation/mainReservation";
    }
}
