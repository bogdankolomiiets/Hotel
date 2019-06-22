package com.hotel.mariam.model;

import com.hotel.mariam.dao.ClientDAO;
import com.hotel.mariam.entity.Client;
import com.hotel.mariam.constants.ClientRole;
import com.hotel.mariam.ConnectionProvider;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientModel implements ClientDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public Client getClientByEmail(String clientEmail) {
        try {
            connection = new ConnectionProvider().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM client WHERE clientEmail LIKE '" + clientEmail + "'");
            return extractClientFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client getClient(String clientEmail, String pass) {
        try {
            connection = new ConnectionProvider().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM client WHERE clientEmail = '" + clientEmail + "'");
            Client client = extractClientFromResultSet(resultSet);
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
            connection = new ConnectionProvider().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM client WHERE clientPhone LIKE '" + clientPhone + "'");
            return extractClientFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> getClientByName(String clientName) {
        List<Client> clientList = new ArrayList<>();
        try {
            connection = new ConnectionProvider().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM client WHERE clientName LIKE '" + clientName + "'");
            Client client;
            while ((client = extractClientFromResultSet(resultSet)) != null){
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
            connection = new ConnectionProvider().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM client WHERE clientSurname LIKE '" + clientSurname + "'");
            Client client;
            while ((client = extractClientFromResultSet(resultSet)) != null){
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
            connection = new ConnectionProvider().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM client");
            Client client;
            while ((client = extractClientFromResultSet(resultSet)) != null){
                clientList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    @Override
    public boolean insertClient(Client client) throws SQLException {
        if (client != null) {
            connection = new ConnectionProvider().getConnection();
            String sqlInsert = "INSERT INTO client (clientName, clientSurname, clientPhone, clientEmail, clientPass, clientRole) " +
                    "VALUES ('" + client.getClientName() + "', '" + client.getClientSurname() + "', '"
                    + client.getClientPhone() + "', '" + client.getClientEmail() + "', '" + client.getClientPass() + "', '" + client.getClientRole().getIntValue() + "')";
            statement = connection.createStatement();
            int result = statement.executeUpdate(sqlInsert);
            if (result == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateClientByPhone(Client client, String phone) {
        if (client != null) {
            try {
                connection = new ConnectionProvider().getConnection();
                preparedStatement = connection.prepareStatement("UPDATE client SET clientName=?, clientSurname=?," +
                        "clientPhone=?, clientEmail=?, clientPass=?, clientRole=? WHERE clientPhone=?");
                preparedStatement.setString(1, client.getClientName());
                preparedStatement.setString(2, client.getClientSurname());
                preparedStatement.setString(3, client.getClientPhone());
                preparedStatement.setString(4, client.getClientEmail());
                preparedStatement.setString(5, client.getClientPass());
                preparedStatement.setInt(6, client.getClientRole().getIntValue());
                preparedStatement.setString(7, phone);
                int result = preparedStatement.executeUpdate();
                if (result == 1) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateClientByEmail(Client client, String email) {
        if (client != null) {
            try {
                connection = new ConnectionProvider().getConnection();
                preparedStatement = connection.prepareStatement("UPDATE client SET clientName=?, clientSurname=?," +
                        "clientPhone=?, clientEmail=?, clientPass=?, clientRole=? WHERE clientEmail=?");
                preparedStatement.setString(1, client.getClientName());
                preparedStatement.setString(2, client.getClientSurname());
                preparedStatement.setString(3, client.getClientPhone());
                preparedStatement.setString(4, client.getClientEmail());
                preparedStatement.setString(5, client.getClientPass());
                preparedStatement.setInt(6, client.getClientRole().getIntValue());
                preparedStatement.setString(7, email);
                int result = preparedStatement.executeUpdate();
                if (result == 1) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteClientByPhone(String clientPhone) {
        try {
            connection = new ConnectionProvider().getConnection();
            statement = connection.createStatement();
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
            connection = new ConnectionProvider().getConnection();
            statement = connection.createStatement();
            int result = statement.executeUpdate("DELETE FROM client WHERE clientEmail = '" + clientEmail + "'");
            if (result == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Client extractClientFromResultSet(ResultSet rs){
        Client client = null;
        try {
            if (rs.next()){
                client = new Client();
                client.setClientId(rs.getInt("clientId"));
                client.setClientName(rs.getString("clientName"));
                client.setClientSurname(rs.getString("clientSurname"));
                client.setClientPhone(rs.getString("clientPhone"));
                client.setClientEmail(rs.getString("clientEmail"));
                client.setClientPass(rs.getString("clientPass"));
                client.setClientRole(ClientModel.getClientRoleFromIntValue(rs.getInt("clientRole")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return client;
    }

    private static ClientRole getClientRoleFromIntValue(int intClientRole){
        for (ClientRole r : ClientRole.values()){
            if (r.getIntValue() == intClientRole){
                return r;
            }
        }
        return null;
    }
}
