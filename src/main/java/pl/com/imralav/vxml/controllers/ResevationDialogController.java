package pl.com.imralav.vxml.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/reservation")
@Controller
public class ResevationDialogController {

    @RequestMapping
    public String mainDialog() {
        return "reservation/main.xml";
    }
}
