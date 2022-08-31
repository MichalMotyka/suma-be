package com.crm.crmbe.security.user.authentication;

import com.crm.crmbe.database.repository.UserRepo;
import com.crm.crmbe.database.services.UserServices;
import com.crm.crmbe.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;


@EnableWebSecurity
@Configuration
public class UserAuthentication{

//    @Autowired
//    UserServices userServices;
//
//    public void CreateUser(User user){
//        if(!userServices.findIsUserExist(user)){
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            userServices.save(user);
//        }
//    }
//
//    @Bean
//    public void config(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeRequests().antMatchers("").hasRole("");
//    }
}
