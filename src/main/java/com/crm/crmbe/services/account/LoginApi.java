package com.crm.crmbe.services.account;

import com.crm.crmbe.database.services.UserServices;
import com.crm.crmbe.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
public class LoginApi {

    @Autowired
    private UserServices userServices;

    @PreAuthorize("permitAll()")
    @PutMapping("/login")
    public Cookie login(@RequestBody User user, HttpServletResponse response) {
        User.HashPassword(user);
        User copyUser = userServices.findByLoginAndPassword(user);
        if(copyUser != null) {
            Long currentTimeMillis =  System.currentTimeMillis();
            String token =  Jwts.builder()
                    .setSubject(user.getLogin())
                    .claim("roles",user.getPermission())
                    .setIssuedAt(new Date(currentTimeMillis))
                    .setExpiration(new Date(currentTimeMillis + 200000))
                    .signWith(SignatureAlgorithm.HS512,user.getPassword())
                    .compact();
            copyUser.setCurrentToken(token);
            userServices.save(copyUser);
            Cookie cookie = new Cookie("authorization",token);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(20000);
            response.addCookie(cookie);
            return cookie;
        }
       return null;
    }
}
