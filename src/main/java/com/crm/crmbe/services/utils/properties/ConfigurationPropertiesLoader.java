package com.crm.crmbe.services.utils.properties;

import java.util.Properties;

public class ConfigurationPropertiesLoader {

    static Properties properties = new Properties();

    public ConfigurationPropertiesLoader() {
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getTokenExperience() {
        return Integer.parseInt(properties.getProperty("jwt.expiration.time"));
    }
}
