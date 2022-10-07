package com.crm.crmbe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "historic_kontrahent")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoricKontrahent extends Kontrahent{
    @Column(name = "user_id")
    private String user_id;
}
