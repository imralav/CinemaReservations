package pl.com.imralav.vxml.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.com.imralav.vxml.entities.Showing;

@Repository
public interface ShowingRepository extends PagingAndSortingRepository<Showing, Integer>{

}
