package com.hotel.mariam.dao;

import com.hotel.mariam.entity.Room;
import com.hotel.mariam.entity.RoomTypes;
import java.util.Calendar;
import java.util.List;

public interface RoomDAO {
    Room getRoomByNumber(int roomNumber);
    List<Room> getRoomByType(RoomTypes roomTypes);
    boolean insertRoom(Room room);
    boolean updateRoom(Room room, int roomId);
    int updatePrice(double oldPrice, double newPrice);
    boolean deleteRoom(int roomNumber);
    boolean bookRoom(int roomNumber, Calendar roomStartDate, Calendar roomEndDate);
    List<Room> getAllRooms();
    List<Room> getAvailableRooms(RoomTypes roomTypes, Calendar availableDate);
    List<Room> getAvailableRooms(Calendar availableDate);
}
