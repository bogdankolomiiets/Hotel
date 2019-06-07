package com.hotel.mariam.dao;

import com.hotel.mariam.entity.Client;

import java.util.List;

public interface ClientDAO {
    Client getClient(String clientEmail, String pass);
    Client getClientByEmail(String clientEmail);
    Client getClientByPhone(String clientPhone);
    List<Client> getClientByName(String clientName);
    List<Client> getClientBySurname(String clientSurname);
    List<Client> getAllClients();
    boolean insertClient(Client client);
    boolean updateClientByPhone(Client client, String phone);
    boolean updateClientByEmail(Client client, String email);
    boolean deleteClientByPhone(String clientPhone);
    boolean deleteClientByEmail(String clientEmail);
}
