package com.crm.crmbe.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@ToString
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;
    private String uid;
    private String name;
    private long tarif;
    private String tarname;
    private long component;
    private String compname;
    private float price;
    private String active;
}
