package pl.com.imralav.vxml.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainDialogController {

    @RequestMapping(value="/main-dialog")
    public String vxmlDialog(Model model) {
        return "main.xml";
    }

    @RequestMapping(value="/farewell")
    public String farewell() {
        return "farewell.xml";
    }
}
