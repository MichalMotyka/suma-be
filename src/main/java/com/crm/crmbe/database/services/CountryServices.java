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
    public void saveCountry(Country country){
        countryRepo.save(country);
    }
    public boolean isCountryExist(Country country){
        return countryRepo.findByNameAndPrefix(country.getName(),country.getPrefix()).isPresent();
    }
}
