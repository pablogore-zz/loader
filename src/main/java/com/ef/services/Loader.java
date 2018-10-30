package com.ef.services;

import com.ef.Arguments;
import com.ef.dto.Line;

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
