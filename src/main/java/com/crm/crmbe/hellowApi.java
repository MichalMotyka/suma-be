package com.crm.crmbe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hellowApi {

    @GetMapping("api/hello")
    public String hello(){
        return "Hello";
    }
}
