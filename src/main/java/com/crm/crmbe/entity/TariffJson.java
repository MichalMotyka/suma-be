package com.crm.crmbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TariffJson {
        private long id;
        private String tarif_id;
        private String name;
        private long[] component_id;
        private String active;
}
