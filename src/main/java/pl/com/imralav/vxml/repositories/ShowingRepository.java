package pl.com.imralav.vxml.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.com.imralav.vxml.entities.Showing;

@Repository
public interface ShowingRepository extends PagingAndSortingRepository<Showing, Integer>{

    List<Showing> findByShowingDatetimeBetween(LocalDateTime from, LocalDateTime to);

}
