package pl.com.imralav.vxml.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class DateTimeService {
    public static final DateTimeFormatter READABLE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter READABLE_DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMMM uuuu",
                                                                                                 Locale.forLanguageTag("pl-pl"));
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
            return LocalDate.parse(initialDate, DateTimeFormatter.ofPattern(initialFormat));
        }

        public String toReadable() {
            return toDate().format(READABLE_DATE_FORMATTER);
        }
    }

    public String toReadable(LocalDate showingDate) {
        return showingDate.format(READABLE_DATE_FORMATTER);
    }

    public String toReadable(LocalTime showingTime) {
        return showingTime.format(READABLE_TIME_FORMATTER);
    }
}
