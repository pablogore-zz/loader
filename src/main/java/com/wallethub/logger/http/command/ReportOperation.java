package com.wallethub.logger.http.command;

import com.wallethub.logger.http.Arguments;
import com.wallethub.logger.http.services.Loader;
import com.wallethub.logger.http.services.MySQLLoaderServiceImpl;

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
