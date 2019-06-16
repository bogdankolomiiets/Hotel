package com.hotel.mariam.controller;

import com.hotel.mariam.entity.Query;
import com.hotel.mariam.model.ClientModel;
import com.hotel.mariam.model.QueryModel;
import com.hotel.mariam.logic.SessionHelper;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Cabinet extends HttpServlet {
    private static Logger LOGGER = Logger.getLogger(Cabinet.class);
    private RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clientQueries", new QueryModel().getClientsQueries(SessionHelper.getClientEmailFromCookie(req)));

        if (new ClientModel().getClientByEmail(SessionHelper.getClientEmailFromCookie(req)).getClientRole().getIntValue() > 0){
            resp.sendRedirect("queriesPage");
        } else {
            dispatcher = req.getRequestDispatcher("/jsps/cabinet.jsp");
            SessionHelper.forward(req, resp, dispatcher);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("queryId") != null){
            int queryId = Integer.parseInt(req.getParameter("queryId"));
            LOGGER.info("user deleted query: " + new QueryModel().getQueryById(queryId));
            new QueryModel().deleteQuery(queryId);
            doGet(req, resp);
        }
    }
}
