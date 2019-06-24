package com.hotel.mariam.model;

import com.hotel.mariam.dao.HotelDAO;
import com.hotel.mariam.entity.Hotel;
import com.hotel.mariam.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelModel implements HotelDAO {

    public Hotel getHotelById(int hotelId) {
        Hotel hotel = new Hotel();
        String sqlGetByHotelId = "SELECT * FROM hotel WHERE hotelID  = " + hotelId;
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlGetByHotelId)){
            hotel = extractHotelFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }

    public List<Hotel> getHotelByName(String hotelName) {
        List<Hotel> hotelList = new ArrayList<>();
        String sqlGetByHotelName = "SELECT * FROM hotel WHERE hotelName LIKE '" + hotelName + "'";
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlGetByHotelName)){
            Hotel hotel;
            while ((hotel = extractHotelFromResultSet(resultSet)) != null){
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public boolean insertHotel(Hotel hotel) {
        if (hotel != null) {
            String insertCommand = "INSERT INTO hotel (hotelName, hotelAddress, hotelPhone, " +
                "hotelCountOfFloors, hotelCountOfStars) " +
                "VALUES ('" + hotel.getName() + "', '" + hotel.getAddress() + "', '" + hotel.getPhone()
                + "', '" + hotel.getCountOfFloors() + "', '" + hotel.getCountOfStars() + "')";
            try (Connection connection = ConnectionProvider.getConnection();
                 Statement statement = connection.createStatement()){
                int result = statement.executeUpdate(insertCommand);
                if (result == 1) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updateHotel(Hotel hotel, String hotelName) {
        if (hotel != null) {
            try (Connection connection = ConnectionProvider.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("UPDATE hotel SET hotelName=?, hotelAddress=?," +
                        "hotelPhone=?, hotelCountOfFloors=?, hotelCountOfStars=? WHERE hotelName =?")){
                preparedStatement.setString(1, hotel.getName());
                preparedStatement.setString(2, hotel.getAddress());
                preparedStatement.setString(3, hotel.getPhone());
                preparedStatement.setInt(4, hotel.getCountOfFloors());
                preparedStatement.setInt(5, hotel.getCountOfStars());
                preparedStatement.setString(6, hotelName);
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean deleteHotel(String hotelName) {
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement()){
            int result = statement.executeUpdate("DELETE FROM hotel WHERE hotelName LIKE '" + hotelName + "'");
            if (result > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Hotel> getAll() {
        List<Hotel> hotelList = new ArrayList<>();
        try (Connection connection = ConnectionProvider.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM hotel")){
            Hotel hotel;
            while ((hotel = extractHotelFromResultSet(resultSet)) != null) {
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public static int getHotelID(String hotelName) {
        try {
            Statement statement = ConnectionProvider.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM hotel WHERE hotelName = '" + hotelName + "'");
            if (rs.next()){
                return rs.getInt("hotelID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int getHotelID(String hotelName, String hotelAddress) {
        try {
            Statement statement = ConnectionProvider.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM hotel WHERE hotelName = '" + hotelName
                    + "' AND hotelAddress LIKE '" + hotelAddress + "'");
            if (rs.next()){
                return rs.getInt("hotelID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private Hotel extractHotelFromResultSet(ResultSet rs) {
        Hotel hotel = null;
        try {
            if (rs.next()) {
                hotel = new Hotel();
                hotel.setHotelId(rs.getInt("hotelID"));
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
