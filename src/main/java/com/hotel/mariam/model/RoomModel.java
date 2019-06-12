package com.hotel.mariam.model;

import com.hotel.mariam.dao.RoomDAO;
import com.hotel.mariam.entity.RoomLevel;
import com.hotel.mariam.entity.RoomType;
import com.hotel.mariam.entity.Room;
import com.hotel.mariam.logic.ConnectionProvider;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class RoomModel implements RoomDAO {

    @Override
    public Room getRoomByNumber(int roomNumber) {
        try {
            Statement statement = new ConnectionProvider().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM room where roomNumber = '" + roomNumber + "'");
            return extractHotelFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Room> getRoomByType(RoomType roomType) {
        List<Room> roomList = new ArrayList<>();
        try {
            Statement statement = new ConnectionProvider().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM room where roomTypeId = '" + roomType.getIntValue() + "'");
            Room room;
            while ((room = extractHotelFromResultSet(rs)) != null){
                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public static List<RoomType> getRoomTypes() {
        List<RoomType> types = new ArrayList<>();
        try {
            Statement statement = new ConnectionProvider().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT distinct roomTypeId FROM room");
            while (rs.next()) {
                types.add(RoomModel.getRoomTypeFromIntValue(rs.getInt("roomTypeId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    public static List<RoomLevel> getRoomLevelsByType(RoomType roomType) {
        List<RoomLevel> levels = new ArrayList<>();
        try {
            Statement statement = new ConnectionProvider().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT distinct roomLevelId FROM room WHERE roomTypeId = '" + roomType.getIntValue() + "'");
            while (rs.next()) {
                levels.add(RoomModel.getRoomLevelFromIntValue(rs.getInt("roomLevelId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;
    }

    @Override
    public List<Room> getRoomByTypeAndLevel(RoomType roomType, RoomLevel roomLevel) {
        List<Room> roomList = new ArrayList<>();
        try {
            Statement statement = new ConnectionProvider().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM room where roomTypeId = '" + roomType.getIntValue()
                    + "' AND roomLevelId = '" + roomLevel + "'");
            Room room;
            while ((room = extractHotelFromResultSet(rs)) != null){
                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    @Override
    public boolean insertRoom(Room room) {
        try {
            Statement statement = new ConnectionProvider().getConnection().createStatement();
            int result = statement.executeUpdate("INSERT INTO room VALUES ('" + room.getRoomNumber() + "', '"
                    + room.getRoomType().getIntValue() + "', '" + room.getRoomLevel().getIntValue() + "', '"
                    + room.getRoomPrice() + "', '" + toTimestamp(room.getRoomBookingDate()) + "', '" + toSQLDate(room.getRoomStartDate()) + "', '"
                    + toSQLDate(room.getRoomEndDate()) + "', '" + room.getHotelID() + "', '" + room.getClientID() + "')");
            if (result == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateRoom(Room room, int roomNumber) {
        try {
            PreparedStatement statement = new ConnectionProvider().getConnection().prepareStatement("UPDATE room SET roomNumber=?, roomTypeId=?," +
                    "roomLevelId=?, roomPrice=?, roomBookingDate=?, roomStartDate=?, roomEndDate=?, hotelID=?," +
                    "clientID=? WHERE roomNumber=?");
            statement.setInt(1, room.getRoomNumber());
            statement.setInt(2, room.getRoomType().getIntValue());
            statement.setInt(3, room.getRoomLevel().getIntValue());
            statement.setDouble(4, room.getRoomPrice());
            statement.setTimestamp(5, toTimestamp(room.getRoomBookingDate()));
            statement.setDate(6, toSQLDate(room.getRoomStartDate()));
            statement.setDate(7, toSQLDate(room.getRoomEndDate()));
            statement.setInt(8, room.getHotelID());
            statement.setInt(9, room.getClientID());
            statement.setInt(10, roomNumber);
            int result = statement.executeUpdate();
            if (result == 1){
                return true;
            }        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int updatePrice(double oldPrice, double newPrice) {
        try {
            Statement statement = new ConnectionProvider().getConnection().createStatement();
            int result = statement.executeUpdate("UPDATE room SET roomPrice = '" + newPrice
                    + "' WHERE roomPrice = '" + oldPrice + "'");
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean deleteRoom(int roomNumber) {
        try {
            Statement statement = new ConnectionProvider().getConnection().createStatement();
            int result = statement.executeUpdate("DELETE FROM room WHERE roomNumber = '"
                    + roomNumber + "'");
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean bookRoom(int roomNumber, Calendar roomStartDate, Calendar roomEndDate) {

        return false;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> roomList = new ArrayList<>();
        try {
            Statement statement = new ConnectionProvider().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT roomNumber,\n" +
                    "       roomtype.roomTypeName as roomTypeId,\n" +
                    "       roomLevel.roomLevelName as roomLevelId,\n" +
                    "       roomPrice,\n" +
                    "       roomBookingDate,\n" +
                    "       roomStartDate,\n" +
                    "       roomEndDate,\n" +
                    "       hotelID,\n" +
                    "       clientID\n" +
                    "       FROM room " +
                    "join (roomtype on room.roomTypeId = roomtype.roomTypeId)" +
                    "join (roomlevel on room.roomLevelId = roomlevel.roomLevelID)");
            Room room;
            while ((room = extractHotelFromResultSet(rs)) != null) {
                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    private Room extractHotelFromResultSet(ResultSet rs) {
        Room room = null;
        try {
            if (rs.next()) {
                room = new Room();
                room.setRoomNumber(rs.getInt("roomNumber"));
                room.setRoomType(getRoomTypeFromIntValue(rs.getInt("roomTypeId")));
                room.setRoomLevel(getRoomLevelFromIntValue(rs.getInt("roomLevelId")));
                room.setRoomPrice(rs.getDouble("roomPrice"));
                room.setRoomBookingDate(rs.getDate("roomBookingDate"));
                room.setRoomStartDate(rs.getDate("roomStartDate"));
                room.setRoomEndDate(rs.getDate("roomEndDate"));
                room.setHotelID(rs.getInt("hotelID"));
                room.setClientID(rs.getInt("clientID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public List<Room> getAvailableRooms(RoomType roomType, Calendar availableDate) {
        List<Room> roomList = new ArrayList<>();
        try {
            Statement statement = new ConnectionProvider().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM room WHERE roomTypeId = '" + roomType.getIntValue() +
                    "' AND roomEndDate < '" + new SimpleDateFormat("yyyy-MM-dd").format(new Date(availableDate.getTimeInMillis()))
                    + "' OR roomEndDate IS null ");
            Room room;
            while ((room = extractHotelFromResultSet(rs)) != null){
                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    @Override
    public List<Room> getAvailableRooms(Calendar availableDate) {
        return null;
    }

    private static RoomType getRoomTypeFromIntValue(int intRoomType){
        for (RoomType r : RoomType.values()){
            if (r.getIntValue() == intRoomType){
                return r;
            }
        }
        return null;
    }

    private static RoomLevel getRoomLevelFromIntValue(int intRoomLevel){
        for (RoomLevel l : RoomLevel.values()){
            if (l.getIntValue() == intRoomLevel){
                return l;
            }
        }
        return null;
    }

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
/*
    public static void main(String[] args) {
        RoomModel model = new RoomModel();
        System.out.println(model.insertRoom(new Room(1, RoomType.SINGLE, RoomLevel.ECONOMY, 200, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(2, RoomType.SINGLE, RoomLevel.ECONOMY, 200, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(3, RoomType.SINGLE, RoomLevel.STANDARD, 350, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(4, RoomType.SINGLE, RoomLevel.STANDARD, 350, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(5, RoomType.DOUBLE, RoomLevel.ECONOMY, 450, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(6, RoomType.DOUBLE, RoomLevel.ECONOMY, 450, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(7, RoomType.DOUBLE, RoomLevel.STANDARD, 500, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(8, RoomType.DOUBLE, RoomLevel.STANDARD, 500, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(9, RoomType.DOUBLE, RoomLevel.STANDARD, 500, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(10, RoomType.DOUBLE, RoomLevel.IMPROVED, 600, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(11, RoomType.DOUBLE, RoomLevel.IMPROVED, 600, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(12, RoomType.DOUBLE, RoomLevel.IMPROVED, 600, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(13, RoomType.TRIPLE, RoomLevel.ECONOMY, 650, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(14, RoomType.TRIPLE, RoomLevel.STANDARD, 650, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(15, RoomType.TRIPLE, RoomLevel.IMPROVED, 650, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(16, RoomType.QUAD, RoomLevel.ECONOMY, 700, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(17, RoomType.QUAD, RoomLevel.ECONOMY, 700, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(18, RoomType.QUAD, RoomLevel.STANDARD, 750, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(19, RoomType.QUAD, RoomLevel.STANDARD, 750, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(20, RoomType.QUAD, RoomLevel.IMPROVED, 750, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(26, RoomType.KING, RoomLevel.IMPROVED, 1000, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(27, RoomType.KING, RoomLevel.DELUXE, 1000, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(28, RoomType.KING, RoomLevel.DELUXE, 1000, HotelModel.getHotelID("Mariam"))));
    }*/
}
