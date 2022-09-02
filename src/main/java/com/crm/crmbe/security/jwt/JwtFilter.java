package com.crm.crmbe.security.jwt;

import com.crm.crmbe.database.services.PermissionSerices;
import com.crm.crmbe.database.services.UserServices;
import com.crm.crmbe.entity.PermisionList;
import com.crm.crmbe.entity.Permission;
import com.crm.crmbe.entity.User;
import com.crm.crmbe.services.utils.CookiController;
import com.crm.crmbe.services.utils.properties.ConfigurationPropertiesLoader;
import com.crm.crmbe.services.utils.properties.ResponsePropertiesLoader;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class JwtFilter implements javax.servlet.Filter {

    private static UserServices userServices;
    private static PermissionSerices permissionSerices;
    private final ConfigurationPropertiesLoader configurationPropertiesLoader = new ConfigurationPropertiesLoader();
    private final ResponsePropertiesLoader responsePropertiesLoader = new ResponsePropertiesLoader();

    @Autowired
    private void setUserServices(UserServices userServices,PermissionSerices permissionSerices) {
        JwtFilter.userServices = userServices;
        JwtFilter.permissionSerices = permissionSerices;
    }

    public String generateToken(User user) {
        long currentTimeMillis =  System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getLogin())
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
        String refresh = CookiController.findCookie("Refresh",cookies);
        if (token == null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, responsePropertiesLoader.getTokenEmpty());
        }else{
             Stream<PermisionList> permisionListStream = PermisionList
                     .getPermisionList()
                     .stream()
                     .filter(value-> value.getPermision()
                             .equals(((HttpServletRequest) servletRequest)
                             .getRequestURI()));
             String userid = JwtFilter.userServices.findByToken(token).getId();
//             try {
//                 JwtFilter.permissionSerices.findPermisionByUserIdAndPermision(userid,permisionListStream.findFirst().get());
//             }catch (NoSuchElementException e){
//                 response.sendError(HttpServletResponse.SC_UNAUTHORIZED,responsePropertiesLoader.getNoPermit());
//                 return;
//             }
            try {
                Claims claims = Jwts
                        .parser()
                        .setSigningKey(JwtFilter.userServices.findByToken(token).getPassword())
                        .parseClaimsJws(token)
                        .getBody();
                servletRequest.setAttribute("claims", claims);
            } catch (ExpiredJwtException e){
                try {
//                    Claims claims = Jwts
//                            .parser()
//                            .setSigningKey(JwtFilter.userServices.findByToken(token).getPassword())
//                            .parseClaimsJws(refresh)
//                            .getBody();
//                    servletRequest.setAttribute("claims", claims);
                }catch (ExpiredJwtException e1){
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, responsePropertiesLoader.getTokenExpired());
                }

            }catch (JwtException e){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, responsePropertiesLoader.getTokenInvalid());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
