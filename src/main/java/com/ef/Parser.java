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

        Utils.configurationParser();
        Arguments options = CommandLineParser.parse(Arguments.class, args,
                OptionStyle.LONG_OR_COMPACT);

        if (options.isHelp()) {
            Command command = new UsageOperation(options);
            command.execute();
            return;
        } else if (options.isClean()) {
            CleanOperation clean = new CleanOperation();
            clean.execute();
        } else if (!Utils.isNullorEmpty(options.getAccessLog())) {
            new SaveOperation(options).execute();
        } else if (!Utils.isNullorEmpty(options.getStartDate())
                && !Utils.isNullorEmpty(options.getDuration())
                && !Utils.isNullorEmpty(options.getThreshold())) {
            new ReportOperation(options).execute();
        } else if (!Utils.isNullorEmpty(options.getIp())) {
            new IPReportOperation(options).execute();
        } else {
            new UsageOperation(options).execute();
        }
    }
}
