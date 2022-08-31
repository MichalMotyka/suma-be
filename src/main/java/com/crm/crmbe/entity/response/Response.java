package com.crm.crmbe.entity.response;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Response {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    private String TimeStamp;
    private int status;
    private String Message;

    @Override
    public String toString() {
        return "{" +
                "\"timestamp\":\"" + TimeStamp +"\"," +
                "\"status\":"+status+" ,"+
                "\"message\":\"" + Message + "\"" +
                "}";
    }


    public Response(int status, String message) {
        this.TimeStamp = sdf.format(new Timestamp(System.currentTimeMillis()));
        this.status = status;
        this.Message = message;
    }
}
