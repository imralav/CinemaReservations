package pl.com.imralav.vxml.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.com.imralav.vxml.entities.Showing;
import pl.com.imralav.vxml.repositories.ShowingRepository;

@RestController
@RequestMapping("/showings")
public class ShowingRest {

    @Autowired
    private ShowingRepository showingRepository;

    @RequestMapping
    public ResponseEntity<Iterable<Showing>> getShowings() {
        return ResponseEntity.ok(showingRepository.findAll());
    }

}
