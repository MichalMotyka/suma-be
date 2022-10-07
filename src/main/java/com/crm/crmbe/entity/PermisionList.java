package com.crm.crmbe.entity;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum PermisionList {
    adresFull("/api/v1/adres/**"),
    adresList("/api/v1/adres/get_state"),
    adresView("api/v1/adres/view"),
    adresEdit("api/v1/adres/edit");

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
