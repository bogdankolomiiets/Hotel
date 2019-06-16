package com.hotel.mariam.dao;

import com.hotel.mariam.entity.RoomLevel;
import com.hotel.mariam.entity.RoomType;
import com.hotel.mariam.entity.Room;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface RoomDAO {
    Room getRoomByNumber(int roomNumber);
    List<Room> getRoomByType(RoomType roomType);
    List<Room> getRoomByTypeAndLevel(RoomType roomType, RoomLevel roomLevel);
    boolean insertRoom(Room room);
    boolean updateRoom(Room room, int roomNumber);
    int updatePrice(double oldPrice, double newPrice);
    boolean deleteRoom(int roomNumber);
    boolean bookRoom(int roomNumber, Calendar roomStartDate, Calendar roomEndDate);
    List<Room> getAllRooms();
    List<Room> getAvailableRooms(RoomType roomType, Calendar availableDate);
    List<Room> getAvailableRooms(RoomType roomType, RoomLevel roomLevel, Date roomStartDate);
    List<Room> getDistinctRooms();
}
