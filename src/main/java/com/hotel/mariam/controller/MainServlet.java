package com.hotel.mariam.controller;

import com.hotel.mariam.SessionHelper;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher = req.getRequestDispatcher("/jsps/index.jsp");
        SessionHelper.forward(req, resp, dispatcher);
    }
}
