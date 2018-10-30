package com.ef.command;

/**
 * Common Command interface.
 */
@FunctionalInterface
public interface Command {
    /**
     * Execute the current Command.
     */
    void execute();
}
