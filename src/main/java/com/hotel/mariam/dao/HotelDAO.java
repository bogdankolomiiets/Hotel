package com.hotel.mariam.dao;

import com.hotel.mariam.entity.Hotel;
import java.util.List;

public interface HotelDAO {
    Hotel getHotelById(int hotelId);
    List<Hotel> getHotelByName(String hotelName);
    boolean insertHotel(Hotel hotel);
    boolean updateHotel(Hotel hotel, String hotelName);
    boolean deleteHotel(String hotelName);
    List<Hotel> getAll();
}
