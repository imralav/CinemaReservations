package pl.com.imralav.vxml.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.com.imralav.vxml.entities.Seat;
import pl.com.imralav.vxml.entities.Showing;
import pl.com.imralav.vxml.entities.dtos.ShowingDto;
import pl.com.imralav.vxml.repositories.ShowingRepository;
import pl.com.imralav.vxml.services.datetime.DateTimeService;

@Service
public class ShowingService {

    @Autowired
    private ShowingRepository showingRepository;

    @Autowired
    private DateTimeService dateTimeService;

    public List<Showing> findForDate(LocalDate date) {
        return convertDateToBetweenPeriod(date, (from, to) -> {
            return showingRepository.findByShowingDatetimeBetween(from, to);
        });
    }

    private <T> T convertDateToBetweenPeriod(LocalDate date, BiFunction<LocalDateTime, LocalDateTime, T> biFunction) {
        LocalDateTime from = date.atStartOfDay();
        LocalDateTime to = from.plusDays(1);
        return biFunction.apply(from, to);
    }

    public List<Showing> findForDateAndMovieId(LocalDate date, Integer movieId) {
        return convertDateToBetweenPeriod(date, (from, to) -> {
            return showingRepository.findByShowingDatetimeBetweenAndMovieId(from, to, movieId);
        });
    }

    public ShowingDto toDto(Showing showing) {
        ShowingDto dto = new ShowingDto();
        dto.setId(showing.getId());
        dto.setMovieTitle(showing.getMovieTitle());
        String readableDate = dateTimeService.toReadable(showing.getShowingDate());
        String readableTime = dateTimeService.toReadable(showing.getShowingTime());
        dto.setReadableDate(readableDate);
        dto.setReadableTime(readableTime);
        return dto;
    }

    public List<ShowingDto> toDto(List<Showing> showings) {
        return showings.stream().map(showing -> {
            return toDto(showing);
        }).collect(Collectors.toList());
    }

    public Showing findOne(Integer id) {
        return showingRepository.findOne(id);
    }

    public int findEmptySeatsAmountForShowingId(Integer showingId) {
        return showingRepository.findEmptySeatsAmountForShowingId(showingId);
    }

    public List<Seat> findEmptySeatsForShowingId(Integer showingId) {
        return showingRepository.findEmptySeatsForShowingId(showingId);
    }

    public Map<String, Integer> getTimeToShowingIdMap(List<ShowingDto> showingDtos) {
        return showingDtos.stream().collect(Collectors.toMap(ShowingDto::getReadableTime, ShowingDto::getId));
    }

    public Showing findByDatetimeAndMovieTitle(LocalDateTime showingDatetime, String movieTitle) {
        return showingRepository.findByShowingDatetimeAndMovie_TitleIgnoreCase(showingDatetime, movieTitle);
    }

    public boolean existsForDatetimeAndMovieTitle(LocalDateTime showingDatetime, String movieTitle) {
        return showingRepository.doesExistForShowingDateTimeAndMovieTitle(showingDatetime, movieTitle);
    }
}
