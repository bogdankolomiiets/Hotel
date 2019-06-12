package com.hotel.mariam.controller;

import com.hotel.mariam.entity.Hotel;
import com.hotel.mariam.entity.RoomType;
import com.hotel.mariam.logic.SessionHelper;
import com.hotel.mariam.model.HotelModel;
import com.hotel.mariam.model.RoomModel;
import org.apache.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Booking extends HttpServlet {
    private static Logger LOGGER = Logger.getLogger(Booking.class);
    RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionHelper.setCharacterEncoding(req, resp);
        Hotel mariamHotel = new HotelModel().getByHotelName("Mariam").get(0);
        req.setAttribute("hotel", mariamHotel);
        req.setAttribute("typesFromServer", RoomModel.getRoomTypes());

        //if user changes localization - put info to cookie
        SessionHelper.setNewLocalizationToCookie(req, resp);

        //if user logged in - than book.jsp else login.jsp
        if (!SessionHelper.checkClientValid(req)){
            resp.sendRedirect("/login");
        } else {
            dispatcher = req.getRequestDispatcher("/jsps/book.jsp");
            SessionHelper.forward(req, resp, dispatcher);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("submitViaButton"));
        String typeFromJSP = req.getParameter("selectedType");
        if (req.getParameter("submitViaButton").equals("0")){
            req.setAttribute("previousType", typeFromJSP);
            req.setAttribute("levelsFromServer", RoomModel.getRoomLevelsByType(RoomType.valueOf(typeFromJSP)));
            doGet(req, resp);
        } else {
            resp.sendRedirect("/cabinet");
        }
    }
}
