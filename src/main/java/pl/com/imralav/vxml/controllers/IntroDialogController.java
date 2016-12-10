package pl.com.imralav.vxml.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IntroDialogController {

    @RequestMapping
    public String introDialog(Model model) {
        return "dialog";
    }
}
