package com.wallethub.logger.http;

import com.github.jankroken.commandline.CommandLineParser;
import com.github.jankroken.commandline.OptionStyle;
import com.wallethub.logger.http.services.MySQLLoaderServiceImpl;
import com.wallethub.logger.http.services.Loader;

/**
 * This is the Entry Point of the log processor
 */
public class Parser {

    public static final void main(String[] args) throws Exception {

        Arguments options = CommandLineParser.parse(Arguments.class, args,
                OptionStyle.LONG_OR_COMPACT);
        if (options.isHelp()){
            options.usage();
            return;
        }


        //Parse and load file into the db
        Loader loader =  new MySQLLoaderServiceImpl();
        loader.logLoader(options.getAccessLog());
    }
}
