package com.ef.dao;

import com.ef.dto.Line;

import java.util.List;

/**
 * Handling operation with the database.
 */
public interface LoaderDAO {
    /**
     * Store values into the database.
     * @param lines
     *          list of value lines to be perform
     * @return
     *      number of rows inserted
     *
     * @throws Exception
     *             throw exception when save list into the database.
     */
    int save(List<Line> lines) throws Exception;

    /**
     * Clean db.
     * @throws Exception
     *          throw exception in clean operation
     */
    void clean() throws Exception;

    /**
     * Perform the report counting the request in a certain piriod of time.
     * @param startDate
     *          startDate param
     * @param duration
     *          duration param
     * @param threshold
     *          threshold param
     * @return
     *      the number of record
     * @throws Exception
     *             throw exception when execute report.
     */
    int report(String startDate, String duration,
               String threshold) throws Exception;

    /**
     *  Report that filter the result by ip.
     * @param ip
     *          the ip to filter
     * @return  a list of dto Line
     * @throws Exception
     *             throw exception when execute report filter by ip
     */
    List<Line> report(String ip) throws Exception;

}
