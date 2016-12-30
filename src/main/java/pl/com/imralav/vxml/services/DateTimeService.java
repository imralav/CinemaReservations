package pl.com.imralav.vxml.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Service;

import pl.com.imralav.vxml.services.DateTimeService.ReformatTarget;

@Service
public class DateTimeService {
    private static final Locale POLISH_LOCALE = Locale.forLanguageTag("pl-pl");
    private static final String READABLE_DATE_FORMAT = "d MMMM uuuu";
    public static final DateTimeFormatter READABLE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter READABLE_DATE_FORMATTER = DateTimeFormatter.ofPattern(READABLE_DATE_FORMAT,
                                                                                                 POLISH_LOCALE);
    public ReformatTarget reformat(String initialDate) {
        return new ReformatTarget(initialDate);
    }

    public class ReformatTarget {
        private String initialDate;
        private String initialFormat;

        public ReformatTarget(String initialDate) {
            this.initialDate = initialDate;
        }

        public ReformatTarget from(String initialFormat) {
            this.initialFormat = initialFormat;
            return this;
        }

        public LocalDate toDate() {
            return LocalDate.parse(initialDate, DateTimeFormatter.ofPattern(initialFormat, POLISH_LOCALE));
        }

        public String toReadable() {
            return toDate().format(READABLE_DATE_FORMATTER);
        }

        public ReformatTarget fromReadable() {
            this.initialFormat = READABLE_DATE_FORMAT;
            return this;
        }
    }

    public String toReadable(LocalDate showingDate) {
        return showingDate.format(READABLE_DATE_FORMATTER);
    }

    public String toReadable(LocalTime showingTime) {
        return showingTime.format(READABLE_TIME_FORMATTER);
    }
}
