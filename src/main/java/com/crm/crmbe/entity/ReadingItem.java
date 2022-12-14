package com.crm.crmbe.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "readings_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReadingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;
    @Column(name = "readingid")
    private long readingId;
    private long element;
    private long wear;
}
