package ru.c_energies.utils.converters;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateFormat {
    private final String PATTERN = "yyyy-MM-dd HH:mm";
    private final int unixtime;
    public DateFormat(int unixtime){
        this.unixtime = unixtime;
    }
    public String convert(){
        Instant instant = Instant.ofEpochSecond(Long.parseLong(String.valueOf(this.unixtime)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.PATTERN)
                .withZone(ZoneId.systemDefault());
        String format = formatter.format(instant);
        return format;
    }
}
