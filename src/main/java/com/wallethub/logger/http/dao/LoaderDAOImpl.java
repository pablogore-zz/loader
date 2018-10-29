package com.wallethub.logger.http.dao;

import com.wallethub.logger.http.Utils;
import com.wallethub.logger.http.dto.Line;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LoaderDAOImpl implements LoaderDAO {


    public LoaderDAOImpl() {
    }

    @Override
    public int save(List<Line> lines) throws Exception {

        String sql = "insert into access_logger (OP_DATE, IP,REQUEST,STATUS,USER_AGENT) values (?, ?, ?, ?, ?)";

        Connection connection = Utils.getConnection();

        PreparedStatement ps = connection.prepareStatement(sql);

        int countLines = 0;
        final int batchSize = 1000;

        for (Line line : lines) {
            ps.setTimestamp(1, new Timestamp(line.getDate().getTime()));
            ps.setString(2, line.getIp());
            ps.setString(3, line.getRequest());
            ps.setString(4, line.getStatus());
            ps.setString(5, line.getUserAgent());
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
        String sql1 = "delete from report_logger ";

        Connection connection = Utils.getConnection();

        Statement stmt = connection.createStatement();

        stmt.executeUpdate(sql);
        stmt.executeUpdate(sql1);

        connection.commit();
        connection.close();
    }

    @Override
    public int report(String startDate,String duration,String threshold) throws Exception {

        String sql = this.buildQuery(startDate,duration,threshold);

        Connection connection = Utils.getConnection();

        Statement stmt = connection.createStatement();

        int rows = stmt.executeUpdate(sql);

        connection.commit();
        connection.close();
        return rows;
    }

    @Override
    public List<Line> report(String ip) throws Exception {
        String sql = String.format(" SELECT IP ,STATUS ,REQUEST , USER_AGENT FROM access_logger WHERE IP='%s'", ip);

        Connection connection = Utils.getConnection();

        Statement stmt = connection.createStatement();

        ResultSet rows = stmt.executeQuery(sql);

        List<Line> list  = new ArrayList<>();

        while (rows.next()){

            Line line = new Line(new Date(rows.getTimestamp("OP_DATE").getTime())
                    ,rows.getString("IP")
                    ,rows.getString("REQUEST")
                    ,rows.getString("STATUS")
                    ,rows.getString("USER_AGENT"));
            list.add(line);
        }
        return list;
    }

    private String buildQuery(String startDate,String duration,String threshold){

        StringBuffer sb = new StringBuffer();
        String sql = String.format(sb.append("INSERT INTO report_logger (IP,STATUS,REQUEST,USER_AGENT,TOTAL)")
                .append(" SELECT distinct(IP),STATUS,REQUEST,USER_AGENT,count(*) as TOTAL ")
                .append(" FROM access_logger WHERE  `OP_DATE` BETWEEN '%s' and '%s' ")
                .append(" GROUP BY IP,STATUS,REQUEST,USER_AGENT HAVING TOTAL> %s;")
                .toString(), startDate,nextTo(startDate,duration),threshold);

    return  sql;


    }

    public static void main(String args[]) throws Exception {
        System.out.println(
                new LoaderDAOImpl().buildQuery("2017-01-01.13:00:00","hourly","100"));
    }



    private String nextTo(String date , String duration ){
        if ("hourly".equals(duration)){
            return Utils.getDatToString(nextHour(date));
        }

        if ("daily".equals(duration)){
            return Utils.getDatToString(next24Hours(date));

        }

        return null;
    }

    private Date next24Hours(String date) {
        Date current  = Utils.getDate(date);

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(current); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 24); // adds one hour

        return cal.getTime(); //
    }

    private Date nextHour(String date){

        Date current  = Utils.getDate(date);

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(current); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour

        return cal.getTime();
    }
}