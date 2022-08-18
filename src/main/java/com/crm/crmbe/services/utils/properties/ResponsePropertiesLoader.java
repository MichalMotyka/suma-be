package com.crm.crmbe.services.utils.properties;

import java.util.Properties;

public class ResponsePropertiesLoader {

    static Properties  properties = new Properties();

    public ResponsePropertiesLoader() {
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("message.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getLoginOK() {
        return properties.getProperty("response.message.status.ok");
    }
    public String getLoginUnauthorized() {
        return properties.getProperty("response.message.status.unauthorized");
    }
}
