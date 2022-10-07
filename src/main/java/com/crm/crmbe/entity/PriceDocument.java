package com.crm.crmbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class PriceDocument {
    private long id;
    private String uid;
    private String name;
    private long tarif;
    private String tarif_name;
    private String active;
    private ComponentDocument[] components;
}
