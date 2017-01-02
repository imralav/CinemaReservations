package pl.com.imralav.vxml.services;

import java.util.Arrays;
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

    public List<Seat> findAll(String unparsedSeatIds) {
        List<Integer> seatIds = Arrays.stream(unparsedSeatIds.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        return seatRepository.findAll(seatIds);
    }

    public String extractIds(List<Seat> seats) {
        return seats.stream().map(seat -> {
            return Integer.toString(seat.getId());
        }).collect(Collectors.joining(","));
    }

    public long count() {
        return seatRepository.count();
    }
}
