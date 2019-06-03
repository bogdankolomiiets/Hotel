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
        Hotel hotel = new Hotel();
        String sqlGetByHotelId = "SELECT * FROM hotel WHERE hotelID  = " + hotelId;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlGetByHotelId);
            hotel = extractHotelFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }

    public List<Hotel> getByHotelName(String hotelName) {
        List<Hotel> hotelList = new ArrayList<>();
        String sqlGetByHotelName = "SELECT * FROM hotel WHERE hotelName LIKE '" + hotelName + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlGetByHotelName);
            Hotel hotel;
            while ((hotel = extractHotelFromResultSet(rs)) != null){
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public boolean insertHotel(Hotel hotel) {
        try {
            String insertCommand = "INSERT INTO hotel (hotelName, hotelAddress, hotelPhone, " +
                    "hotelCountOfFloors, hotelCountOfStars) " +
                    "VALUES ('" + hotel.getName() + "', '" + hotel.getAddress() + "', '" + hotel.getPhone()
                    + "', '" + hotel.getCountOfFloors() + "', '" + hotel.getCountOfStars() + "')";
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(insertCommand);
            if (result == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateHotel(Hotel hotel, String hotelName) {

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE hotel SET hotelName=?, hotelAddress=?," +
                    "hotelPhone=?, hotelCountOfFloors=?, hotelCountOfStars=? WHERE hotelName =?");
            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getAddress());
            ps.setString(3, hotel.getPhone());
            ps.setInt(4, hotel.getCountOfFloors());
            ps.setInt(5, hotel.getCountOfStars());
            ps.setString(6, hotelName);
            int result = ps.executeUpdate();
            if (result > 0){
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
            int result = statement.executeUpdate("DELETE FROM hotel WHERE hotelName LIKE '" + hotelName + "'");
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Hotel> getAll() {
        List<Hotel> hotelList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM hotel");
            Hotel hotel;
            while ((hotel = extractHotelFromResultSet(rs)) != null) {
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    private Hotel extractHotelFromResultSet(ResultSet rs) {
        Hotel hotel = null;
        try {
            if (rs.next()) {
                hotel = new Hotel();
                hotel.setName(rs.getString("hotelName"));
                hotel.setAddress(rs.getString("hotelAddress"));
                hotel.setPhone(rs.getString("hotelPhone"));
                hotel.setCountOfFloors(rs.getInt("hotelCountOfFloors"));
                hotel.setCountOfStars(rs.getInt("hotelCountOfStars"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }
}
