package pl.com.imralav.vxml.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainDialogController {

    @RequestMapping("/main")
    public String vxmlDialog(Model model) {
        return "mainDialog";
    }

    @RequestMapping("/farewell")
    public String farewell() {
        return "farewellDialog";
    }
}
