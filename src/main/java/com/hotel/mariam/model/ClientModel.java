package com.hotel.mariam.model;

import com.hotel.mariam.dao.ClientDAO;
import com.hotel.mariam.entity.Client;
import com.hotel.mariam.logic.ConnectionProvider;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientModel implements ClientDAO {
    private Connection connection = new ConnectionProvider().getConnection();

    @Override
    public Client getClientByEmail(String clientEmail) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM client WHERE clientEmail LIKE '" + clientEmail + "'");
            return extractClientFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client getClient(String clientEmail, String pass) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM client WHERE clientEmail = '" + clientEmail + "'");
            Client client = extractClientFromResultSet(rs);
            if (client != null) {
                if (BCrypt.checkpw(pass, client.getClientPass())) {
                    return client;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client getClientByPhone(String clientPhone) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM client WHERE clientPhone LIKE '" + clientPhone + "'");
            return extractClientFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> getClientByName(String clientName) {
        List<Client> clientList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM client WHERE clientName LIKE '" + clientName + "'");
            Client client;
            while ((client = extractClientFromResultSet(rs)) != null){
                clientList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    @Override
    public List<Client> getClientBySurname(String clientSurname) {
        List<Client> clientList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM client WHERE clientSurname LIKE '" + clientSurname + "'");
            Client client;
            while ((client = extractClientFromResultSet(rs)) != null){
                clientList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clientList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM client");
            Client client;
            while ((client = extractClientFromResultSet(rs)) != null){
                clientList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    @Override
    public boolean insertClient(Client client) throws SQLException{
            String sqlInsert = "INSERT INTO client (clientName, clientSurname, clientPhone, clientEmail, clientPass) " +
                    "VALUES ('" + client.getClientName() + "', '" + client.getClientSurname() + "', '"
                    + client.getClientPhone() + "', '" + client.getClientEmail() + "', '" + client.getClientPass() + "')";
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlInsert);
            if (result == 1) {
                return true;
            }
        return false;
    }

    @Override
    public boolean updateClientByPhone(Client client, String phone) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE client SET clientName=?, clientSurname=?," +
                    "clientPhone=?, clientEmail=?, clientPass=? WHERE clientPhone=?");
            statement.setString(1, client.getClientName());
            statement.setString(2, client.getClientSurname());
            statement.setString(3, client.getClientPhone());
            statement.setString(4, client.getClientEmail());
            statement.setString(5, client.getClientPass());
            statement.setString(6, phone);
            int result = statement.executeUpdate();
            if (result == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateClientByEmail(Client client, String email) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE client SET clientName=?, clientSurname=?," +
                    "clientPhone=?, clientEmail=?, clientPass=? WHERE clientEmail=?");
            statement.setString(1, client.getClientName());
            statement.setString(2, client.getClientSurname());
            statement.setString(3, client.getClientPhone());
            statement.setString(4, client.getClientEmail());
            statement.setString(5, client.getClientPass());
            statement.setString(6, email);
            int result = statement.executeUpdate();
            if (result == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteClientByPhone(String clientPhone) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("DELETE FROM client WHERE clientPhone = '" + clientPhone + "'");
            if (result == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteClientByEmail(String clientEmail) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("DELETE FROM client WHERE clientEmail = '" + clientEmail + "'");
            if (result == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Client extractClientFromResultSet(ResultSet resultSet){
        Client client = null;
        try {
            if (resultSet.next()){
                client = new Client();
                client.setClientId(resultSet.getInt("clientId"));
                client.setClientName(resultSet.getString("clientName"));
                client.setClientSurname(resultSet.getString("clientSurname"));
                client.setClientPhone(resultSet.getString("clientPhone"));
                client.setClientEmail(resultSet.getString("clientEmail"));
                client.setClientPass(resultSet.getString("clientPass"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return client;
    }
}
