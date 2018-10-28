package com.wallethub.logger.http.services;

import com.wallethub.logger.http.Arguments;
import com.wallethub.logger.http.Utils;
import com.wallethub.logger.http.dao.LoaderDAOImpl;
import com.wallethub.logger.http.dto.Line;
import com.wallethub.logger.http.services.Loader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.UUID;

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


    private Line buildLine(StringTokenizer t){
        return new Line(Utils.getDateSSS(t.nextToken()), t.nextToken(), t.nextToken(), t.nextToken(), t.nextToken());
    }
}