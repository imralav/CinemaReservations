package pl.com.imralav.vxml.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.com.imralav.vxml.entities.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>{

}
