package com.hotel.mariam.model;

import com.hotel.mariam.constants.QueryStatus;
import com.hotel.mariam.constants.RoomLevel;
import com.hotel.mariam.constants.RoomType;
import com.hotel.mariam.dao.QueryDAO;
import com.hotel.mariam.entity.Query;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class QueryModelTest extends Assert {
    public static Query query;
    public static QueryDAO queryDAO;

    @BeforeClass
    public static void init() {
        query = new Query(RoomType.KING, RoomLevel.DELUXE, new Date(), new Date(), new Date(), 0, "email", QueryStatus.PROCESSING);
        queryDAO = new QueryModel();
    }

    @AfterClass
    public static void destroy(){
        queryDAO.deleteQueryByEmail(query.getClientEmail());
        query = null;
        queryDAO = null;
    }

    @Test
    public void insertQueryTest() {
        assertTrue(queryDAO.insertQuery(query));
    }

    @Test
    public void insertNullQueryTest() {
        assertFalse(queryDAO.insertQuery(null));
    }

    @Test
    public void deleteQueryTest() {
        Query query = queryDAO.getAllQueries().get(0);
        assertTrue(queryDAO.deleteQuery(query.getQueryId()));
    }

    @Test
    public void changeStatusTest() {
        assertTrue(queryDAO.changeStatus(queryDAO.getAllQueries().get(0).getQueryId(), QueryStatus.SUCCESS));
        assertFalse(queryDAO.changeStatus(0, QueryStatus.SUCCESS));
    }

    @Test
    public void getQueryByIdTest(){
        queryDAO.insertQuery(query);
        Query query = queryDAO.getQueryById(queryDAO.getAllQueries().get(0).getQueryId());
        assertNotNull(query);
        query = queryDAO.getQueryById(-1);
        assertNull(query);
    }

    @Test
    public void getAllQueriesTest(){
        queryDAO.insertQuery(query);
        List<Query> queryList = queryDAO.getAllQueries();
        assertTrue(queryList.size() > 0);
    }

    @Test
    public void getClientsQueriesTest(){
        queryDAO.insertQuery(query);
        List<Query> queryList = queryDAO.getClientsQueries(query.getClientEmail());
        assertTrue(queryList.size() > 0);
        queryList = queryDAO.getClientsQueries("");
        assertFalse(queryList.size() > 0);
    }

    @Test
    public void deleteQueryByEmailTest(){
        queryDAO.insertQuery(query);
        assertTrue(queryDAO.deleteQueryByEmail(query.getClientEmail()));
        assertFalse(queryDAO.deleteQueryByEmail(""));
    }
}
