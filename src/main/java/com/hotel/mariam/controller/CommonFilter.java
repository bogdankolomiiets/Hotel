package com.hotel.mariam.controller;

import com.hotel.mariam.dao.HotelDAO;
import com.hotel.mariam.entity.Hotel;
import com.hotel.mariam.SessionHelper;
import com.hotel.mariam.model.HotelModel;
import javax.servlet.*;
import java.io.IOException;

public class CommonFilter implements Filter {
    private Hotel mariamHotel;
    private HotelDAO hotelDAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        hotelDAO = new HotelModel();
        mariamHotel = hotelDAO.getHotelByName("Mariam").get(0);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //set object Hotel to all request
        request.setAttribute("hotel", mariamHotel);
        //set character encoding "UTF-8"
        SessionHelper.setCharacterEncoding(request, response);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        mariamHotel = null;
    }
}
