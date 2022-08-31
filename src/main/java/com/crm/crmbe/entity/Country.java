package com.crm.crmbe.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Country")
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String name;
    private String prefix;
    @Column(name = "postmask")
    private String postMask;
    @Column(name = "gusmask")
    private String gusMask;
}

