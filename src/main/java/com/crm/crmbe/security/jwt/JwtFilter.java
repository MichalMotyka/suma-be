package com.crm.crmbe.security.jwt;

import com.crm.crmbe.database.services.UserServices;
import com.crm.crmbe.entity.User;
import com.crm.crmbe.services.utils.CookiController;
import com.crm.crmbe.services.utils.properties.ConfigurationPropertiesLoader;
import com.crm.crmbe.services.utils.properties.ResponsePropertiesLoader;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
public class JwtFilter implements javax.servlet.Filter {

    private static UserServices userServices;
    private final ConfigurationPropertiesLoader configurationPropertiesLoader = new ConfigurationPropertiesLoader();

    @Autowired
    private void setUserServices(UserServices userServices) {
        JwtFilter.userServices = userServices;
    }

    public String generateToken(User user) {
        long currentTimeMillis =  System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getLogin())
                .claim("roles",user.getPermission())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + configurationPropertiesLoader.getTokenExperience()))
                .signWith(SignatureAlgorithm.HS512,user.getPassword())
                .compact();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = CookiController.findCookie("authorization", cookies);
        if (token == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }else{
            Claims claims = Jwts
                    .parser()
                    .setSigningKey(JwtFilter.userServices.findByToken(token).getPassword())
                    .parseClaimsJws(token)
                    .getBody();
            servletRequest.setAttribute("claims", claims);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
