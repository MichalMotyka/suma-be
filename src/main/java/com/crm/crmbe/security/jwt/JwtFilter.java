package com.crm.crmbe.security.jwt;

import com.crm.crmbe.database.services.UserServices;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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

@Service
public class JwtFilter implements javax.servlet.Filter {

    private static UserServices userServices;

    @Autowired
    private void setUserServices(UserServices userServices) {
        JwtFilter.userServices = userServices;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authorization")) {
                    String token = cookie.getValue();
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
        }
    }
}
