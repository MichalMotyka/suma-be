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
    private Long pp;
    private Long conctrator;
    private Long contract;
    private Long meter;
    private String date;
    private String action;
}
