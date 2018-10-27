package com.wallethub.logger.http.services;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Loader interfaces where expose the method to parse file and save into MySQL
 */
public interface Loader {
    /**
     * Parse log file and  store into MySQL
     * @param file
     */
    public void logLoader(String file) throws Exception;
}
