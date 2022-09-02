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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RestController
public class LoginApi {

    JwtFilter jwtFilter = new JwtFilter();
    @Autowired
    private UserServices userServices;
    private final ResponsePropertiesLoader responsePropertiesLoader = new ResponsePropertiesLoader();

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response) throws IOException {
        User copyUser = userServices.findByLoginAndPassword(user.HashPassword());
        System.out.println("test");
        if(copyUser != null) {
            String token = jwtFilter.generateToken(copyUser);
            String refresgToken = jwtFilter.generateToken(copyUser);
            copyUser.setCurrentToken(token);
            copyUser.setRefreshToken(refresgToken);
            userServices.save(copyUser);
            response.addHeader("Set-Cookie","authorization="+token+"; sameSite=none;Secure;HttpOnly");
//            CookiController.generateCookie("authorization", token, response);
            CookiController.generateCookie("Refresh",refresgToken,response);
            System.out.println("zalogowano");
            return new Response(HttpServletResponse.SC_OK,responsePropertiesLoader.getLoginOK()).toString();
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, responsePropertiesLoader.getLoginUnauthorized());
        return null;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user, HttpServletResponse response){

        return null;
    }
}
