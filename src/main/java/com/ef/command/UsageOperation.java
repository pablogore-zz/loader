package com.ef.command;

import com.ef.Arguments;

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
