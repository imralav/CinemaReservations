package pl.com.imralav.vxml.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cancel")
public class CancelDialogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CancelDialogController.class);

    @RequestMapping
    public String mainDialog() {
        return "cancel/mainCancel";
    }

    @RequestMapping("/passCustomerCode")
    public String passCustomerCode(@RequestParam(required=false) String customerCode) {
        LOGGER.info("Checking customer code: {}", customerCode);
        return "redirect:/cancel";
    }
}
