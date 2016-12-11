package pl.com.imralav.vxml.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.com.imralav.vxml.entities.Customer;
import pl.com.imralav.vxml.repositories.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerRest {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }
}
