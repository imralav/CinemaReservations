package pl.com.imralav.vxml.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CancelDialogController {

    @RequestMapping("/cancel")
    public String mainDialog() {
        return "cancel/mainCancel";
    }
}
