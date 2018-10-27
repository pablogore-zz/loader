package com.wallethub.logger.http;

import com.github.jankroken.commandline.CommandLineParser;
import com.github.jankroken.commandline.OptionStyle;
import com.wallethub.logger.http.command.CleanOperation;
import com.wallethub.logger.http.command.SaveOperation;
import com.wallethub.logger.http.command.UsageOperation;
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
            UsageOperation usage = new UsageOperation(options);
            usage.execute();
            return;
        }else if (options.isClean()){
            CleanOperation clean = new CleanOperation();
            clean.execute();
        }else if(options.getAccessLog()!=null || options.getAccessLog().length()!=0){
            SaveOperation save = new SaveOperation(options);
            save.execute();
        }else if(!"".equals(options.getStartDate()) && !"".equals(options.getDuration())
                && !"".equals(options.getThreshold())   ){


        }
    }

}
