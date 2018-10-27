package com.wallethub.logger.http.operation;

import com.wallethub.logger.http.dao.LoaderDAO;
import com.wallethub.logger.http.dao.LoaderDAOImpl;

/**
 * Reciver
 */
public interface Action {
    void clean() throws Exception ;
    void usage() throws Exception ;
}
