package com.hotel.mariam.dao;

import com.hotel.mariam.constants.PaymentStatus;
import com.hotel.mariam.entity.Payment;

import java.util.List;

public interface PaymentDAO {
    boolean insertPayment(Payment payment);
    boolean changePaymentStatus(int paymentId, int newPaymentStatus);
    boolean deletePaymentByClientId(int clientId);
    List<Payment> getNotPaidClientsPayment(int clientId);
}
