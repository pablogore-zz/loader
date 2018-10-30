package com.ef;

import com.ef.command.Command;

/**
 * Executor Command pattern.
 */
public class OperationExecutor {

    /**
     *  This is the execute method for each command.
     * @param command
     *          The command to be perform
     */
    public void execute(final Command command) {

        command.execute();
    }
}
