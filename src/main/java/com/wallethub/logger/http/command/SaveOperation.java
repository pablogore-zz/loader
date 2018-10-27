package com.wallethub.logger.http.command;

import com.wallethub.logger.http.Arguments;
import com.wallethub.logger.http.dao.LoaderDAOImpl;
import com.wallethub.logger.http.services.Loader;
import com.wallethub.logger.http.services.MySQLLoaderServiceImpl;

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
