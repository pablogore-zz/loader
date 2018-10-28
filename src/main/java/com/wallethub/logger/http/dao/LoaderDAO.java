package com.wallethub.logger.http.dao;

import com.wallethub.logger.http.dto.Line;

import java.sql.SQLException;
import java.util.List;

public interface LoaderDAO {
    /**
     * Store values into the database
     * @param lines
     *          list of value lines to be perform
     * @return
     *      number of rows inserted
     */
    int save(List<Line> lines) throws Exception;

    /**
     * Clean db
     * @throws Exception
     */
    void clean() throws Exception;

    int report(String startDate,String duration,String threshold) throws Exception;
}
