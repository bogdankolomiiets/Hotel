package com.hotel.mariam.controller;

import com.hotel.mariam.SessionHelper;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //if user changes localization - put info to cookie
        SessionHelper.setNewLocalizationToCookie(req, resp);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
