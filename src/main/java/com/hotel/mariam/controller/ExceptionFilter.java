package com.hotel.mariam.controller;

import org.apache.log4j.Logger;
import javax.servlet.*;
import java.io.IOException;

public class ExceptionFilter implements Filter {
    private static Logger LOGGER = Logger.getLogger(ExceptionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            //getting element where an exception occurred
            for (StackTraceElement element : e.getStackTrace()) {
                LOGGER.error(element);
            }
            throw e;
        }
    }

    @Override
    public void destroy() {

    }
}
