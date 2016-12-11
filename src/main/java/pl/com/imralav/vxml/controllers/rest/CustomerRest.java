package pl.com.imralav.vxml.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.com.imralav.vxml.entities.Customer;
import pl.com.imralav.vxml.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerRest {

    @Autowired
    private CustomerService customerService;

    @RequestMapping
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @RequestMapping("/generate")
    public ResponseEntity<Customer> generateNewCustomer() {
        return ResponseEntity.ok(customerService.generateNewCustomer());
    }
}
