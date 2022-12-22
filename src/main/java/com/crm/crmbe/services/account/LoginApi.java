package com.crm.crmbe.services.account;

import com.crm.crmbe.database.services.UserServices;
import com.crm.crmbe.entity.response.Response;
//import com.crm.crmbe.security.jwt.JwtFilter;
import com.crm.crmbe.security.jwt.JwtFilter;
import com.crm.crmbe.services.utils.CookiController;
import com.crm.crmbe.entity.User;
import com.crm.crmbe.services.utils.properties.ResponsePropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RestController
@CrossOrigin("*")
public class LoginApi {

    JwtFilter jwtFilter = new JwtFilter();
    @Autowired
    private UserServices userServices;
    private final ResponsePropertiesLoader responsePropertiesLoader = new ResponsePropertiesLoader();

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response) throws IOException {
        User copyUser = userServices.findByLoginAndPassword(user.HashPassword());
        if(copyUser != null) {
            String token = jwtFilter.generateToken(copyUser);
            String refresgToken = jwtFilter.generateToken(copyUser);
            copyUser.setCurrentToken(token);
            copyUser.setRefreshToken(refresgToken);
            userServices.save(copyUser);
//            response.addHeader("Set-Cookie","authorization="+token+";sameSite=none;Secure=false;HttpOnly");
            CookiController.generateCookie("authorization", token, response);
//            CookiController.generateCookie("Refresh",refresgToken,response);
            return new Response(HttpServletResponse.SC_OK,responsePropertiesLoader.getLoginOK()).toString();
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, responsePropertiesLoader.getLoginUnauthorized());
        return null;
    }

    @GetMapping("/api/v1/logout")
    public void logout(HttpServletResponse response,HttpServletRequest request) throws IOException {
        Cookie [] cookies = request.getCookies();
        if (cookies != null){
            for (int x = 0; x < cookies.length; x++){
                cookies[x].setMaxAge(0);
                cookies[x].setDomain("localhost");
                cookies[x].setPath("/");
                response.addCookie(cookies[x]);
            }
        }
        response.sendError(HttpServletResponse.SC_OK);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user, HttpServletResponse response){

        return null;
    }
}
