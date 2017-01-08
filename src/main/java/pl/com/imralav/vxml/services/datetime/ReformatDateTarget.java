package pl.com.imralav.vxml.services.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ReformatDateTarget extends ReformatTarget {

    public ReformatDateTarget(String initialDate, Locale locale, DateTimeFormatter formatter) {
        super(initialDate, locale, formatter);
    }

    public LocalDate toDate() {
        return LocalDate.parse(initial, DateTimeFormatter.ofPattern(initialFormat, locale));
    }

    public ReformatDateTarget from(String initialFormat) {
        this.initialFormat = initialFormat;
        return this;
    }

    @Override
    public String toReadable() {
        return toDate().format(formatter);
    }

    public ReformatDateTarget fromReadable() {
        this.initialFormat = DateTimeService.READABLE_DATE_FORMAT;
        return this;
    }
}
