package pl.com.imralav.vxml.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.com.imralav.vxml.entities.Customer;
import pl.com.imralav.vxml.repositories.CustomerRepository;

@Controller
public class ViewerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/viewer")
    public String htmlDialog(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "viewer.html";
    }


    @RequestMapping("/customers")
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }
}
