package com.ef.command;

import com.ef.Arguments;

/**
 * This class execute a Command show the usage string.
 */
public class UsageOperation implements Command {

    /**
     * hold the program arguments.
     */
    private final Arguments arguments;

    /**
     * Contructor with Arguments parameter.
     * @param arguments
     *          the arguments programs
     */
    public UsageOperation(final Arguments arguments) {
        this.arguments = arguments;
    }

    /**
     * Execute the current Command.
     */
    @Override
    public void execute() {
        this.arguments.usage();
    }
}
