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
}
