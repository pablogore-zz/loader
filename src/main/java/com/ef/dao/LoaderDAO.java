package com.ef.dao;

import com.ef.dto.Line;

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

    /**
     *
     * @param startDate
     * @param duration
     * @param threshold
     * @return
     * @throws Exception
     */
    int report(String startDate,String duration,String threshold) throws Exception;

    /**
     *
     * @param ip
     * @return
     * @throws Exception
     */
    List<Line> report(String ip) throws Exception;

}
