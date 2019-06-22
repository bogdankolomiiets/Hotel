package com.hotel.mariam.model;

import com.hotel.mariam.constants.ClientRole;
import com.hotel.mariam.dao.ClientDAO;
import com.hotel.mariam.entity.Client;
import org.junit.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientModelTest extends Assert {
    private static Client client;
    private static ClientDAO clientDAO;

    @BeforeClass
    public static void init(){
        client = new Client("clientName", "clientSurname", "clientPhone", "clientEmail", "0000", ClientRole.USER);
        clientDAO = new ClientModel();
    }

    @AfterClass
    public static void destroy(){
        clientDAO.deleteClientByEmail(client.getClientEmail());
    }

    @Test
    @Ignore
    public void delete(){
        assertTrue(clientDAO.deleteClientByEmail(client.getClientEmail()));
    }

    @Test
    public void insertClientTest(){
        try {
            delete();
            assertTrue(clientDAO.insertClient(client));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = SQLException.class)
    public void insertSameClientTest() throws SQLException {
            assertFalse(clientDAO.insertClient(client));
    }

    @Test
    public void insertNullClientTest() throws SQLException {
        assertFalse(clientDAO.insertClient(null));
    }

    @Test
    public void getAllClientsTest() throws SQLException {
        clientDAO.deleteClientByEmail(client.getClientEmail());
        assertTrue(clientDAO.insertClient(client));
        List<Client> clientList = clientDAO.getAllClients();
        assertTrue(clientList.size() > 0);
    }

    @Test
    public void getClientByEmailTest() throws SQLException {
        Client newClient = new Client();
        newClient.setClientEmail("email");

        clientDAO.deleteClientByEmail(newClient.getClientEmail());
        clientDAO.insertClient(newClient);

        assertNotNull(clientDAO.getClientByEmail(newClient.getClientEmail()));
        clientDAO.deleteClientByEmail(newClient.getClientEmail());
    }

    @Test
    public void getClientByNameTest() throws SQLException {
        Client newClient = new Client();
        newClient.setClientName("Bogdan");
        newClient.setClientEmail("email");

        clientDAO.deleteClientByEmail(newClient.getClientEmail());
        clientDAO.insertClient(newClient);

        List<Client> clientList = clientDAO.getClientByName(newClient.getClientEmail());
        assertNotNull(clientList);
        clientList = clientDAO.getClientByName("");
        assertTrue(clientList.size() == 0);
        assertNotNull(clientList);
    }

    @Test
    public void getClientByPhoneTest() throws SQLException {
        Client newClient = new Client();
        newClient.setClientPhone("phone");
        newClient.setClientEmail("email");

        clientDAO.deleteClientByEmail(newClient.getClientEmail());
        clientDAO.insertClient(newClient);
        assertNotNull(clientDAO.getClientByPhone(newClient.getClientPhone()));
        assertNull(clientDAO.getClientByPhone(""));
    }

    @Test
    public void getClientBySurnameTest() throws SQLException {
        Client newClient = new Client();
        newClient.setClientSurname("Kolomiiets");
        newClient.setClientEmail("email");

        clientDAO.deleteClientByEmail(newClient.getClientEmail());
        clientDAO.insertClient(newClient);

        List<Client> clientList = clientDAO.getClientBySurname(newClient.getClientSurname());
        assertNotNull(clientList);
        assertTrue(clientList.size() > 0);
        clientList = clientDAO.getClientBySurname("");
        assertNotNull(clientList);
        assertTrue(clientList.size() == 0);
    }

    @Test
    public void updateClientByPhoneTest(){
        try {
            clientDAO.deleteClientByEmail(client.getClientEmail());
            clientDAO.insertClient(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertTrue(clientDAO.updateClientByPhone(client, client.getClientPhone()));
        assertFalse(clientDAO.updateClientByPhone(null, client.getClientPhone()));
    }

    @Test
    public void updateClientByEmailTest(){
        try {
            clientDAO.deleteClientByEmail(client.getClientEmail());
            clientDAO.insertClient(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertTrue(clientDAO.updateClientByEmail(client, client.getClientEmail()));
        assertFalse(clientDAO.updateClientByEmail(null, client.getClientEmail()));
    }

    @Test
    public void deleteClientByPhoneTest(){
        Client client = new Client();
        client.setClientPhone("none");
        client.setClientEmail("email");
        try {
            clientDAO.deleteClientByEmail(client.getClientEmail());
            clientDAO.insertClient(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertTrue(clientDAO.deleteClientByPhone(client.getClientPhone()));
        assertFalse(clientDAO.deleteClientByPhone(client.getClientPhone()));
    }



}
