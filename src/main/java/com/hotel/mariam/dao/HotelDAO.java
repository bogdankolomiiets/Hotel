package com.hotel.mariam.dao;

import com.hotel.mariam.entity.Hotel;
import java.util.List;

public interface HotelDAO {
    Hotel getByHotelId(int hotelId);
    List<Hotel> getByHotelName(String hotelName);
    boolean insertHotel(Hotel hotel);
    boolean updateHotel(Hotel hotel, String hotelName);
    boolean deleteHotel(String hotelName);
    List<Hotel> getAll();
}
