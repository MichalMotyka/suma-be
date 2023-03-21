package com.crm.crmbe.entity;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum PermisionList {
    //adresFull("/api/v1/adres/**"),
    adresList("/api/v1/adres/get_state"),
    adresView("/api/v1/adres/view"),
    adresEdit("/api/v1/adres/edit"),
    adresCreate("/api/v1/adres/create"),
    adresDeleteState("/api/v1/adres/delete_state"),
    adresGetByGus("/api/v1/adres/get_adres"),
    adresGetAllNoState("/api/v1/adres/get_all_no_state"),
    adresGetAllNoHistoric("/api/v1/adres/get_all_no_state/historic"),
    adresGetById("/api/v1/adres/get_by_id"),
    componentGetAll("/api/v1/component/get_all"),
    componentSave("/api/v1/component/save"),
    componentDelete("/api/v1/component/delete"),
    componentGet("/api/v1/component/get_by"),
    contractCreate("/api/v1/contract/create"),
    contractGetByPP("/api/v1/contract/getByPP"),
    contractGetById("/api/v1/contract/getById"),
    contractGetByUid("/api/v1/contract/getByUid"),
    contractDelete("/api/v1/contract/delete"),
    contractEdit("/api/v1/contract/edit"),
    contractGetAll("/api/v1/contract/getAll"),
    contractActivation("/api/v1/contract/activate"),
    contractGetByContractor("/api/v1/contract/get_by_contractor"),
    countryList("/api/v1/country/list"),
    countryCreate("/api/v1/country/create"),
    countryDelete("/api/v1/country/delete"),
    contractorSave("/api/v1/kontrahent/save"),
    contractorList("/api/v1/kontrahent/list"),
    contractorGet("/api/v1/kontrahent/get"),
    contractorDelete("/api/v1/kontrahent/delete"),
    contractorGetUid("/api/v1/kontrahent/get_uid"),
    meterCreate("/api/v1/meter/create"),
    meterGet("/api/v1/meter/get"),
    meterGetByID("/api/v1/meter/get_id"),
    meterGetUid("/api/v1/meter/get_uid"),
    meterRemove("/api/v1/meter/remove"),
    otGetAll("/api/v1/ot/get_all"),
    otDelete("/api/v1/ot/delete"),
    otCreate("/api/v1/ot/create"),
    otEdit("/api/v1/ot/edit"),
    otVeryfy("/api/v1/ot/veryfy"),
    ppCreate("/api/v1/pp/create"),
    ppGet("/api/v1/pp/get"),
    ppGetByUid("/api/v1/pp/getByUid"),
    ppGetByID("/api/v1/pp/getById"),
    priceGetAll("/api/v1/price/get_all"),
    priceSave("/api/v1/price/save"),
    priceDelete("/api/v1/price/delete"),
    priceGetByUid("/api/v1/price/get_by"),
    priceGetById("/api/v1/price/get_by_id"),
    readingGetAll("/api/v1/reading/getall"),
    readingCreate("/api/v1/reading/create"),
    readingActivate("/api/v1/reading/activate"),
    readingDelete("/api/v1/reading/delete"),
    readingClose("/api/v1/reading/end"),
    readingGetById("/api/v1/reading/getById"),
    readingsElementCreate("/api/v1/reading/item/create"),
    tarifSave("/api/v1/tariff/save"),
    tarifDelete("/api/v1/tariff/delete"),
    tarifGetById("/api/v1/tariff/get"),
    tarifGetAll("/api/v1/tariff/get_all"),
    tarfiGetByUid("/api/v1/tariff/getByUid"),
    usersGetAll("/api/v1/users/getAll"),
    usersGetActiveRoles("/api/v1/users/get_active_role"),
    userUpdate("/api/v1/users/update"),
    usersGetRoles("/api/v1/users/getroles"),
    usersRegister("/api/v1/users/register"),
    countryGetById("/api/v1/country/get");









    private String endpoint;
    PermisionList(String endpoint) {
        this.endpoint= endpoint;
    }

    public String getPermision(){
        return this.endpoint;
    }
    public static  List<PermisionList> getPermisionList(){
        return new ArrayList<>(EnumSet.allOf(PermisionList.class));
    }
}
