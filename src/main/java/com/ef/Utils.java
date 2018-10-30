package com.ef;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * This is a class to handle static helpers methods .
 */
public final class Utils {

    /**
     * Hidden default constructor.
     */
    private Utils() {
    }

    /**
     * Create a Date using the String parameter.
     * @param str
     *          the String date representation to be transform.
     * @return Date in yyyy-MM-dd.HH:mm:ss format.
     * @throws ParseException
     */

    public static Date getDate(final String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss",
                Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Create a Date using the String parameter.
     * @param str
     *      the String date representation to be transform.
     * @return Date in yyyy-MM-dd HH:mm:ss.SSS format.
     */
    public static Date getDateSSS(final String str) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS",
                Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        try {
            return formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Get the string date.
     * @param current
     *          A Date
     * @return
     *      the Date string representation in yyyy-MM-dd HH:mm:ss.SSS format.
     */
    public static String getDatToString(final Date current) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS",
                Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        return formatter.format(current);
    }

    /**
     * Create a connection the MySQL database.
     * @return the Connection
     * @throws Exception
     *          exception throw
     */
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String connection = "jdbc:mysql://localhost:3306/loaderdb";
        Connection conn = DriverManager.getConnection(connection,
                "loader", "loader");
        conn.setAutoCommit(false);
        return conn;
    }
}

