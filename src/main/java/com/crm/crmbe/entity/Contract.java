package com.crm.crmbe.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@ToString
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String uid;
    private Long contract;
    private Long payer;
    private Long adres;
    private Long tarif;
    private Long price;
    private Long ot;
    private String roz;
    @Column(name = "enddate")
    private String endDate;
    private String faza;
    private String state;
}
