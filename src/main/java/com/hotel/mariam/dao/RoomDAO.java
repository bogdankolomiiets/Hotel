package com.hotel.mariam.dao;

import com.hotel.mariam.entity.Room;
import com.hotel.mariam.entity.RoomTypes;
import java.util.Calendar;
import java.util.List;

public interface RoomDAO {
    Room getByRoomId(int roomId);
    List<Room> getByRoomType(RoomTypes roomTypes);
    boolean insertRoom(Room room);
    boolean updateRoom(Room room, int roomId);
    boolean deleteRoom(int roomId);
    boolean bookRoom(int roomId, Calendar roomStartDate, Calendar roomEndDate);
    List<Room> getAllRooms();
    List<Room> getAvailableRooms(RoomTypes types, Calendar availableDate);
    List<Room> getAvailableRooms(Calendar availableDate);
}
