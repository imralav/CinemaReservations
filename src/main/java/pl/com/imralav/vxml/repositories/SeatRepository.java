package pl.com.imralav.vxml.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.com.imralav.vxml.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer>{

}
