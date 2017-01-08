package pl.com.imralav.vxml.services.datetime;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public abstract class ReformatTarget {
    protected String initial;
    protected String initialFormat;
    protected Locale locale;
    protected DateTimeFormatter formatter;

    public ReformatTarget(String initial, Locale locale, DateTimeFormatter formatter) {
        this.initial = initial;
        this.locale = locale;
        this.formatter = formatter;
    }

    public abstract String toReadable();
}
