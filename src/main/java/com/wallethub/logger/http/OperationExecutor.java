package com.wallethub.logger.http;

import com.wallethub.logger.http.command.Command;

public class OperationExecutor {

    /**
     *
     * @param command
     */
    public void execute(Command command){

        command.execute();
    }
}
