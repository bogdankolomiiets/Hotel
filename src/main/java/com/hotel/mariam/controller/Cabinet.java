package com.hotel.mariam.controller;

import com.hotel.mariam.logic.RoomQueryQueue;
import com.hotel.mariam.logic.SessionHelper;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Cabinet extends HttpServlet {
    private RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clientQueries", RoomQueryQueue.getQueriesByClientEmail(SessionHelper.getClientEmailFromCookie(req)));
        dispatcher = req.getRequestDispatcher("/jsps/cabinet.jsp");
        SessionHelper.forward(req, resp, dispatcher);
    }
}
