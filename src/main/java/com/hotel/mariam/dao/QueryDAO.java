package com.hotel.mariam.dao;

import com.hotel.mariam.entity.Query;
import java.util.List;

public interface QueryDAO {
    boolean insertQuery(Query query);
    boolean deleteQuery(int queryId);
    boolean deleteQueryByEmail(String clientEmail);
    boolean changeStatus(int queryId, int queryStatus);
    Query getQueryById(int queryId);
    List<Query> getAllQueries();
    List<Query> getClientsQueries(String clientEmail);
}
