package com.crm.crmbe.entity;

import lombok.Data;

@Data
public class UserData {

    private final String id;
    private final String name;
    private final String role;
    private final boolean active;
}
