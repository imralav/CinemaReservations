package pl.com.imralav.vxml.services.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class DateTimeService {

    private static final Locale POLISH_LOCALE = Locale.forLanguageTag("pl-pl");
    public static final String READABLE_DATE_FORMAT = "d MMMM uuuu";
    public static final DateTimeFormatter READABLE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter READABLE_DATE_FORMATTER = DateTimeFormatter.ofPattern(READABLE_DATE_FORMAT,
                                                                                                POLISH_LOCALE);

    public ReformatDateTarget reformatDate(String initialDate) {
        return new ReformatDateTarget(initialDate, POLISH_LOCALE, READABLE_DATE_FORMATTER);
    }

    public LocalDateTime toDatetime(String dateText, String timeText) {
        LocalDate date = reformatDate(dateText).fromReadable().toDate();
        LocalTime time = toTime(timeText);
        return LocalDateTime.of(date, time);
    }

    public String toReadable(LocalDate showingDate) {
        return showingDate.format(READABLE_DATE_FORMATTER);
    }

    public String toReadable(LocalTime showingTime) {
        return showingTime.format(READABLE_TIME_FORMATTER);
    }

    public LocalTime toTime(String initialTime) {
        return LocalTime.parse(initialTime, READABLE_TIME_FORMATTER);
    }
}
