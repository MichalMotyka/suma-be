package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.CountryRepo;
import com.crm.crmbe.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServices {

    @Autowired
    CountryRepo countryRepo;

    public List<Country> getAllCountry(){
        List<Country> countrylist = new ArrayList<>();
        countryRepo.findAll().forEach(s-> countrylist.add(s));
        return countrylist;
    }
    public void disableCountry(Country country){
        country.setActive("N");
        countryRepo.save(country);
    }
    public void saveCountry(Country country){
        countryRepo.save(country);
    }
    public boolean isCountryExist(Country country){
        return countryRepo.findByNameAndPrefixAndActive(country.getName(),country.getPrefix(),"T").isPresent();
    }
    public Country getCountryByName(String id){
        return countryRepo.findByNameAndActive(id,"T").orElse(null);
    }
    public String translateGus(String gus) {
        gus = gus.replaceAll("[1-9]", "0");
        gus = gus.replaceAll("[a-zA-Z]", "A");
        String regex = "";
        for (String s : gus.split("")) {
            if (s.equals("0")) {
                regex += "[0-9]{1}";
            } else if (s.equals("A")) {
                regex += "[a-zA-Z]{1}";
            } else {
                regex += "[" + s + "]";
            }
        }
        return regex;
    }

    public List<Country> search(String name) {
       return (List<Country>) countryRepo.findByNameOrPrefix(name,name);
    }
}
