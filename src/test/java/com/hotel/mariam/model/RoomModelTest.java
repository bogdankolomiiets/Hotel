package com.hotel.mariam.model;

import com.hotel.mariam.constants.ClientRole;
import com.hotel.mariam.constants.RoomLevel;
import com.hotel.mariam.constants.RoomType;
import com.hotel.mariam.dao.ClientDAO;
import com.hotel.mariam.dao.HotelDAO;
import com.hotel.mariam.dao.RoomDAO;
import com.hotel.mariam.entity.Client;
import com.hotel.mariam.entity.Hotel;
import com.hotel.mariam.entity.Room;
import org.junit.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class RoomModelTest extends Assert {
    public static Room room;
    public static RoomDAO roomDAO;
    public static Hotel hotel;
    public static HotelDAO hotelDAO;
    public static Client client;
    public static ClientDAO clientDAO;


    @BeforeClass
    public static void init(){
        roomDAO = new RoomModel();
        clientDAO = new ClientModel();
        hotelDAO = new HotelModel();
        hotel = new Hotel("hotelName", "hotelAddress", "hotelPhone", 5, 5);
        hotelDAO.insertHotel(hotel);
        client = new Client("clientName", "clientSurname", "clientPhone", "clientEmail", "pass", ClientRole.USER);
        try {
            clientDAO.deleteClientByEmail(client.getClientEmail());
            clientDAO.insertClient(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        room = new Room(Integer.MAX_VALUE, RoomType.KING, RoomLevel.DELUXE, 5000,
                null, null, null, hotelDAO.getHotelByName(hotel.getName()).get(0).getHotelId(),  clientDAO.getClientByEmail(client.getClientEmail()).getClientId());
    }

    @AfterClass
    public static void destroy(){
        hotelDAO.deleteHotel(hotel.getName());
        clientDAO.deleteClientByEmail(client.getClientEmail());
        roomDAO.deleteRoom(room.getRoomNumber());
    }

    @Before
    public void beforeTests(){
        roomDAO.insertRoom(room);
    }

    @After
    public void afterTests(){
        roomDAO.deleteRoom(room.getRoomNumber());
    }

    @Test
    public void getRoomByNumberTest(){
        Room localRoom = roomDAO.getRoomByNumber(room.getRoomNumber());
        assertTrue(localRoom != null);
        localRoom = roomDAO.getRoomByNumber(-5);
        assertFalse(localRoom != null);
    }

    @Test
    public void getRoomByTypeTest(){
        List<Room> roomList;
        for (RoomType type : RoomType.values()) {
            roomList = roomDAO.getRoomByType(type);
            assertTrue(roomList.size() > 0);
        }
    }

    @Test
    public void getRoomByTypeAndLevelTest() {
        List<Room> roomList;
        roomList = roomDAO.getRoomByTypeAndLevel(RoomType.DOUBLE, RoomLevel.STANDARD);
        assertTrue(roomList.size() > 0);
        roomList = roomDAO.getRoomByTypeAndLevel(RoomType.DOUBLE, RoomLevel.DELUXE);
        assertFalse(roomList.size() > 0);
    }

    @Test
    public void insertRoomTest(){
        roomDAO.deleteRoom(room.getRoomNumber());
        assertTrue(roomDAO.insertRoom(room));
        assertFalse(roomDAO.insertRoom(null));
    }

    @Test
    public void updateRoomTest(){
        assertTrue(roomDAO.updateRoom(room, room.getRoomNumber()));
        assertFalse(roomDAO.updateRoom(null, room.getRoomNumber()));
    }

    @Test
    public void updatePriceTest(){
        assertTrue(roomDAO.updatePrice(room.getRoomPrice(), 10000.15));
        assertFalse(roomDAO.updatePrice(room.getRoomPrice(), 10000.15));
    }

    @Test
    public void deleteRoomTest(){
        assertTrue(roomDAO.deleteRoom(room.getRoomNumber()));
        assertFalse(roomDAO.deleteRoom(room.getRoomNumber()));
    }

    @Test
    public void bookRoomTest() throws SQLException{
        assertTrue(roomDAO.bookRoom(room.getRoomNumber(), new Date(), new Date(), new Date(), 1000, client.getClientEmail()));
        assertFalse(roomDAO.bookRoom(room.getRoomNumber(), null, new Date(), new Date(), 1000, client.getClientEmail()));
        assertFalse(roomDAO.bookRoom(room.getRoomNumber(), new Date(), null, new Date(), 1000, client.getClientEmail()));
        assertFalse(roomDAO.bookRoom(room.getRoomNumber(), new Date(), new Date(), null, 1000, client.getClientEmail()));
    }

    @Test
    public void getAllRoomsTest(){
        assertTrue(roomDAO.getAllRooms().size() > 0);
    }

    @Test
    public void getRoomPriceTest(){
        assertTrue(roomDAO.getRoomPrice(RoomType.SINGLE, RoomLevel.STANDARD) > 1);
        assertFalse(roomDAO.getRoomPrice(RoomType.SINGLE, RoomLevel.DELUXE) > 1);
    }

    @Test
    public void getAvailableRoomsTest(){
        assertTrue(roomDAO.getAvailableRooms(RoomType.SINGLE, RoomLevel.ECONOMY, new Date()).size() > 0);
    }

    @Test
    public void getDistinctRoomsTest(){
        assertTrue(roomDAO.getDistinctRooms().size() > 0);
    }
}
