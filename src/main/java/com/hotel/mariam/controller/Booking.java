package com.hotel.mariam.controller;

import com.hotel.mariam.constants.QueryStatus;
import com.hotel.mariam.constants.RoomLevel;
import com.hotel.mariam.constants.RoomType;
import com.hotel.mariam.dao.QueryDAO;
import com.hotel.mariam.dao.RoomDAO;
import com.hotel.mariam.entity.*;
import com.hotel.mariam.SessionHelper;
import com.hotel.mariam.model.QueryModel;
import com.hotel.mariam.model.RoomModel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Booking extends HttpServlet {
    private RequestDispatcher dispatcher;
    private QueryDAO queryDAO = new QueryModel();
    private RoomDAO roomDAO = new RoomModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("typesFromServer", roomDAO.getRoomTypes());
        //if user logged in - than book.jsp else login.jsp
        if (!SessionHelper.checkClientValid(req)){
            resp.sendRedirect("login");
        } else {
            dispatcher = req.getRequestDispatcher("/jsps/book.jsp");
            SessionHelper.forward(req, resp, dispatcher);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeFromJSP = req.getParameter("selectedType");
        String levelFromJSP = req.getParameter("selectedLevel");
        if (req.getParameter("submitViaButton").equals("0")){
            req.setAttribute("previousStartDate", req.getParameter("StartDate"));
            req.setAttribute("previousEndDate", req.getParameter("EndDate"));
            req.setAttribute("previousType", typeFromJSP);
            req.setAttribute("levelsFromServer", roomDAO.getRoomLevelsByType(RoomType.valueOf(typeFromJSP)));


            if (req.getParameter("selectedLevel") != null) {
                req.setAttribute("previousLevel", levelFromJSP);
                RoomType type = RoomType.valueOf(typeFromJSP);
                RoomLevel level = RoomLevel.valueOf(req.getParameter("selectedLevel"));
                double roomPrice = roomDAO.getRoomPrice(type, level);

                if (Integer.parseInt(req.getParameter("countOfDays")) > 0){
                    req.setAttribute("amount", (Integer.parseInt(req.getParameter("countOfDays")) * roomPrice));
                }
                req.setAttribute("roomPrice", roomPrice);
            }
            doGet(req, resp);
        } else {
            try {
                queryDAO.insertQuery(new Query(
                        0,
                        RoomType.valueOf(req.getParameter("selectedType")),
                        RoomLevel.valueOf(req.getParameter("selectedLevel")),
                        Calendar.getInstance().getTime(),
                        new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("StartDate")),
                        new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("EndDate")),
                        Double.parseDouble(req.getParameter("amount")),
                        SessionHelper.getClientEmailFromCookie(req), QueryStatus.PROCESSING));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("cabinet");
        }
    }
}
