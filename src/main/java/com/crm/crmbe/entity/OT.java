package com.crm.crmbe.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String uid;
    private String pp;
    private String conctrator;
    private String contract;
    private String meter;
    private String date;
    private String action;
}
