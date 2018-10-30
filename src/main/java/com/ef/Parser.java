package com.ef;

import com.ef.command.CleanOperation;
import com.ef.command.Command;
import com.ef.command.IPReportOperation;
import com.ef.command.ReportOperation;
import com.ef.command.SaveOperation;
import com.ef.command.UsageOperation;
import com.github.jankroken.commandline.CommandLineParser;
import com.github.jankroken.commandline.OptionStyle;

/**
 * This is the Entry Point of the log processor.
 */
public final class Parser {

    /**
     * Hidden default contructor.
     */
    private Parser() {
    }

    /**
     * Entry point method.
     *
     * @param args argument to be read
     * @throws Exception The exception to be trhow
     */
    public static void main(final String[] args) throws Exception {


        Arguments options = CommandLineParser.parse(Arguments.class, args,
                OptionStyle.LONG_OR_COMPACT);

        OperationExecutor executor = new OperationExecutor();
        if (options.isHelp()) {
            Command command = new UsageOperation(options);
            command.execute();
            return;
        } else if (options.isClean()) {
            CleanOperation clean = new CleanOperation();
            clean.execute();
        } else if (options.getAccessLog() != null) {
            Command command = new SaveOperation(options);
            command.execute();
        } else if (options.getStartDate() != null
                && options.getDuration() != null
                && options.getThreshold() != null) {

            Command command = new ReportOperation(options);
            command.execute();
        } else if (options.getIp() != null) {
            Command command = new IPReportOperation(options);
            command.execute();
        } else {
            Command command = new UsageOperation(options);
            command.execute();
            return;
        }
    }

}
