package com.wallethub.logger.http.command;

import com.wallethub.logger.http.Arguments;

public class UsageOperation implements Command {

    private final Arguments arguments;

    public UsageOperation(Arguments arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        this.arguments.usage();
    }
}
