package com.crm.crmbe.services.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookiController {


    public static void generateCookie(String key, String value, HttpServletResponse response){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(20000);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
    public static Cookie generateCookie(String key, String value){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(20000);
        cookie.setHttpOnly(true);
        return cookie;
    }

}
