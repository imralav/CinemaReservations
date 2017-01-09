package pl.com.imralav.vxml.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.com.imralav.vxml.entities.Movie;
import pl.com.imralav.vxml.entities.dtos.MovieDto;
import pl.com.imralav.vxml.repositories.MovieRepository;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<String> findAllTitles() {
        return movieRepository.findAll().stream().map(Movie::getTitle).collect(Collectors.toList());
    }

    public MovieDto toDto(Movie movie) {
        return new MovieDto(movie.getId(), movie.getTitle());
    }
}
