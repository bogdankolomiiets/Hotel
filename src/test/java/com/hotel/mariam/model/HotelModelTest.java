package com.hotel.mariam.model;

import com.hotel.mariam.dao.HotelDAO;
import com.hotel.mariam.entity.Hotel;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class HotelModelTest extends Assert {
    public static Hotel hotel;
    public static HotelDAO hotelDAO;

    @BeforeClass
    public static void init(){
        hotel = new Hotel("TestHotel", "Address", "phone", 5, 5);
        hotelDAO = new HotelModel();
    }

    @AfterClass
    public static void destroy(){
        hotelDAO.deleteHotel(hotel.getName());
    }

    @Test
    public void insertHotelTest(){
        assertTrue(hotelDAO.insertHotel(hotel));
    }

    @Test
    public void insertNullHotelTest(){
        assertFalse(hotelDAO.insertHotel(null));
    }

    @Test
    public void updateHotelTest(){
        insertHotelTest();
        Hotel hotelToUpdate = new Hotel("updatedHotel", "Address", "phone", 5, 5);
        assertTrue(hotelDAO.updateHotel(hotelToUpdate, "TestHotel"));
        assertFalse(hotelDAO.updateHotel(hotelToUpdate, "TestHotel"));
        hotelDAO.deleteHotel(hotelToUpdate.getName());
    }

    @Test
    public void getByHotelNameTest(){
        insertHotelTest();
        List<Hotel> hotelList = hotelDAO.getByHotelName(hotel.getName());
        assertTrue(hotelList.size() > 0);
        hotelList = hotelDAO.getByHotelName("");
        assertFalse(hotelList.size() > 0);
    }

    @Test
    public void deleteHotelTest(){
        insertHotelTest();
        insertHotelTest();
        assertTrue(hotelDAO.deleteHotel(hotel.getName()));
        assertFalse(hotelDAO.deleteHotel(hotel.getName()));
        assertFalse(hotelDAO.deleteHotel(null));
        assertFalse(hotelDAO.deleteHotel(""));
    }
}
