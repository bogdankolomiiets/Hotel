package com.hotel.mariam.controller;

import com.hotel.mariam.entity.Client;
import com.hotel.mariam.logic.SessionHelper;
import com.hotel.mariam.model.ClientModel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {
    private RequestDispatcher dispatcher;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        dispatcher = req.getRequestDispatcher("jsps/login.jsp");
        SessionHelper.forward(req, resp, dispatcher);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ClientModel model = new ClientModel();
        Client client;
        if ((client = model.getClient(req.getParameter("email"), req.getParameter("password"))) != null) {
            SessionHelper.setClientValid(req, resp);
            SessionHelper.setClientEmail(client, resp);
            //if the user role is higher than 0 redirect to adminPage
            resp.sendRedirect(client.getClientRole().getIntValue() > 0 ? "queriesPage" : "book");
        } else {
            req.setAttribute("userExists", "-1");
            doGet(req, resp);
        }
    }
}
