package pl.com.imralav.vxml.services;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.com.imralav.vxml.entities.Customer;
import pl.com.imralav.vxml.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer generateNewCustomer() {
        Customer generatedCustomer = new Customer();
        generatedCustomer.setCode(generateUniqueCode());
        generatedCustomer = customerRepository.save(generatedCustomer);
        return generatedCustomer;
    }

    private Integer generateUniqueCode() {
        boolean doesExistForCode = true;
        int randomCode = 0;
        while(doesExistForCode) {
            randomCode = ThreadLocalRandom.current().nextInt(1000, 10000);
            doesExistForCode = customerRepository.doesExistForCode(randomCode);
        }
        return randomCode;
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }
}
