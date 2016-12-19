package pl.com.imralav.vxml.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.com.imralav.vxml.entities.Booking;

@Repository
public interface BookingRepository extends PagingAndSortingRepository<Booking, Integer>{

    Booking findByCustomerCode(int customerCode);

    void deleteByCustomerCode(int customerCode);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN 'true' ELSE 'false' END FROM Booking b WHERE b.customer.code = ?1")
    boolean doesExistForCustomerCode(int customerCode);

}
