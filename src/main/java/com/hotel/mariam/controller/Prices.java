package com.hotel.mariam.controller;

import com.hotel.mariam.dao.RoomDAO;
import com.hotel.mariam.entity.Room;
import com.hotel.mariam.SessionHelper;
import com.hotel.mariam.model.RoomModel;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Prices extends HttpServlet {
    private RoomDAO roomDAO = new RoomModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Room> roomList = roomDAO.getDistinctRooms();
        req.setAttribute("roomList", roomList);
        SessionHelper.forward(req, resp, req.getRequestDispatcher("/jsps/prices.jsp"));
    }
}
