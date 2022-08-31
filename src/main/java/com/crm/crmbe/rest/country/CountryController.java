package com.crm.crmbe.rest.country;

import com.crm.crmbe.database.services.CountryServices;
import com.crm.crmbe.entity.Country;
import com.crm.crmbe.entity.response.CountrytResponse;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@CrossOrigin("*")
@RestController
public class CountryController {

    @Autowired
    CountryServices countryServices;
    CountrytResponse countrytResponse = new CountrytResponse();
    @GetMapping("/api/v1/country/list")
    public String getAllCountry(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin","*");
        return countrytResponse.CountryList(countryServices.getAllCountry());
    }
    @PutMapping("/api/v1/country/create")
    public void createCountry(@RequestBody Country country,HttpServletResponse response){
        if (createCountryIfNotExist(country)){
            try {
                response.sendError(HttpServletResponse.SC_CREATED,"Country was succesfully created");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            response.sendError(HttpServletResponse.SC_OK,"Country cannot be created");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createCountryIfNotExist(Country country){
        if (!countryServices.isCountryExist(country)){
            countryServices.saveCountry(country);
            return true;
        }
        return false;
    }
}
