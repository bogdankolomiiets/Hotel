package com.hotel.mariam.controller;

import com.hotel.mariam.dao.BankDAO;
import com.hotel.mariam.dao.ClientDAO;
import com.hotel.mariam.dao.PaymentDAO;
import com.hotel.mariam.dao.QueryDAO;
import com.hotel.mariam.entity.Client;
import com.hotel.mariam.entity.Payment;
import com.hotel.mariam.model.BankModel;
import com.hotel.mariam.model.ClientModel;
import com.hotel.mariam.model.PaymentModel;
import com.hotel.mariam.model.QueryModel;
import com.hotel.mariam.SessionHelper;
import org.apache.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Cabinet extends HttpServlet {
    private static Logger LOGGER = Logger.getLogger(Cabinet.class);
    private RequestDispatcher dispatcher;
    private ClientDAO clientDAO = new ClientModel();
    private QueryDAO queryDAO = new QueryModel();
    private PaymentDAO paymentDAO = new PaymentModel();
    private BankDAO bankDAO = new BankModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = clientDAO.getClientByEmail(SessionHelper.getClientEmailFromCookie(req));
        if (client.getClientRole().getIntValue() > 0){
            resp.sendRedirect("queriesPage");
        } else {
            req.setAttribute("clientQueries", queryDAO.getClientsQueries(SessionHelper.getClientEmailFromCookie(req)));
            List<Payment> paymentList = paymentDAO.getNotPaidClientsPayment(client.getClientId());
            if (paymentList.size() > 0) {
                req.setAttribute("clientName", client.getClientName());
                req.setAttribute("clientSurname", client.getClientSurname());
                req.setAttribute("bank", bankDAO.getBankById(paymentList.get(0).getPaymentBankId()));
                req.setAttribute("payments", paymentList);
            }
            dispatcher = req.getRequestDispatcher("/jsps/cabinet.jsp");
            SessionHelper.forward(req, resp, dispatcher);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("queryId") != null){
            int queryId = Integer.parseInt(req.getParameter("queryId"));
            LOGGER.info("user deleted query: " + queryDAO.getQueryById(queryId));
            queryDAO.deleteQuery(queryId);
            doGet(req, resp);
        }
        if (req.getParameter("payment") != null) {
            resp.sendRedirect("https://www.portmone.com.ua/r3/perekaz-dovilni-rekvizyty");
        }
    }
}
