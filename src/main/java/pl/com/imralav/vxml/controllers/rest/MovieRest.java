package pl.com.imralav.vxml.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.com.imralav.vxml.entities.Movie;
import pl.com.imralav.vxml.repositories.MovieRepository;

@RestController
@RequestMapping("/movies")
public class MovieRest {

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping
    public ResponseEntity<Iterable<Movie>> getMovies() {
        return ResponseEntity.ok(movieRepository.findAll());
    }

}
