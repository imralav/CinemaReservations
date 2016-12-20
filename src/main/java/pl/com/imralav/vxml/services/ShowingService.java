package pl.com.imralav.vxml.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.com.imralav.vxml.entities.Showing;
import pl.com.imralav.vxml.repositories.ShowingRepository;

@Service
public class ShowingService {

    @Autowired
    private ShowingRepository showingRepository;

    public List<Showing> findForDate(LocalDate date) {
        LocalDateTime from = date.atStartOfDay();
        LocalDateTime to = from.plusDays(1);
        return showingRepository.findByShowingDatetimeBetween(from, to);
    }

}
