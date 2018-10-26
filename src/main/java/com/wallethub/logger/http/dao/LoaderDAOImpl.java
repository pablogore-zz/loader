package com.wallethub.logger.http.dao;

import com.mysql.cj.jdbc.CallableStatement;
import com.wallethub.logger.http.dto.Line;

import java.sql.*;
import java.util.List;

public class LoaderDAOImpl implements LoaderDAO {
    String sql = "insert into access_logger (ID, DATE, IP,REQUEST,STATUS,USER_AGENT) values (?, ?, ?, ?, ?, ?)";

    Connection connection = getConnection();

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName ("org.h2.Driver");
        Connection conn = DriverManager.getConnection ("jdbc:h2:./target/foobar", "sa","");
        return  conn;
    }

    PreparedStatement ps = connection.prepareStatement(sql);


    public LoaderDAOImpl() throws SQLException, ClassNotFoundException {
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
                ps.executeBatch();
                System.out.println("INSRTANDO ....." + countLines);
            }
        }

        ps.executeBatch(); // insert remaining records
        ps.close();
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
