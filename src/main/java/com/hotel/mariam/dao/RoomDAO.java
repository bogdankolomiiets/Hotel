package com.hotel.mariam.dao;

import com.hotel.mariam.constants.RoomLevel;
import com.hotel.mariam.constants.RoomType;
import com.hotel.mariam.entity.Room;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface RoomDAO {
    Room getRoomByNumber(int roomNumber);
    List<Room> getRoomByType(RoomType roomType);
    List<Room> getRoomByTypeAndLevel(RoomType roomType, RoomLevel roomLevel);
    boolean insertRoom(Room room);
    boolean updateRoom(Room room, int roomNumber);
    boolean updatePrice(double oldPrice, double newPrice);
    boolean deleteRoom(int roomNumber);
    boolean bookRoom(int roomNumber, Date roomBookingDate, Date roomStartDate, Date roomEndDate, double amount, String clientEmail) throws SQLException;
    List<Room> getAllRooms();
    double getRoomPrice(RoomType roomType, RoomLevel roomLevel);
    List<Room> getAvailableRooms(RoomType roomType, RoomLevel roomLevel, Date roomStartDate);
    List<Room> getDistinctRooms();
}
