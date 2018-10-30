package com.ef;

import java.sql.Connection;
import java.sql.DriverManager;
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

    public static Date getDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getDateSSS(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getDatToString(Date current) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        return formatter.format(current);
    }




    public static Connection getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        String connection = "jdbc:mysql://localhost:3306/loaderdb";
        Connection conn = DriverManager.getConnection(connection, "loader", "loader");
        conn.setAutoCommit(false);
        return conn;
    }
}

