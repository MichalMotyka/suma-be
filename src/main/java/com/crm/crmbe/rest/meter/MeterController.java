package com.crm.crmbe.rest.meter;

import com.crm.crmbe.database.services.MeterService;
import com.crm.crmbe.entity.Meter;
import com.crm.crmbe.entity.response.ObjectResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MeterController {

    @Autowired
    MeterService meterService;
    ObjectResponse objectResponse = new ObjectResponse();

    @PutMapping("api/v1/meter/create")
    public void create(@RequestBody Meter meter, HttpServletResponse response) throws IOException {
        if (meterService.createIfNotExist(meter)){
            response.sendError(HttpServletResponse.SC_CREATED);
        }else {
            response.sendError(HttpServletResponse.SC_OK);
        }
    }

    @GetMapping("api/v1/meter/get")
    public String getAll(@RequestParam String status){
        if (status.equals("T")) {
            return objectResponse.MeterJsonList(meterService.getActive());
        }
        return objectResponse.MeterJsonList(meterService.getAll());
    }

    @GetMapping("api/v1/meter/get_id")
    public Meter getById(@RequestParam Long id){
        return meterService.getById(id);
    }

    @GetMapping("api/v1/meter/get_uid")
    public Meter getByUid(@RequestParam String uid){
        return meterService.getByModel(uid);
    }

    @DeleteMapping("api/v1/meter/remove")
    public void remove(@RequestParam Long id,HttpServletResponse response) throws IOException {
        if (meterService.delete(id)){
            response.sendError(HttpServletResponse.SC_OK);
        }else{
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }

    }

}
