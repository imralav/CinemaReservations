package pl.com.imralav.vxml.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.com.imralav.vxml.entities.Seat;
import pl.com.imralav.vxml.entities.Showing;

@Repository
public interface ShowingRepository extends PagingAndSortingRepository<Showing, Integer>{

    List<Showing> findByShowingDatetimeBetween(LocalDateTime from, LocalDateTime to);

    List<Showing> findByShowingDatetimeBetweenAndMovieId(LocalDateTime from, LocalDateTime to, int movieId);

    @Query("SELECT seat FROM Seat seat WHERE seat.id NOT IN ("
            + "SELECT bs.id FROM Booking b, IN(b.seats) bs WHERE b.showing.id = ?1"
            + ") ORDER BY seat.rowNumber ASC, seat.seatNumber ASC")
    List<Seat> findEmptySeatsForShowingId(Integer showingId);

    @Query("SELECT count(seat) FROM Seat seat WHERE seat.id NOT IN ("
            + "SELECT bs.id FROM Booking b, IN(b.seats) bs WHERE b.showing.id = ?1"
            + ")")
    int findEmptySeatsAmountForShowingId(Integer showingId);

    Showing findByShowingDatetimeAndMovie_TitleIgnoreCase(LocalDateTime showingDatetime, String movieTitle);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN 'true' ELSE 'false' END FROM Showing s WHERE s.showingDatetime = ?1 and UPPER(s.movie.title) = UPPER(?2)")
    boolean doesExistForShowingDateTimeAndMovieTitle(LocalDateTime showingDatetime, String movieTitle);
}
