package com.ef.command;

import com.ef.dao.LoaderDAOImpl;

/**
 * This is the Clean operation command.
 */
public class CleanOperation implements Command {

    /**
     * Execute the current Command.
     */
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
