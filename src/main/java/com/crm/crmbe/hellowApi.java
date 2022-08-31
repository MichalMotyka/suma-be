package com.crm.crmbe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hellowApi {

    @GetMapping("/api/v1/adres/list")
    public String hello(){
        return "Hello";
    }
    @GetMapping("/api/v1/adres/view")
    public String hello2(){
        return "Hello";
    }
}
