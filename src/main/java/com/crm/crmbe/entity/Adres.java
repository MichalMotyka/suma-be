package com.crm.crmbe.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Table(name="adres")
@Entity
@ToString
public class Adres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;
    private String name;
    @Column(name = "gus")
    private String gus;
    private String type;
    private String active;
    private String post;
    private String post_code;
    private String country;
}
