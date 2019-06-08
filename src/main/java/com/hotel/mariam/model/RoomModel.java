package com.hotel.mariam.model;

import com.hotel.mariam.dao.RoomDAO;
import com.hotel.mariam.entity.Room;
import com.hotel.mariam.entity.RoomTypes;
import com.hotel.mariam.logic.ConnectionProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RoomModel implements RoomDAO {
    private static Connection connection = new ConnectionProvider().getConnection();

    @Override
    public Room getRoomByNumber(int roomNumber) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM room where roomNumber = '" + roomNumber + "'");
            return extractHotelFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Room> getRoomByType(RoomTypes roomTypes) {
        List<Room> roomList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM room where roomTypeId = '" + roomTypes.getIntValue() + "'");
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
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("INSERT INTO room (roomNumber, roomTypeId, roomPrice, hotelID)" +
                    " VALUES ('" + room.getRoomNumber() + "', '" + room.getRoomType().getIntValue() + "', '" +
                    room.getRoomPrice() + "', '" + room.getHotelID() + "')");
            if (result == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateRoom(Room room, int roomId) {
        return false;
    }

    @Override
    public int updatePrice(double oldPrice, double newPrice) {
        try {
            Statement statement = connection.createStatement();
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
            Statement statement = connection.createStatement();
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT roomNumber,\n" +
                    "       roomtype.roomTypeName as roomTypeId,\n" +
                    "       roomPrice,\n" +
                    "       roomBookingDate,\n" +
                    "       roomStartDate,\n" +
                    "       roomEndDate,\n" +
                    "       hotelID,\n" +
                    "       clientID\n" +
                    "       FROM room join roomtype on room.roomTypeId = roomtype.roomTypeId");
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

    /*
    *
    * /*roomTypeId = '" + roomTypes.getIntValue() + "'" +
                    "AND roomEndDate
    *
    * */

    @Override
    public List<Room> getAvailableRooms(RoomTypes roomTypes, Calendar availableDate) {
        List<Room> roomList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM room WHERE roomTypeId = '" + roomTypes.getIntValue() +
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

    private RoomTypes getRoomTypeFromIntValue(int intRoomType){
        for (RoomTypes r : RoomTypes.values()){
            if (r.getIntValue() == intRoomType){
                return r;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        RoomModel model = new RoomModel();
//        System.out.println(model.updatePrice(1000.20, 1000));
        List<Room> list = model.getAvailableRooms(RoomTypes.SINGLE, Calendar.getInstance());
        for (Room r : list){
            System.out.println(r);
        }


        /*System.out.println(model.insertRoom(new Room(1, RoomTypes.SINGLE, 400, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(2, RoomTypes.SINGLE, 400, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(3, RoomTypes.SINGLE, 400, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(4, RoomTypes.SINGLE, 400, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(5, RoomTypes.DOUBLE, 550, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(6, RoomTypes.DOUBLE, 550, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(7, RoomTypes.DOUBLE, 550, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(8, RoomTypes.DOUBLE, 550, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(9, RoomTypes.DOUBLE, 550, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(10, RoomTypes.DOUBLE, 550, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(11, RoomTypes.DOUBLE, 550, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(12, RoomTypes.DOUBLE, 550, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(13, RoomTypes.TRIPLE, 600, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(14, RoomTypes.TRIPLE, 600, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(15, RoomTypes.TRIPLE, 600, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(16, RoomTypes.QUAD, 700, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(17, RoomTypes.QUAD, 700, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(18, RoomTypes.QUAD, 700, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(19, RoomTypes.QUAD, 700, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(20, RoomTypes.QUAD, 700, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(21, RoomTypes.QUEEN, 800, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(22, RoomTypes.QUEEN, 800, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(23, RoomTypes.QUEEN, 800, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(24, RoomTypes.QUEEN, 800, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(25, RoomTypes.QUEEN, 800, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(26, RoomTypes.KING, 1000, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(27, RoomTypes.KING, 1000, HotelModel.getHotelID("Mariam"))));
        System.out.println(model.insertRoom(new Room(28, RoomTypes.KING, 1000, HotelModel.getHotelID("Mariam"))));*/
    }
}
