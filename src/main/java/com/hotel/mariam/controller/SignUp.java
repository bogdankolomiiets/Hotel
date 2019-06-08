package com.hotel.mariam.controller;

import com.hotel.mariam.entity.Client;
import com.hotel.mariam.entity.Hotel;
import com.hotel.mariam.logic.SessionHelper;
import com.hotel.mariam.model.ClientModel;
import com.hotel.mariam.model.HotelModel;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class SignUp extends HttpServlet {
    private RequestDispatcher dispatcher;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionHelper.setCharacterEncoding(req, resp);
        Hotel mariamHotel = new HotelModel().getByHotelName("Mariam").get(0);
        req.setAttribute("hotel", mariamHotel);

        //if user changes localization - put info to cookie
        SessionHelper.setNewLocalizationToCookie(req, resp);

        dispatcher = req.getRequestDispatcher("jsps/signup.jsp");
        SessionHelper.forward(req, resp, dispatcher);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionHelper.setCharacterEncoding(req, resp);

        Client client = new Client(req.getParameter("clientName"), req.getParameter("clientSurname"),
                req.getParameter("clientPhone"), req.getParameter("clientEmail"), req.getParameter("clientPass"));
        ClientModel model = new ClientModel();
        try {
            if (model.insertClient(client) == true){
                req.setAttribute("registerInfo", "1");
            } else {
                req.setAttribute("registerInfo", "0");
            }
        } catch (SQLException e) {
            req.setAttribute("registerInfo", "-1");
        }

        doGet(req, resp);
    }
}
