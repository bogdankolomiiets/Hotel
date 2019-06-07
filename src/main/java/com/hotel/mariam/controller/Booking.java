package com.hotel.mariam.controller;

import com.hotel.mariam.entity.Hotel;
import com.hotel.mariam.logic.SessionHelper;
import com.hotel.mariam.model.HotelModel;
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
        Hotel mariamHotel = new HotelModel().getByHotelName("Mariam").get(0);
        req.setAttribute("hotel", mariamHotel);

        //if user changes localization - put info to cookie
        SessionHelper.setNewLocalizationToCookie(req, resp);

        dispatcher = req.getRequestDispatcher("/jsps/bookingPage.jsp");
        SessionHelper.forward(req, resp, dispatcher);
        /*if (!SessionHelper.checkLoginStatus(req)){
            dispatcher = req.getRequestDispatcher("jsps/login.jsp");
            dispatcher.forward(req, resp);
        } else {
            dispatcher = req.getRequestDispatcher("jsps/bookingPage.jsp");
            dispatcher.forward(req, resp);
        }*/

    }
}
