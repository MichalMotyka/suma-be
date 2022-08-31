package com.crm.crmbe.entity.response;

import com.crm.crmbe.entity.Country;
import com.google.gson.Gson;

import java.util.List;

public class CountrytResponse {
    Gson gson = new Gson();
    public String CountryList(List<Country> countries){
        return "{\"countries\":"+gson.toJson(countries)+"}";
    }

}
