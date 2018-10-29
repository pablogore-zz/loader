package com.wallethub.logger.http.services;

import com.wallethub.logger.http.Arguments;
import com.wallethub.logger.http.dto.Line;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Loader interfaces where expose the method to parse file and save into MySQL
 */
public interface Loader {
    /**
     * Parse log file and  store into MySQL
     * @param file
     */
    void logLoader(String file) throws Exception;

    /**
     * Parse log file and  store into MySQL
     * @param arguments
     */
    void report(Arguments arguments) throws Exception;

    /**
     * filter data by ip
     * @param arguments
     */
    List<Line> filterByIP(Arguments arguments) throws Exception;

}
