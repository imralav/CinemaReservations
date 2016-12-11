package pl.com.imralav.vxml.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.com.imralav.vxml.entities.Seat;

@Repository
public interface SeatRepository extends PagingAndSortingRepository<Seat, Integer>{

}
