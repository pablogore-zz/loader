package com.ef.command;

import com.ef.Arguments;
import com.ef.services.MySQLLoaderServiceImpl;

/**
 * This class execute a Command for filter report by ip.
 */
public class IPReportOperation implements Command {

    /**
     * hold the program arguments.
     */
    private final Arguments arguments;

    /**
     * Contructor with Arguments parameter.
     * @param arguments
     *          the arguments programs
     */
    public IPReportOperation(final Arguments arguments) {
        this.arguments = arguments;
    }

    /**
     * Execute the current Command.
     */
    @Override
    public void execute() {
        try {
            new MySQLLoaderServiceImpl().filterByIP(arguments);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
