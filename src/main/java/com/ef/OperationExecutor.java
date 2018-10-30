package com.ef;

import com.ef.command.Command;

public class OperationExecutor {

    /**
     *
     * @param command
     */
    public void execute(Command command){

        command.execute();
    }
}
