package com.wallethub.logger.http;

import com.github.jankroken.commandline.CommandLineParser;
import com.github.jankroken.commandline.OptionStyle;
import com.wallethub.logger.http.command.*;
import com.wallethub.logger.http.services.MySQLLoaderServiceImpl;
import com.wallethub.logger.http.services.Loader;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * This is the Entry Point of the log processor
 */
public class Parser {

    public static final void main(String[] args) throws Exception {

        Arguments options = CommandLineParser.parse(Arguments.class, args,
                OptionStyle.LONG_OR_COMPACT);

        OperationExecutor executor = new OperationExecutor();
        if (options.isHelp()){
            Command command = new UsageOperation(options);
            command.execute();
            return;
        }else if (options.isClean()){
            CleanOperation clean = new CleanOperation();
            clean.execute();
        }else if(options.getAccessLog()!=null){
            Command command = new SaveOperation(options);
            command.execute();
        }else if(options.getStartDate()!=null && options.getDuration()!=null
                && options.getThreshold()!=null) {

            Command command = new ReportOperation(options);
            command.execute();
        }else if (!"".equals(options.getIp())){
            Command command = new IPReportOperation(options);
            command.execute();
        }
    }

}
