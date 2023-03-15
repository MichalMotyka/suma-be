package com.crm.crmbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class Role {
    public long id;
    private String name;
    private boolean active;
    public Role(long id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

}
