package com.crm.crmbe.rest.readings;

import com.crm.crmbe.database.services.ReadingsServices;
import com.crm.crmbe.entity.ReadingItem;
import com.crm.crmbe.entity.Readings;
import com.crm.crmbe.entity.ReadingsElement;
import com.crm.crmbe.entity.response.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReadingController {

    @Autowired
    ReadingsServices readingsServices;

    ObjectResponse objectResponse = new ObjectResponse();


    @GetMapping("/api/v1/reading/getall")
    public String getAll(){
      return objectResponse.ReadingJsonList(readingsServices.getAllReadings());
    }

    @PutMapping("/api/v1/reading/create")
    public void create(@RequestBody Readings readings, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_OK, String.valueOf(readingsServices.createReading(readings)));
    }

    @PutMapping("/api/v1/reading/activate")
    public void activate(@RequestBody Readings readings, HttpServletResponse response, HttpServletRequest request) throws IOException {
        if (readingsServices.activate(readings)){
            response.sendError(HttpServletResponse.SC_OK);
            return;
        }
        response.sendError(HttpServletResponse.SC_NO_CONTENT);
    }

    @DeleteMapping("/api/v1/reading/delete")
    public void delete(@RequestParam long id, HttpServletResponse response) throws IOException {
        readingsServices.delete(id);
        response.sendError(HttpServletResponse.SC_OK);
    }

    @PutMapping("/api/v1/reading/end")
    public void end(@RequestBody Readings readings, HttpServletResponse response) throws IOException {
        readingsServices.end(readings);
        response.sendError(HttpServletResponse.SC_OK);
    }
    @GetMapping("/api/v1/reading/getById")
    public String getById(@RequestParam long id, HttpServletResponse response){
        return objectResponse.ReadingsViewJsonList(readingsServices.getByIdItems(id));
    }
    @GetMapping("/api/v1/reading/search")
    public String search(@RequestParam String data){
        return objectResponse.ReadingJsonList(readingsServices.search(data));
    }
}
