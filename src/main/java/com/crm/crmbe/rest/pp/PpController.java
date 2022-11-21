package com.crm.crmbe.rest.pp;

import com.crm.crmbe.database.services.PpService;
import com.crm.crmbe.entity.pp;
import com.crm.crmbe.entity.response.ObjectResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PpController {

    @Autowired
    PpService ppService;

    ObjectResponse objectResponse = new ObjectResponse();

    @PutMapping("/api/v1/pp/create")
    public void create(@RequestBody pp pp, HttpServletResponse response) throws IOException {
        if (ppService.createIfNotActive(pp)){
            response.sendError(HttpServletResponse.SC_CREATED);
        }else {
            response.sendError(HttpServletResponse.SC_OK);
        }
    }
    @GetMapping("/api/v1/pp/get")
    public String getAll(@RequestParam String active){
        if (active.equals("T")){
           return objectResponse.PPJsonList(ppService.getActive());
        }else{
           return objectResponse.PPJsonList(ppService.getAll());
        }
    }
    @GetMapping("/api/v1/pp/getByUid")
    public String getByUid(@RequestParam String id){
        Gson gson = new Gson();
        return gson.toJson(ppService.getByUid(id));
    }

    @GetMapping("/api/v1/pp/getById")
    public String getById(@RequestParam Long id){
        Gson gson = new Gson();
        return gson.toJson(ppService.getById(id));
    }


}
