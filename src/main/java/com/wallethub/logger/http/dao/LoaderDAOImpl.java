package com.wallethub.logger.http.dao;

import com.mysql.cj.jdbc.CallableStatement;
import com.wallethub.logger.http.Utils;
import com.wallethub.logger.http.dto.Line;

import java.sql.*;
import java.util.List;

public class LoaderDAOImpl implements LoaderDAO {
    String sql = "insert into access_logger (ID, OP_DATE, IP,REQUEST,STATUS,USER_AGENT) values (?, ?, ?, ?, ?, ?)";

    Connection connection = Utils.getConnection();

    PreparedStatement ps = connection.prepareStatement(sql);


    public LoaderDAOImpl() throws Exception {
    }

    @Override
    public int save(List<Line> lines) throws SQLException {

        int countLines = 0;
        long start = System.currentTimeMillis();

        final int batchSize = 1000;

        for (Line line : lines) {
            ps.setString(1, line.getId().get());
            ps.setTimestamp(2, new Timestamp(line.getDate().getTime()));
            ps.setString(3, line.getIp());
            ps.setString(4, line.getRequest());
            ps.setString(5, line.getStatus());
            ps.setString(6, line.getUserAgent());
            ps.addBatch();

            if(++countLines % batchSize == 0) {
                ps.executeBatch();
                System.out.println("INSERTING ....." + countLines);
            }
        }

        ps.executeBatch(); // insert remaining records
        ps.close();
        connection.commit();
        connection.close();

        return countLines;
    }

    @Override
    public void clean() throws Exception {
        String sql = "delete from access_logger ";

        Connection connection = Utils.getConnection();

        PreparedStatement ps = connection.prepareStatement(sql);

        Statement stmt = connection.createStatement();
        int deleted = stmt.executeUpdate(sql);
        if(deleted==0){
            System.out.println("Deleted All Rows In The Table Successfully...");
        }else{
            System.out.println("Table already empty.");
        }
        connection.close();
    }
}
