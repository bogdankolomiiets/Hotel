package com.hotel.mariam.controller;

import com.hotel.mariam.entity.Hotel;
import com.hotel.mariam.logic.SessionHelper;
import com.hotel.mariam.model.HotelModel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private Hotel mariamHotel;
    private RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("hotel", mariamHotel);

        //if user changes localization - put info to cookie
         SessionHelper.setNewLocalizationToCookie(req, resp);

        dispatcher = req.getRequestDispatcher("/jsps/index.jsp");
        SessionHelper.forward(req, resp, dispatcher);
    }

    @Override
    public void init() throws ServletException {
        mariamHotel = new HotelModel().getByHotelName("Mariam").get(0);
    }
}
