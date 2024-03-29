package com.crm.crmbe.entity.response;

import com.crm.crmbe.database.services.ComponentService;
import com.crm.crmbe.entity.*;
import com.crm.crmbe.entity.mapper.PriceMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ObjectResponse {
    Gson gson = new Gson();

    public String CountryList(List<Country> countries) {
        return "{\"countries\":" + gson.toJson(countries) + "}";
    }

    public String StateList(List<Adres> adresList) {
        return "{\"adresys\":" + gson.toJson(adresList) + "}";
    }

    public String KontrahentList(List<Kontrahent> kontrahentList) {
        return "{\"kontrahentList\":" + gson.toJson(kontrahentList) + "}";
    }

    public String ComponentList(List<Component> componentList) {
        return "{\"componentList\":" + gson.toJson(componentList) + "}";
    }

    public String TariffList(List<Tariff> tariffList) {
        return "{\"tarifList\":" + gson.toJson(tariffList) + "}";
    }

    public String TariffJsonList(List<TariffJson> tariffJsonList) {
        return "{\"tarifList\":" + gson.toJson(tariffJsonList) + "}";
    }

    public String PriceJsonList(List<PriceDocument> prices) {
        return "{\"PriceList\":" + gson.toJson(prices) + "}";
    }


    public String MeterJsonList(List<Meter> meters) {
        return  "{\"meterList\":"+gson.toJson(meters)+"}";
    }

    public String PPJsonList(List<pp> pp){
        return "{\"ppList\":"+gson.toJson(pp)+"}";
    }
    public String ContractJsonList(List<Contract> contractList){
        return "{\"contractList\":"+gson.toJson(contractList)+"}";
    }
    public String OtJsonList(List<OT> otList){
        return "{\"otList\":"+gson.toJson(otList)+"}";
    }

    public String ReadingJsonList(List<Readings> readings){return "{\"readingList\":"+gson.toJson(readings)+"}";}
    public String ReadingsViewJsonList(List<ReadingsElement> readingsElements){
        return "{\"readingList\":"+gson.toJson(readingsElements)+"}";
    }

    public String UsersListJsonList(List<UserData> userData){
        return "{\"userList\":"+gson.toJson(userData)+"}";
    }

    public String RoleListJsonList(List<Role> roleList){return "{\"rolesList\":"+gson.toJson(roleList)+"}";}
}
