package pl.com.imralav.vxml.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.com.imralav.vxml.entities.Booking;
import pl.com.imralav.vxml.entities.dtos.BookingDto;
import pl.com.imralav.vxml.services.BookingService;
import pl.com.imralav.vxml.services.CustomerService;

@Controller
@RequestMapping("/cancel")
public class CancelDialogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CancelDialogController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookingService bookingService;

    @RequestMapping
    public String mainDialog() {
        return "cancel/mainCancel";
    }

    @RequestMapping("/passCustomerCode")
    public String passCustomerCode(@RequestParam(required=false) String customerCodeText, Model model) {
        LOGGER.info("Checking customer code: {}", customerCodeText);
        int customerCode = Integer.parseInt(customerCodeText);
        if(customerService.doesCustomerExistForCode(customerCode)) {
            Booking booking = bookingService.findByCustomerCode(customerCode);
            BookingDto bookingDto = bookingService.toDto(booking);
        }
        return "redirect:/cancel";
    }
}
