package com.crm.crmbe.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Permission {
    @Id
    private String id;
    private String userID;
    private String permision;



}
