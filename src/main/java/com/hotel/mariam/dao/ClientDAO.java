package com.hotel.mariam.dao;

import com.hotel.mariam.entity.Client;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {
    Client getClient(String clientEmail, String pass);
    Client getClientByEmail(String clientEmail);
    Client getClientByPhone(String clientPhone);
    List<Client> getClientByName(String clientName);
    List<Client> getClientBySurname(String clientSurname);
    List<Client> getAllClients();
    boolean insertClient(Client client) throws SQLException;
    boolean updateClientByPhone(Client client, String phone);
    boolean updateClientByEmail(Client client, String email);
    boolean deleteClientByPhone(String clientPhone);
    boolean deleteClientByEmail(String clientEmail);
}
