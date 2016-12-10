package pl.com.imralav.vxml.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainDialogController {

    @RequestMapping("/html")
    public String htmlDialog(Model model) {
        return "dialog.html";
    }

    @RequestMapping("/vxml")
    public String vxmlDialog(Model model) {
        return "dialog.xml";
    }
}
