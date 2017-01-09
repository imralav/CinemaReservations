package pl.com.imralav.vxml.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.com.imralav.vxml.services.cancel.CancelService;

@Controller
@RequestMapping("/cancel")
public class CancelDialogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CancelDialogController.class);

    @Autowired
    private CancelService cancelService;

    @RequestMapping
    public String mainDialog() {
        return "cancel/mainCancel";
    }

    @RequestMapping("/passCustomerCode")
    public String passCustomerCode(@RequestParam(required=true, name="customerCode") String customerCodeText, Model model) {
        LOGGER.trace("Checking customer code: {}", customerCodeText);
        int customerCode = Integer.parseInt(customerCodeText);
        if(cancelService.doesCustomerAndBookingExistForCustomerCode(customerCode)) {
            updateModelForExistingCustomer(model, customerCode);
            return "cancel/bookingSummary";
        } else {
            model.addAttribute("customerCode", customerCodeText);
            return "cancel/customerUnknown";
        }
    }

    private void updateModelForExistingCustomer(Model model, int customerCode) {
        model.addAttribute("booking", cancelService.getBookingDtoForCustomerCode(customerCode));
        model.addAttribute("customerCode", Integer.toString(customerCode));
    }

    @RequestMapping("/attemptCancel")
    public String attemptCancel(@RequestParam(name="customerCode") String customerCodeText, @RequestParam Boolean shouldCancel, Model model) {
        if(shouldCancel) {
            LOGGER.info("Cancelling booking for customerCode: {}", customerCodeText);
            int customerCode = Integer.parseInt(customerCodeText);
            cancelService.cancelByCustomerCode(customerCode);
        }
        model.addAttribute("bookingCancelled", shouldCancel);
        return "cancel/cancelSummary";
    }
}
