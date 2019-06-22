package com.hotel.mariam.controller;

import com.hotel.mariam.dao.ClientDAO;
import com.hotel.mariam.dao.PaymentDAO;
import com.hotel.mariam.dao.QueryDAO;
import com.hotel.mariam.dao.RoomDAO;
import com.hotel.mariam.entity.Query;
import com.hotel.mariam.constants.QueryStatus;
import com.hotel.mariam.SessionHelper;
import com.hotel.mariam.model.ClientModel;
import com.hotel.mariam.model.PaymentModel;
import com.hotel.mariam.model.QueryModel;
import com.hotel.mariam.model.RoomModel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Process extends HttpServlet {
    private QueryDAO queryDAO = new QueryModel();
    private RoomDAO roomDAO = new RoomModel();
    private ClientDAO clientDAO = new ClientModel();
    private PaymentDAO paymentDAO = new PaymentModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer queryId = Integer.parseInt(req.getSession().getAttribute("queryId").toString());
        Query query = queryDAO.getQueryById(queryId);
        req.setAttribute("availableRooms", roomDAO.getAvailableRooms(
                query.getRoomType(),
                query.getRoomLevel(),
                query.getRoomStartDate()));
        req.setAttribute("query", query);
        req.setAttribute("payments", paymentDAO.getNotPaidClientsPayment(clientDAO.getClientByEmail(query.getClientEmail()).getClientId()));
        SessionHelper.forward(req, resp, req.getRequestDispatcher("/jsps/process.jsp"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("roomNo") != null && req.getParameter("queryId") != null) {
            try {
                Query query = queryDAO.getQueryById(Integer.parseInt(req.getParameter("queryId")));
                if ((roomDAO.bookRoom(Integer.parseInt(req.getParameter("roomNo")), query.getRoomBookingDate(),
                        query.getRoomStartDate(), query.getRoomEndDate(), query.getQueryAmount(), query.getClientEmail())) == true) {
                    queryDAO.changeStatus(query.getQueryId(), QueryStatus.SUCCESS);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (req.getParameter("denyQueryId") != null){
            queryDAO.changeStatus(Integer.parseInt(req.getParameter("denyQueryId")), QueryStatus.DENY);
        }
        resp.sendRedirect("queriesPage");
    }
}
