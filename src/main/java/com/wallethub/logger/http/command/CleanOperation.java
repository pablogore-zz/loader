package com.wallethub.logger.http.command;

import com.wallethub.logger.http.Arguments;
import com.wallethub.logger.http.dao.LoaderDAO;
import com.wallethub.logger.http.dao.LoaderDAOImpl;

public class CleanOperation implements Command {


    @Override
    public void execute() {
        try {
            LoaderDAOImpl dao = new LoaderDAOImpl();
            dao.clean();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
