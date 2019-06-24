package com.hotel.mariam.model;

import com.hotel.mariam.dao.QueryDAO;
import com.hotel.mariam.entity.Query;
import com.hotel.mariam.constants.QueryStatus;
import com.hotel.mariam.constants.RoomLevel;
import com.hotel.mariam.constants.RoomType;
import com.hotel.mariam.ConnectionProvider;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryModel implements QueryDAO {

    @Override
    public Query getQueryById(int queryId) {
        Query query = null;
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select queryId, roomtype.roomTypeName as queryRoomType, roomlevel.roomLevelName as queryRoomLevel, queryRoomBookingDate, queryRomStartDate, queryRoomEndDate, queryAmount, queryClientEmail, queryStatus FROM query\n" +
                    "join roomtype on query.queryRoomType = roomtype.roomTypeId\n" +
                    "join roomlevel on query.queryRoomLevel = roomlevel.roomLevelID WHERE queryId = '" + queryId + "'")){
            query = extractQueryFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    @Override
    public boolean insertQuery(Query query) {
        if (query != null) {
            try (Connection connection = ConnectionProvider.getConnection();
                 Statement statement = connection.createStatement()){
                int result = statement.executeUpdate("INSERT INTO query (queryRoomType, queryRoomLevel, queryRoomBookingDate, queryRomStartDate, " +
                        "queryRoomEndDate, queryAmount, queryClientEmail, queryStatus) VALUES ('" + query.getRoomType().getIntValue() + "', '" + query.getRoomLevel().getIntValue() + "', '"
                        + toTimestamp(query.getRoomBookingDate()) + "', '" + toSQLDate(query.getRoomStartDate()) + "', '" + toSQLDate(query.getRoomEndDate()) +
                        "', '" + query.getQueryAmount() + "', '" + query.getClientEmail() + "', '" + query.getQueryStatus() + "')");
                if (result == 1) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean changeStatus(int queryId, int queryStatus) {
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement()){
            int result = statement.executeUpdate("UPDATE query SET queryStatus = '" + queryStatus
                    + "' WHERE queryId = '" + queryId + "'");
            if (result == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteQuery(int queryId) {
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement()){
            int result = statement.executeUpdate("DELETE FROM query WHERE queryId = '" + queryId + "'");
            if (result == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteQueryByEmail(String clientEmail) {
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement()){
            int result = statement.executeUpdate("DELETE FROM query WHERE queryClientEmail = '" + clientEmail + "'");
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Query> getAllQueries() {
        List<Query> queryList = new ArrayList<>();
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select queryId, roomtype.roomTypeName as queryRoomType, roomlevel.roomLevelName as queryRoomLevel, " +
                    "queryRoomBookingDate, queryRomStartDate, queryRoomEndDate, queryAmount, queryClientEmail, queryStatus FROM query\n" +
                    "join roomtype on query.queryRoomType = roomtype.roomTypeId\n" +
                    "join roomlevel on query.queryRoomLevel = roomlevel.roomLevelID WHERE queryStatus = " + QueryStatus.PROCESSING)){
            Query query;
            while ((query = extractQueryFromResultSet(resultSet)) != null){
                queryList.add(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryList;
    }

    @Override
    public List<Query> getClientsQueries(String clientEmail) {
        List<Query> queryList = new ArrayList<>();
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select queryId, roomtype.roomTypeName as queryRoomType, roomlevel.roomLevelName as queryRoomLevel, " +
                    "queryRoomBookingDate, queryRomStartDate, queryRoomEndDate, queryAmount, queryClientEmail, queryStatus FROM query\n" +
                    "join roomtype on query.queryRoomType = roomtype.roomTypeId\n" +
                    "join roomlevel on query.queryRoomLevel = roomlevel.roomLevelID WHERE queryClientEmail ='" + clientEmail + "' AND queryStatus < " + QueryStatus.SUCCESS)){
            Query query;
            while ((query = extractQueryFromResultSet(resultSet)) != null){
                queryList.add(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryList;    }

    private Timestamp toTimestamp(Date date){
        if (date != null) {
            return java.sql.Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        } else return Timestamp.valueOf("1900-01-01 00:00:00");
    }

    private java.sql.Date toSQLDate(Date date) {
        if (date != null) {
            return java.sql.Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(date));
        } else return java.sql.Date.valueOf("1900-01-01");
    }

    private Query extractQueryFromResultSet(ResultSet rs) {
        Query query = null;
        try {
            if (rs.next()) {
                query = new Query();
                query.setQueryId(rs.getInt("queryId"));
                query.setRoomType(RoomType.valueOf(rs.getString("queryRoomType")));
                query.setRoomLevel(RoomLevel.valueOf(rs.getString("queryRoomLevel")));
                query.setRoomBookingDate(rs.getTimestamp("queryRoomBookingDate"));
                query.setRoomStartDate(rs.getDate("queryRomStartDate"));
                query.setRoomEndDate(rs.getDate("queryRoomEndDate"));
                query.setQueryAmount(rs.getDouble("queryAmount"));
                query.setClientEmail(rs.getString("queryClientEmail"));
                query.setQueryStatus(rs.getInt("queryStatus"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}
