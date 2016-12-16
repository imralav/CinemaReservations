package pl.com.imralav.vxml.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cancel")
public class CancelDialogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CancelDialogController.class);

    @RequestMapping
    public String mainDialog() {
        return "cancel/mainCancel";
    }

    @RequestMapping("/passCustomerCode")
    public String passCustomerCode(HttpServletRequest request) {
        LOGGER.info("Request attributes passed: {}", request.getAttributeNames());
        LOGGER.info("Request params passed: {}", request.getParameterMap());
        return "cancel/customerUnknown";
    }
}
