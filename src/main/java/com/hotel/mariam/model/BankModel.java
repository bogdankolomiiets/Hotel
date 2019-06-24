package com.hotel.mariam.model;

import com.hotel.mariam.dao.BankDAO;
import com.hotel.mariam.entity.Bank;
import com.hotel.mariam.ConnectionProvider;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankModel implements BankDAO {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private void initConnectionAndStatement() throws SQLException {
        connection = ConnectionProvider.getConnection();
        statement = connection.createStatement();
    }

    private void closeConnection(){
        try {
            if (resultSet != null) resultSet.close();
            statement.close();
            statement = null;
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insertBank(Bank bank) {
        if (bank != null) {
            try {
                initConnectionAndStatement();
                int result = statement.executeUpdate("INSERT INTO bank (bankFullName, bankShortName, bankIdentifierCode, " +
                        "bankTaxpayerIdentificationNumber, bankCardNumber) VALUES ('" + bank.getBankFullName() + "', '" + bank.getBankShortName() + "', '" +
                        bank.getBankIdentifierCode() + "', '" + bank.getBankTaxpayerIdentificationNumber() + "', '" + bank.getBankCardNumber() + "')");
                if (result == 1) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }
        return false;
    }

    @Override
    public boolean deleteBankByBIC(int bankIdentifierCode) {
        try {
            initConnectionAndStatement();
            int result = statement.executeUpdate("DELETE FROM bank WHERE bankIdentifierCode = '" + bankIdentifierCode + "'");
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }

    @Override
    public Bank getBankById(int bankId) {
        Bank bank = new Bank();
        try {
            initConnectionAndStatement();
            resultSet = statement.executeQuery("SELECT * FROM bank WHERE bankId = '" + bankId + "'");
            while (resultSet.next()) {
                bank = extractBankFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
            return bank;
        }
    }

    @Override
    public Bank getBankByBIC(int bankIdentifierCode) {
        Bank bank = null;
        try {
            initConnectionAndStatement();
            resultSet = statement.executeQuery("SELECT * FROM bank WHERE bankIdentifierCode = '" + bankIdentifierCode + "'");
            while (resultSet.next()) {
                bank = extractBankFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
            return bank;
        }
    }

    private Bank extractBankFromResultSet(ResultSet rs){
        Bank bank = new Bank();
        try {
            bank.setBankId(rs.getInt("bankId"));
            bank.setBankFullName(rs.getString("bankFullName"));
            bank.setBankShortName(rs.getString("bankShortName"));
            bank.setBankIdentifierCode(rs.getInt("bankIdentifierCode"));
            bank.setBankTaxpayerIdentificationNumber(rs.getInt("bankTaxpayerIdentificationNumber"));
            bank.setBankCardNumber(rs.getString("bankCardNumber"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return bank;
        }
    }
}
