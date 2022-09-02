package com.crm.crmbe;

//import com.crm.crmbe.security.jwt.JwtFilter;
import com.crm.crmbe.security.jwt.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import javax.sql.DataSource;
import java.util.Collections;
@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class CrmBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmBeApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.setUrlPatterns(Collections.singleton("/api/v1/*"));
        return registrationBean;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers().addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin","http://localhost:4200"));
        httpSecurity.headers().addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials","true"));
//        httpSecurity.headers().addHeaderWriter(new StaticHeadersWriter("Access-Control-Expose-Headers","Set-Cookie"));
//        httpSecurity.headers().addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers","true"));
//        httpSecurity.antMatcher("/api/v1/*").headers().addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin","http://localhost:4200"));
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }

}
