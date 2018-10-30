package com.ef.command;

import com.ef.Arguments;
import com.ef.services.Loader;
import com.ef.services.MySQLLoaderServiceImpl;

public class SaveOperation implements Command {

    private final Arguments arguments;

    public SaveOperation(Arguments arguments) {
        this.arguments = arguments;
    }


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
