package com.crm.crmbe.entity;


import lombok.Data;

@Data
public class ReadingsElement {
    private final long id;
    private final String name;
    private final long prev;
    private final long value;
    private final long used;
}
