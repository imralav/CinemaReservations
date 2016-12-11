package pl.com.imralav.vxml.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.com.imralav.vxml.entities.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>{

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM Customer c WHERE c.code = ?1")
    boolean doesExistForCode(int randomCode);

}
