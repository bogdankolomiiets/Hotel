package com.hotel.mariam.model;

import com.hotel.mariam.dao.RoomDAO;
import com.hotel.mariam.entity.Room;
import com.hotel.mariam.entity.RoomTypes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class RoomModel implements RoomDAO {
    @Override
    public Room getByRoomId(int roomId) {
        return null;
    }

    @Override
    public List<Room> getByRoomType(RoomTypes roomTypes) {
        return null;
    }

    @Override
    public boolean insertRoom(Room room) {
        return false;
    }

    @Override
    public boolean updateRoom(Room room, int roomId) {
        return false;
    }

    @Override
    public boolean deleteRoom(int roomId) {
        return false;
    }

    @Override
    public boolean bookRoom(int roomId, Calendar roomStartDate, Calendar roomEndDate) {
        return false;
    }

    @Override
    public List<Room> getAllRooms() {
        return null;
    }

    @Override
    public List<Room> getAvailableRooms(RoomTypes types, Calendar availableDate) {
        return null;
    }

    @Override
    public List<Room> getAvailableRooms(Calendar availableDate) {
        return null;
    }

    private Room extractRoomFromResultSet(ResultSet rs) {
        Room room = null;
        /*try {
            if (rs.next()) {
                room = new Room();
                room.setRoomType(rs.getString("roomType"));
                room.setRoomPrice(rs.getDouble("hotelAddress"));
                room.setRoomBookingDate(rs.getDate("hotelPhone"));
                room.setCountOfFloors(rs.getInt("hotelCountOfFloors"));
                room.setCountOfStars(rs.getInt("hotelCountOfStars"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return room;
    }

    public static void main(String[] args) {
        RoomModel model = new RoomModel();
        model.getAllRooms();
    }
}
