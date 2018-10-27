package com.wallethub.logger.http.services;

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

        int totalRows = new LoaderDAOImpl().save(lines,1000);
        System.out.println("TOTAL=" + totalRows);
        System.out.println("TOTAL FROM * =" + new LoaderDAOImpl().getCount());
    }


    private Line buildLine(StringTokenizer t){
        try {
            return new Line(Optional.of(UUID.randomUUID().toString()),Utils.getDate(t.nextToken()), t.nextToken(), t.nextToken(), t.nextToken(), t.nextToken());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}