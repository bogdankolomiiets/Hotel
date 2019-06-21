package com.hotel.mariam.controller;

import com.hotel.mariam.dao.ClientDAO;
import com.hotel.mariam.entity.Client;
import com.hotel.mariam.constants.ClientRole;
import com.hotel.mariam.SessionHelper;
import com.hotel.mariam.model.ClientModel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignUp extends HttpServlet {
    private RequestDispatcher dispatcher;
    private ClientDAO clientDAO = new ClientModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        dispatcher = req.getRequestDispatcher("jsps/signup.jsp");
        SessionHelper.forward(req, resp, dispatcher);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client = new Client(req.getParameter("clientName"), req.getParameter("clientSurname"),
                req.getParameter("clientPhone"), req.getParameter("clientEmail"), req.getParameter("clientPass"), ClientRole.USER);
        try {
            if (clientDAO.insertClient(client) == true){
                req.setAttribute("registerInfo", "1");
            } else {
                req.setAttribute("registerInfo", "0");
            }
        } catch (SQLException e) {
            req.setAttribute("registerInfo", "-1");
        }

        doGet(req, resp);
    }
}
