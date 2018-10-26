package com.wallethub.logger.http;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * This is a class to handle static helpers methods
 * like transformation string to date.
 */
public class Utils {

    /**
     * Create a Date using the String parameter
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date getDate(String str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        return formatter.parse(str);
    }


    public static Connection getConnection(){
        return null;
    }
}

