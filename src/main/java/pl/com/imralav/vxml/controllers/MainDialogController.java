package pl.com.imralav.vxml.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainDialogController {

    @RequestMapping("/vxml")
    public String vxmlDialog(Model model) {
        return "dialog.xml";
    }

    @RequestMapping("/joke")
    public String getJoke(Model model) {
        model.addAttribute("joke","Dobry dowcip");
        return "joke.xml";
    }
}
