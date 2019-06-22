package com.hotel.mariam.model;

import com.hotel.mariam.constants.PaymentStatus;
import com.hotel.mariam.dao.PaymentDAO;
import com.hotel.mariam.entity.Payment;
import com.hotel.mariam.ConnectionProvider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentModel implements PaymentDAO {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @Override
    public boolean insertPayment(Payment payment) {
        if (payment != null) {
            try {
                connection = new ConnectionProvider().getConnection();
                statement = connection.createStatement();
                int result = statement.executeUpdate("INSERT INTO payment (paymentBankId, paymentAmount, paymentClientId, paymentStatus) " +
                        "VALUES ('" + payment.getPaymentBankId() + "', '" + payment.getPaymentAmount() + "', '"
                        + payment.getPaymentClientId() + "', '" + payment.getPaymentStatus() + "')");
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
    public boolean changePaymentStatus(int paymentId, int newPaymentStatus) {
        try {
            connection = new ConnectionProvider().getConnection();
            statement = connection.createStatement();
            int result = statement.executeUpdate("UPDATE payment SET paymentStatus = " + newPaymentStatus
                    + " WHERE paymentId = " + paymentId);
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Payment> getNotPaidClientsPayment(int clientId) {
        List<Payment> paymentList = new ArrayList<>();
        try {
            connection = new ConnectionProvider().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM payment where paymentClientId = " + clientId + " AND paymentStatus = " + PaymentStatus.NOT_PAID);
            Payment payment;
            while ((payment = extractPaymentFromResultSet(resultSet)) != null){
                paymentList.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentList;
    }

    private Payment extractPaymentFromResultSet(ResultSet rs) {
        Payment payment = null;
        try {
            if (rs.next()) {
                payment = new Payment();
                payment.setPaymentId(rs.getInt("paymentId"));
                payment.setPaymentBankId(rs.getInt("paymentBankId"));
                payment.setPaymentAmount(rs.getInt("paymentAmount"));
                payment.setPaymentClientId(rs.getInt("paymentClientId"));
                payment.setPaymentStatus(rs.getInt("paymentStatus"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }
}
