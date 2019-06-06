package com.hotel.mariam.logic;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckSession {
    public static boolean checkLoginStatus(HttpServletRequest req){
        if (req.getSession().getAttribute("clientName") == null){
            req.setAttribute("check_login", "<li><a href=\"/login\"><fmt:message key=\"header.menu.login\"/></a></li><li><a href=\"/signup\"><fmt:message key=\"header.menu.signup\"/></a></li>");
            return false;
        } else {
            req.setAttribute("check_login", "<li><a href=\"/logout\"><fmt:message key=\"header.menu.logout\"/></a></li>");
            return true;
        }
    }

    public static void setNewLocalizationToCookie(HttpServletRequest req, HttpServletResponse resp){
        if (req.getParameter("language") != null) {
            Cookie cookie = new Cookie("language", req.getParameter("language"));
            cookie.setMaxAge(60 * 60 * 24 * 7);
            resp.addCookie(cookie);
        }
    }
}
