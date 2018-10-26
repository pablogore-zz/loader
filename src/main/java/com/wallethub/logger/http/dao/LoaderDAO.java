package com.wallethub.logger.http.dao;

import com.wallethub.logger.http.dto.Line;

public interface LoaderDAO {
    public int save(Line line);
}
