package pl.com.imralav.vxml.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.com.imralav.vxml.entities.Seat;
import pl.com.imralav.vxml.repositories.SeatRepository;

@RestController
@RequestMapping("/seats")
public class SeatRest {

    @Autowired
    private SeatRepository seatRepository;

    @RequestMapping
    public ResponseEntity<Iterable<Seat>> getBookings() {
        return ResponseEntity.ok(seatRepository.findAll());
    }

}
