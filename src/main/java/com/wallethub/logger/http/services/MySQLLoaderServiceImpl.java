package com.wallethub.logger.http.services;

import com.wallethub.logger.http.Utils;
import com.wallethub.logger.http.dto.Line;
import com.wallethub.logger.http.services.Loader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.toList;

public class MySQLLoaderServiceImpl implements Loader {
    @Override
    public void logLoader(String file) throws IOException {
        List<Line> lines = Files.lines(Paths.get(file))
                .map(line -> new StringTokenizer(line, "|"))
                .map(t-> buildLine(t))
                .filter(line-> line!=null)
                .collect(toList());

        lines.stream().map(this::save);
    }

    //This method should store each line into the mysql
    private <R> R save(Line line) {
        return null;
    }


    private Line buildLine(StringTokenizer t){
        try {
            return new Line(Utils.getDate(t.nextToken()), t.nextToken(), t.nextToken(), t.nextToken(), t.nextToken());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}