package pl.com.imralav.vxml.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.com.imralav.vxml.entities.Showing;
import pl.com.imralav.vxml.entities.dtos.MovieDto;

@Service
public class ReservationDialogService {

    @Autowired
    private MovieService movieService;

    public List<MovieDto> extractMovieDtosFromShowings(List<Showing> showings) {
        return showings.stream().map(Showing::getMovie).map(movieService::toDto).distinct().collect(Collectors.toList());
    }

}
