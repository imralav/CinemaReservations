package pl.com.imralav.vxml.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainDialogController {

    @RequestMapping(value="/main-dialog")
    public String vxmlDialog(Model model) {
        return "main-dialog.xml";
    }

    @RequestMapping(value="/joke")
    public String getJoke(Model model) {
        model.addAttribute("joke","Dobry dowcip");
        return "joke.xml";
    }
}
