package com.crm.crmbe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract  class Kontrahent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    @Column(name = "numerklienta")
    private String numerKlienta;
    private String nazwa;
    private String pesel;
    private String nip;
    private String prosument;
    private float saldo;
    private int adres;
    private String ulica;
    @Column(name="adreskores")
    private int adresKores;
    @Column(name="ulicakores")
    private String ulicaKores;
    private String ppe;
    private String state;
}
