package pl.com.imralav.vxml.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewerController {

    @RequestMapping("/viewer")
    public String htmlDialog(Model model) {
        return "viewer.html";
    }

}
