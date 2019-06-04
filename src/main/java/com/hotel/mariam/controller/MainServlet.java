package com.hotel.mariam.controller;

import com.hotel.mariam.entity.Hotel;
import com.hotel.mariam.model.HotelModel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hotel mariamHotel = new HotelModel().getByHotelName("Mariam").get(0);
        req.setAttribute("hotel", mariamHotel);
//        if (req.getSession().getAttribute("clientName") == null){
//            req.setAttribute("imgPath", imgPath);
//        } else req.setAttribute("login_out", "<a href=\"./login\">Login</a><a href=\"./signup\">Sign Up</a>");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsps/index.jsp");
        dispatcher.forward(req, resp);
    }
}
