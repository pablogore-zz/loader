package com.ef.command;

import com.ef.Arguments;
import com.ef.services.Loader;
import com.ef.services.MySQLLoaderServiceImpl;

/**
 * This class execute a Command that save a result into mysql table.
 */
public class SaveOperation implements Command {

    /**
     * hold the program arguments.
     */
    private final Arguments arguments;

    /**
     * Contructor with Arguments parameter.
     * @param arguments
     *          the arguments programs
     */
    public SaveOperation(final Arguments arguments) {
        this.arguments = arguments;
    }

    /**
     * Execute the current Command.
     */
    @Override
    public void execute() {

        Loader loader = new MySQLLoaderServiceImpl();

        try {
            loader.logLoader(arguments.getAccessLog());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
