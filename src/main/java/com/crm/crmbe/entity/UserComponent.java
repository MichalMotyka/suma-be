package com.crm.crmbe.entity;

import lombok.Data;

@Data
public class UserComponent {
        private final String id;
        private final String name;
        private String password;
        private final String roleName;
        private final Role[] role;
}
