package pl.com.imralav.vxml.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.com.imralav.vxml.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
