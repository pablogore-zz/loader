package com.ef.command;

import com.ef.Arguments;
import com.ef.services.Loader;
import com.ef.services.MySQLLoaderServiceImpl;

public class ReportOperation implements Command {

    private final Arguments arguments;

    public ReportOperation(Arguments arguments) {
        this.arguments = arguments;
    }


    @Override
    public void execute() {

        Loader loader = new MySQLLoaderServiceImpl();

        try {
            loader.report(arguments);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
