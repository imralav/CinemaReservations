package pl.com.imralav.vxml.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.com.imralav.vxml.entities.Seat;
import pl.com.imralav.vxml.repositories.SeatRepository;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> findAll(List<Integer> seatIds) {
        return seatRepository.findAll(seatIds);
    }

    public List<Integer> extractIds(List<Seat> seats) {
        return seats.stream().mapToInt(Seat::getId).boxed().collect(Collectors.toList());
    }

}
