package pl.com.imralav.vxml.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import pl.com.imralav.vxml.entities.Seat;

@Service
public class ReadableSeatsBuilder {

    public String buildFor(List<Seat> prepareSeats) {
        String result = extractRowNumbers(prepareSeats).mapToObj(rowNumber -> {
            return extractSeatNumbersToStr(prepareSeats, rowNumber).collect(rowCollector(rowNumber));
        }).collect(Collectors.joining(", "));
        return result;
    }

    private IntStream extractRowNumbers(List<Seat> prepareSeats) {
        return prepareSeats.stream().mapToInt(Seat::getRowNumber).distinct().sorted();
    }

    private Stream<String> extractSeatNumbersToStr(List<Seat> prepareSeats, int rowNumber) {
        return extractSeatNumbersToInt(prepareSeats, rowNumber).mapToObj(seatNumber -> {
            return seatNumber + "";
        });
    }

    private IntStream extractSeatNumbersToInt(List<Seat> prepareSeats, int rowNumber) {
        return prepareSeats.stream().filter(seat -> {
            return seat.getRowNumber() == rowNumber;
        }).mapToInt(Seat::getSeatNumber).sorted();
    }


    private Collector<CharSequence, ?, String> rowCollector(int rowNumber) {
        return Collectors.joining(", ", "", " w rzędzie " + rowNumber);
    }
}
