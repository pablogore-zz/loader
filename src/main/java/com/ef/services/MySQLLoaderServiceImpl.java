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

public class MySQLLoaderServiceImpl implements Loader {
    @Override
    public void logLoader(String file) throws Exception {
        List<Line> lines = Files.lines(Paths.get(file))
                .map(line -> new StringTokenizer(line, "|"))
                .map(t-> buildLine(t))
                .filter(line-> line!=null)
                .collect(toList());

        int totalRows = new LoaderDAOImpl().save(lines);
        System.out.println("TOTAL=" + totalRows);
    }

    @Override
    public void report(Arguments arguments) throws Exception {
        int totalRows = new LoaderDAOImpl().report(arguments.getStartDate(),arguments.getDuration(),arguments.getThreshold());
        System.out.println("TOTAL=" + totalRows);
    }

    @Override
    public List<Line> filterByIP(Arguments arguments) throws Exception {
       return new LoaderDAOImpl().report(arguments.getIp());
    }


    private Line buildLine(StringTokenizer t){
        return new Line(Utils.getDateSSS(t.nextToken()), t.nextToken(), t.nextToken(), t.nextToken(), t.nextToken());
    }
}