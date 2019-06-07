package com.hotel.mariam.logic;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class SessionHelper {

    public static void setNewLocalizationToCookie(HttpServletRequest req, HttpServletResponse resp){
        if (req.getParameter("language") != null) {
            Cookie cookie = new Cookie("language", req.getParameter("language"));
            cookie.setMaxAge(60 * 60 * 24 * 7);
            resp.addCookie(cookie);
        }
        setLocalization(req, resp);
    }

    private static void setLocalization(HttpServletRequest req, HttpServletResponse resp){
        //setting current localization
        Cookie[] cookie = req.getCookies();
        if (cookie != null){
            //setup default locale
            resp.setLocale(Locale.ENGLISH);

            for (Cookie c : cookie){
                if (c.getValue().equals("ua")) {
                    resp.setLocale(new Locale("uk", "UA"));
                }
            }
        }
    }

    public static void forward(HttpServletRequest req, HttpServletResponse resp, RequestDispatcher dispatcher) throws ServletException, IOException{
        //if user changed language than redirect else dispatcher.forward
        if(req.getParameter("language")!= null){
                resp.sendRedirect(req.getRequestURI());
        } else {
            dispatcher.forward(req, resp);
        }
    }
}
