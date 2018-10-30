package com.ef.command;

@FunctionalInterface
public interface Command {
    /**
     * Command execution method
     */
    void execute();
}
