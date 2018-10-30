package com.ef.command;

import com.ef.dao.LoaderDAOImpl;

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
