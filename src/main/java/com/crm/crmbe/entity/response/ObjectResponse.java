package com.crm.crmbe.entity.response;

import com.crm.crmbe.entity.Adres;
import com.crm.crmbe.entity.Country;
import com.google.gson.Gson;

import java.util.List;

public class ObjectResponse {
    Gson gson = new Gson();
    public String CountryList(List<Country> countries){
        return "{\"countries\":"+gson.toJson(countries)+"}";
    }
    public String StateList(List<Adres> adresList){
        return  "{\"adresys\":"+gson.toJson(adresList)+"}";
    }

}
