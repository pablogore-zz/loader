package com.ef.services;

import com.ef.Arguments;
import com.ef.Utils;
import com.ef.dao.LoaderDAOImpl;
import com.ef.dto.Line;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.toList;

/**
 * This class represent the service that work with access file.
 */
public class MySQLLoaderServiceImpl implements Loader {
    /**
     * Parse log file and  store into MySQL.
     * @param file
     *          this is the file
     * @throws Exception to be throw.
     */
    @Override
    public void logLoader(final String file) throws Exception {
        List<Line> lines = Files.lines(Paths.get(file))
                .map(line -> new StringTokenizer(line, "|"))
                .map(t-> buildLine(t))
                .filter(line-> line != null)
                .collect(toList());

        int totalRows = new LoaderDAOImpl().save(lines);
        System.out.println("TOTAL=" + totalRows);
    }

    /**
     * Parse log file and  store into MySQL.
     * @param arguments
     *          this is the arguments
     * @throws Exception to be throw.
     */
    @Override
    public void report(final Arguments arguments) throws Exception {
        int totalRows = new LoaderDAOImpl().report(arguments.getStartDate(),
                arguments.getDuration(),
                arguments.getThreshold());

        System.out.println("TOTAL=" + totalRows);
    }

    /**
     * filter data by ip.
     * @param arguments
     *          this is the arguments
     * @throws Exception to be throw.
     * @return the list o lines
     */
    @Override
    public List<Line> filterByIP(final Arguments arguments) throws Exception {
       return new LoaderDAOImpl().report(arguments.getIp());
    }

    /**
     * This method is a helper to build a Line object.
     * @param t is the String token
     * @return a Line object
     */
    private Line buildLine(final StringTokenizer t){
        return new Line(Utils.getDateSSS(t.nextToken()), t.nextToken(),
                t.nextToken(), t.nextToken(), t.nextToken());
    }
}
