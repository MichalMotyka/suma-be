package com.crm.crmbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComponentDocument {
    private Long id;
    private String name;
    private String typ;
    private String active;
    private float price;
}
