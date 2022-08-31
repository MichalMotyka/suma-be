package com.crm.crmbe.services.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookiController {


    public static void generateCookie(String key, String value, HttpServletResponse response){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(20000);
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
    }
    public static Cookie generateCookie(String key, String value){
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(20000);
        cookie.setHttpOnly(true);
        return cookie;
    }
    public static void deleteCookie(String key, HttpServletResponse response){
        Cookie cookie = new Cookie(key, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
    public static String findCookie(String key, Cookie[] cookies){
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(key)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
