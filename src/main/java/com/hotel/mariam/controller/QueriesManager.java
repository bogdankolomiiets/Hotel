package com.hotel.mariam.controller;

import com.hotel.mariam.dao.QueryDAO;
import com.hotel.mariam.SessionHelper;
import com.hotel.mariam.model.QueryModel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class QueriesManager extends HttpServlet {
    private RequestDispatcher dispatcher;
    private QueryDAO queryDAO = new QueryModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clientQueries", queryDAO.getAllQueries());
        dispatcher = req.getRequestDispatcher("/jsps/queriesPage.jsp");
        SessionHelper.forward(req, resp, dispatcher);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("queryId") != null){
            HttpSession session = req.getSession(false);
            session.setAttribute("queryId", req.getParameter("queryId"));
            resp.sendRedirect("process");
        }
    }
}
