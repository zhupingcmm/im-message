package com.ocbc.project.infrastructure.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    private static final String TIME_PATTERN = "yyyy-MM-DD HH:mm:ss";
    public static Date parseStrDateTime(String dateTime){
        DateFormat dateFormat = new SimpleDateFormat(TIME_PATTERN);
        try {
           return dateFormat.parse(dateTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getStrDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
