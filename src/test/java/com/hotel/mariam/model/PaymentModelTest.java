package com.hotel.mariam.model;

import com.hotel.mariam.constants.ClientRole;
import com.hotel.mariam.constants.PaymentStatus;
import com.hotel.mariam.dao.ClientDAO;
import com.hotel.mariam.dao.PaymentDAO;
import com.hotel.mariam.entity.Client;
import com.hotel.mariam.entity.Payment;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

public class PaymentModelTest extends Assert {
    public static Payment payment;
    public static PaymentDAO paymentDAO;
    public static ClientDAO clientDAO;
    public static Client client;
    public static int clientId;

    @BeforeClass
    public static void init() {
        clientDAO = new ClientModel();
        client = new Client("clientName", "clientSurname", "clientPhone", "clientEmail", "0000", ClientRole.USER);
        try {
            clientDAO.deleteClientByEmail(client.getClientEmail());
            clientDAO.insertClient(client);
            clientId = clientDAO.getClientByEmail(client.getClientEmail()).getClientId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        payment = new Payment(1, 1000.21, clientId, PaymentStatus.NOT_PAID);
        paymentDAO = new PaymentModel();
    }

    @AfterClass
    public static void destroy() {
        payment = null;
        paymentDAO = null;
        clientDAO = null;
        client = null;
    }

        @Test
    public void insertPaymentTest(){
        assertTrue(paymentDAO.insertPayment(payment));
    }

    @Test
    public void insertNullPaymentTest() {
        assertFalse(paymentDAO.insertPayment(null));
    }

    @Test
    public void changePaymentStatusTest(){
        insertPaymentTest();
        assertTrue(paymentDAO.changePaymentStatus(paymentDAO.getNotPaidClientsPayment(payment.getPaymentClientId()).get(0).getPaymentId(), PaymentStatus.PAID));
    }

    @Test
    public void getNotPaidClientsPaymentTest(){
        paymentDAO.insertPayment(payment);
        List<Payment> paymentList = paymentDAO.getNotPaidClientsPayment(clientId);
        assertTrue(paymentList.size() > 0);
    }
}
