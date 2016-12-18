package pl.com.imralav.vxml.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.com.imralav.vxml.entities.Booking;

@Repository
public interface BookingRepository extends PagingAndSortingRepository<Booking, Integer>{

    Booking findByCustomerCode(int customerCode);

}
