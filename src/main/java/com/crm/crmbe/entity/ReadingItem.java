package com.crm.crmbe.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "readings_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
