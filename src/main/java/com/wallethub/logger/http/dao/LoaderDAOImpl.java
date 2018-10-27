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
    public int save(List<Line> lines, Integer commitNumber) throws SQLException {

        int countLines = 0;
        for (Line line : lines) {
            ps.setString(1, line.getId().get());
            ps.setDate(2, new Date(line.getDate().getTime()));
            ps.setString(3, line.getIp());
            ps.setString(4, line.getRequest());
            ps.setString(5, line.getStatus());
            ps.setString(6, line.getUserAgent());

            if (++countLines % commitNumber == 0) {
                ps.addBatch();
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
    public int getCount() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select count(*) as total from access_logger");
        if(rs.first()) return rs.getInt("total");
        return 0;
    }
}
