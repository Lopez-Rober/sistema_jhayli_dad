package com.jhayli.system.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
    public static String FORMAT_DATE_ISO = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static String toISOString(Date date, String format, TimeZone tz) {
        if (format == null) {
            format = FORMAT_DATE_ISO;
        }
        if (tz == null) {
            tz = TimeZone.getDefault();
        }
        DateFormat f = new SimpleDateFormat(format);
        f.setTimeZone(tz);
        return f.format(date);
    }
    
    public static String toISOString(Date date) {
        return toISOString(date, FORMAT_DATE_ISO, TimeZone.getDefault());
    }
}
