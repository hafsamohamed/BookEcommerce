package gov.iti.jets.presentation.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeAdapter extends XmlAdapter<String, LocalDate> {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, DATE_TIME_FORMATTER);
    }

    public String marshal(LocalDate v) throws Exception {
        return DATE_TIME_FORMATTER.format(v);
    }
}