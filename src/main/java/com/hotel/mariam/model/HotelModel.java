package com.hotel.mariam.model;

import com.hotel.mariam.dao.HotelDAO;
import com.hotel.mariam.entity.Hotel;
import com.hotel.mariam.logic.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelModel implements HotelDAO {
    public Connection connection = new ConnectionProvider().getConnection();

    public Hotel getByHotelId(int hotelId) {
        String sqlGetByHotelId = "SELECT * FROM hotel WHERE hotelID  = " + hotelId;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlGetByHotelId);
            return extractHotelFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Hotel getByHotelName(String hotelName) {
        String sqlGetByHotelName = "SELECT * FROM hotel WHERE hotelName = '" + hotelName + "'";
        System.out.println(sqlGetByHotelName);
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlGetByHotelName);
            return extractHotelFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertHotel(Hotel hotel) {
        try {
            String insertCommand = "INSERT INTO hotel (hotelName, hotelAddress, hotelPhone, " +
                                "hotelCountOfFloors, hotelCountOfSingleRooms, hotelCountOfDoubleRooms, " +
                                "hotelCountOfTripleRooms, hotelCountOfQuadRooms, hotelCountOfKingRooms) " +
                    "VALUES ('" + hotel.getName() + "', '" + hotel.getAddress() + "', '" + hotel.getPhone()
                    + "', '" + hotel.getCountOfFloors() + "', '" + hotel.getCountOfSingleRooms()
                    + "', '" + hotel.getCountOfDoubleRooms() + "', '" + hotel.getCountOfTripleRooms()
                    + "', '" + hotel.getCountOfQuadRooms() + "', '" + hotel.getCountOfKingRooms() + ")";
            Statement statement = connection.createStatement();
            return statement.execute(insertCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateHotel(Hotel hotel) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE hotel SET hotelName=?, hotelAddress=?," +
                    "hotelPhone=?, hotelCountOfFloors=?, hotelCountOfSingleRooms=?, hotelCountOfDoubleRooms=?, " +
                    "hotelCountOfTripleRooms=?, hotelCountOfQuadRooms=?, hotelCountOfKingRooms=? WHERE hotelName =?");
            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getAddress());
            ps.setString(3, hotel.getPhone());
            ps.setInt(4, hotel.getCountOfFloors());
            ps.setInt(5, hotel.getCountOfSingleRooms());
            ps.setInt(6, hotel.getCountOfDoubleRooms());
            ps.setInt(7, hotel.getCountOfTripleRooms());
            ps.setInt(8, hotel.getCountOfQuadRooms());
            ps.setInt(9, hotel.getCountOfKingRooms());
            int result = ps.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteHotel(String hotelName) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("DELETE FROM hotel WHERE hotelName = " + hotelName);
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Hotel> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM hotel");
            List<Hotel> hotelList = new ArrayList<Hotel>();
            while (rs.next()) {
                Hotel hotel = extractHotelFromResultSet(rs);
                hotelList.add(hotel);
            }
            return hotelList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Hotel extractHotelFromResultSet(ResultSet rs) {
        try {
            if (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setName(rs.getString("hotelName"));
                hotel.setAddress(rs.getString("hotelAddress"));
                hotel.setPhone(rs.getString("hotelPhone"));
                hotel.setCountOfFloors(rs.getInt("hotelCountOfFloors"));
                hotel.setCountOfSingleRooms(rs.getInt("hotelCountOfSingleRooms"));
                hotel.setCountOfDoubleRooms(rs.getInt("hotelCountOfDoubleRooms"));
                hotel.setCountOfTripleRooms(rs.getInt("hotelCountOfTripleRooms"));
                hotel.setCountOfQuadRooms(rs.getInt("hotelCountOfQuadRooms"));
                hotel.setCountOfKingRooms(rs.getInt("hotelCountOfKingRooms"));
                return hotel;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
