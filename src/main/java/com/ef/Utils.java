package com.ef;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
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
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss",
                Locale.ENGLISH);
        simple.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        try {
            return simple.parse(str);
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
                "yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
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
        Properties properties = configurationParser();
        Class.forName(properties.getProperty("driver"));
        String connection = properties.getProperty("url");
        Connection conn = DriverManager.getConnection(connection,
                properties.getProperty("dbuser"),
                properties.getProperty("dbpassword"));
        conn.setAutoCommit(false);
        return conn;
    }

    /**
     * helper method to verify is a value is null or empty.
     * @param str
     *          the str param
     * @return
     *      true is the value is empty or null otherwise false
     */
    public static boolean isNullorEmpty(final String str){
        if (str == null) {
            return true;
        }else if (str.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Helper method for read the configuration file config.properties .
     * @return the Properties
     * @throws Exception throw exception if the file does not exist.
     *
     */
    public static Properties configurationParser() throws Exception {
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("config.properties");
        // load a properties file
        prop.load(input);

        return prop;

    }
}

