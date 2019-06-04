package com.hotel.mariam.controller;

import com.hotel.mariam.entity.Hotel;
import com.hotel.mariam.model.HotelModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hotel mariamHotel = new HotelModel().getByHotelName("Mariam").get(0);
        req.setAttribute("hotel", mariamHotel);
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsps/signup.jsp");
        dispatcher.forward(req, resp);
    }
}