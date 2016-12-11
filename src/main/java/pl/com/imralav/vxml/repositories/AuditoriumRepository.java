package pl.com.imralav.vxml.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.com.imralav.vxml.entities.Auditorium;

@Repository
public interface AuditoriumRepository extends CrudRepository<Auditorium, Integer>{

}
