package com.crm.crmbe.services.account;

import com.crm.crmbe.database.services.UserServices;
import com.crm.crmbe.entity.Response;
import com.crm.crmbe.security.jwt.JwtFilter;
import com.crm.crmbe.services.utils.CookiController;
import com.crm.crmbe.entity.User;
import com.crm.crmbe.services.utils.properties.ConfigurationPropertiesLoader;
import com.crm.crmbe.services.utils.properties.ResponsePropertiesLoader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class LoginApi {

    JwtFilter jwtFilter = new JwtFilter();
    @Autowired
    private UserServices userServices;
    private final ResponsePropertiesLoader responsePropertiesLoader = new ResponsePropertiesLoader();

    @PutMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response) {
        User copyUser = userServices.findByLoginAndPassword(user.HashPassword());
        if(copyUser != null) {
            String token = jwtFilter.generateToken(copyUser);
            copyUser.setCurrentToken(token);
            userServices.save(copyUser);
            CookiController.generateCookie("authorization", token, response);
            return new Response(HttpServletResponse.SC_OK,responsePropertiesLoader.getLoginOK()).toString();
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
       return new Response(response.getStatus(),responsePropertiesLoader.getLoginUnauthorized()).toString();
    }
}
