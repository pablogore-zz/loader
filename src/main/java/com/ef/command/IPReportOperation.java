package com.ef.command;

import com.ef.Arguments;
import com.ef.services.Loader;
import com.ef.services.MySQLLoaderServiceImpl;

public class IPReportOperation implements Command {

    private final Arguments arguments;

    public IPReportOperation(Arguments arguments) {
        this.arguments = arguments;
    }


    @Override
    public void execute() {

        Loader loader = new MySQLLoaderServiceImpl();

        try {
            loader.filterByIP(arguments);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
