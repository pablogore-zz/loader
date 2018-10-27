package com.wallethub.logger.http.command;

import com.wallethub.logger.http.Arguments;

@FunctionalInterface
public interface Command {
    /**
     * Command execution method
     */
    void execute();
}
