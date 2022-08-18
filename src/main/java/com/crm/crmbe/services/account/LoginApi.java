package com.crm.crmbe.services.account;

import com.crm.crmbe.database.services.UserServices;
import com.crm.crmbe.services.utils.CookiController;
import com.crm.crmbe.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @SneakyThrows
    @PreAuthorize("permitAll()")
    @PutMapping("/login")
    public void login(@RequestBody User user, HttpServletResponse response) {
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
            CookiController.generateCookie("authorization", token, response);
            return;
        }
       response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
