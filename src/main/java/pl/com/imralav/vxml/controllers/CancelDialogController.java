package pl.com.imralav.vxml.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cancel")
@Controller
public class CancelDialogController {

    @RequestMapping
    public String mainDialog() {
        return "cancel/main.xml";
    }
}
