package com.crm.crmbe.rest.country;

import com.crm.crmbe.database.services.CountryServices;
import com.crm.crmbe.entity.Country;
import com.crm.crmbe.entity.response.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RestController
public class CountryController {

    @Autowired
    CountryServices countryServices;
    ObjectResponse countrytResponse = new ObjectResponse();

    @GetMapping("/api/v1/country/list")
    public String getAllCountry(HttpServletResponse response){
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
    @DeleteMapping("/api/v1/country/delete")
    public void deleteCountry(@RequestBody Country country,HttpServletResponse response) throws IOException {
        if(countryServices.isCountryExist(country)){
            countryServices.disableCountry(country);
            try {
                response.sendError(HttpServletResponse.SC_ACCEPTED,"Country has been dealted");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            response.sendError(HttpServletResponse.SC_CONFLICT,"Country dont exist");
        }
    }
    @GetMapping("/api/v1/country/get")
    public Country getCountry(@RequestParam String id,HttpServletResponse response) throws IOException {
        Country country = countryServices.getCountryByName(id);
        if (country != null){
            country.setGusMask(countryServices.translateGus(country.getGusMask()));
            country.setPostMask(countryServices.translateGus(country.getPostMask()));
            return country;
        }
        response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        return null;
    }

    public boolean createCountryIfNotExist(Country country){
        if (!countryServices.isCountryExist(country)){
            countryServices.saveCountry(country);
            return true;
        }
        return false;
    }
    @GetMapping("/api/v1/country/search")
    public String search(@RequestParam String name){
        return countrytResponse.CountryList(countryServices.search(name));
    }
}
