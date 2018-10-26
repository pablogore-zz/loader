package com.wallethub.logger.http.dao;

import com.wallethub.logger.http.dto.Line;

import java.sql.SQLException;
import java.util.List;

public interface LoaderDAO {
    /**
     * Store values into the database
     * @param lines
     *          list of value lines to be perform
     * @param commitNumber
     *          number of rows after commit the transaction
     * @return
     *      number of rows inserted
     */
    public int save(List<Line> lines, Integer commitNumber) throws SQLException;

    public int getCount() throws SQLException;
}
