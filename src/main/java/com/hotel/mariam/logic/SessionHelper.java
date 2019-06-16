package com.hotel.mariam.logic;

import com.hotel.mariam.entity.Client;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class SessionHelper {
    private static int maxAgeValue = 60 * 60 * 24 * 7;

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

    public static void setClientValid(HttpServletRequest req, HttpServletResponse resp){
        Cookie cookie = new Cookie("user", "valid");
        cookie.setMaxAge(maxAgeValue);
        resp.addCookie(cookie);
    }

    public static boolean checkClientValid(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("user") && c.getValue().equals("valid")) {
                return true;
            }
        }
        return false;
    }

    public static void setCharacterEncoding(ServletRequest req, ServletResponse resp) {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void setClientEmail(Client client, HttpServletResponse resp) {
        Cookie cookie = new Cookie("clientEmail", client.getClientEmail());
        cookie.setMaxAge(maxAgeValue);
        resp.addCookie(cookie);
    }

    public static String getClientEmailFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String clientEmail;
            for (Cookie c : cookies) {
                if (c.getName().equals("clientEmail")) {
                    clientEmail = c.getValue();
                    return clientEmail;
                }
            }
        }
        return "";
    }
}
