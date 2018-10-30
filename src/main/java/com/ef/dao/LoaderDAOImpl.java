package com.ef.dao;

import com.ef.Utils;
import com.ef.dto.Line;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The loader implementation dao.
 */
public class LoaderDAOImpl implements LoaderDAO {

    /**
     * Constant for period of time.
     */
    private static final int PERIOD_OF_TIME = 24;

    /**
     * this is a logger reference.
     */
    private static Logger logger =
            Logger.getLogger(LoaderDAOImpl.class);

    /**
     * this is the default constructor.
     */
    public LoaderDAOImpl() {
    }


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

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public int save(final List<Line> lines) throws Exception {
        logger.info("*************************************");
        logger.info("load access log into db");

        Instant startTx = Instant.now();


        String sql = "insert into access_logger "
                + "(OP_DATE, IP,REQUEST,STATUS,USER_AGENT) "
                + "values (?, ?, ?, ?, ?)";

        Connection connection = Utils.getConnection();

        PreparedStatement ps = connection.prepareStatement(sql);

        int countLines = 0;
        final int batchSize = 1000;

        for (Line line : lines) {
            int row = 1;
            ps.setTimestamp(row++, new Timestamp(line.getDate().getTime()));
            ps.setString(row++, line.getIp());
            ps.setString(row++, line.getRequest());
            ps.setString(row++, line.getStatus());
            ps.setString(row++, line.getUserAgent());

            ps.addBatch();

            if (++countLines % batchSize == 0) {
                ps.executeBatch();
                logger.info(countLines + " INSERTING..." + line);
            }
        }

        ps.executeBatch();
        ps.close();

        commit(connection);

        Instant finishTx = Instant.now();
        long timeElapsed = Duration.between(startTx, finishTx).toMillis();

        logger.info("End load file access log " + timeElapsed);
        logger.info("*************************************");
        return countLines;
    }

    /**
    * Clean db.
    * @throws Exception
    *          throw exception in clean operation
    */
    @Override
    public void clean() throws Exception {
        logger.info("*************************************");
        logger.info("clean tables");

        Instant start = Instant.now();

        String sql = "delete from access_logger ";
        String sql1 = "delete from report_logger ";

        Connection connection = Utils.getConnection();

        Statement stmt = connection.createStatement();

        stmt.executeUpdate(sql);
        stmt.executeUpdate(sql1);

        commit(connection);

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

        logger.info("clean tables end " + timeElapsed);
        logger.info("*************************************");
    }

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
    @Override
    public int report(final String startDate, final String duration,
                      final String threshold) throws Exception {
        logger.info("*************************************");
        logger.info("clean tables");


        String sql = this.buildQuery(startDate, duration, threshold);

        Connection connection = Utils.getConnection();

        Statement stmt = connection.createStatement();

        int rows = stmt.executeUpdate(sql);

        commit(connection);
        return rows;
    }

    /**
     *  Report that filter the result by ip.
     * @param ip
     *          the ip to filter
     * @return  a list of dto Line
     * @throws Exception
     *             throw exception when execute report filter by ip
     */
    @Override
    public List<Line> report(final String ip) throws Exception {
        String sql = String.format(" SELECT "
                + "IP "
                + ",STATUS "
                + ",REQUEST "
                + ",USER_AGENT "
                + ",OP_DATE "
                + "FROM access_logger WHERE IP='%s'", ip);

        Instant start = Instant.now();

        Connection connection = Utils.getConnection();

        Statement stmt = connection.createStatement();

        ResultSet rows = stmt.executeQuery(sql);

        List<Line> list  = new ArrayList<>();

        logger.info("*************************************");
        logger.info("filter by ip start");
        while (rows.next()) {

            Line line = new Line(new Date(
                    rows.getTimestamp("OP_DATE").getTime()),
                    rows.getString("IP"),
                    rows.getString("REQUEST"),
                    rows.getString("STATUS"),
                    rows.getString("USER_AGENT"));
            list.add(line);
            logger.info(line);
        }


        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

        logger.info("filter by ip end " + timeElapsed);
        logger.info("*************************************");
        return list;
    }

    /**
     * Build the Sql Query String.
     * @param startDate
     *          the startDate
     * @param duration
     *          the duration
     *
     * @param threshold
     *          the threshold
     * @return the sql query
     */
    private String buildQuery(final String startDate, final String duration,
                              final String threshold) {

        StringBuffer sb = new StringBuffer();
        String sql = String.format(sb.append("INSERT INTO report_logger ("
                + "IP "
                + ",STATUS "
                + ",REQUEST "
                + ",USER_AGENT "
                + ",TOTAL)"
                )
                .append(" SELECT "
                        + "distinct(IP) "
                        + ",STATUS "
                        + ",REQUEST "
                        + ",USER_AGENT "
                        + ",count(*) as TOTAL "
                )
                .append(" FROM access_logger WHERE  "
                        + "`OP_DATE` BETWEEN "
                        + "'%s' and '%s' ")
                .append(" GROUP BY "
                        + "IP "
                        + ",STATUS "
                        + ",REQUEST "
                        + ",USER_AGENT HAVING TOTAL> %s;")
                .toString(), startDate,
                nextTo(startDate, duration), threshold);

    return  sql;


    }

    /**
     * Get the next day depending of the duration type.
     * @param date
     *          the date
     * @param duration
     *          the duration
     *
     * @return
     *          the String representation of the next day
     *          to be used in the query.
     */
    private String nextTo(final String date, final String duration) {
        if ("hourly".equals(duration)) {
            return Utils.getDatToString(next(date, 1));
        }

        if ("daily".equals(duration)) {
            return Utils.getDatToString(next(date, PERIOD_OF_TIME));

        }

        return null;
    }

    /**
     * retuen the next  day form the current date.
     * @param date
     *          the date
     * @param  move
     *          move time
     * @return  a Date
     */
    private Date next(final String date, final int move){
        Date current = Utils.getDate(date);

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(current); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, move); // adds one hour
        return cal.getTime();
    }

    /**
     * Commit transaction.
     * @param conn the connection
     * @throws  SQLException throw
     */
    private void commit(final Connection conn) throws SQLException {
        conn.commit();
        conn.close();

    }
}
