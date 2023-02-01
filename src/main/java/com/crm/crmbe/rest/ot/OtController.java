package com.crm.crmbe.rest.ot;

import com.crm.crmbe.database.services.OtService;
import com.crm.crmbe.entity.OT;
import com.crm.crmbe.entity.response.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class OtController {

    @Autowired
    OtService otService;
    ObjectResponse objectResponse = new ObjectResponse();

    @GetMapping("api/v1/ot/get_all")
    public String getAll(){
        return  objectResponse.OtJsonList(otService.getAll());
    }

    @DeleteMapping("api/v1/ot/delete")
    public void delete(@RequestParam Long id, HttpServletResponse response) throws IOException {
        if (otService.deleteIfExist(id)){
            response.sendError(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
    }
    @PutMapping("api/v1/ot/create")
    public void create(@RequestBody OT body, HttpServletResponse response) throws IOException {
        if (otService.createIfNotExist(body) != null){
            response.sendError(HttpServletResponse.SC_OK);
            return;
        }
        response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
    }

    @PutMapping("api/v1/ot/edit")
    public void edit(@RequestBody OT body,HttpServletResponse response) throws IOException {
        if(otService.edit(body)){
            response.sendError(HttpServletResponse.SC_OK);
            return;
        }
        response.sendError(HttpServletResponse.SC_NO_CONTENT);
    }
    @PutMapping("api/v1/ot/veryfy")
    public void varyfi(@RequestBody OT body,HttpServletResponse response) throws IOException {
        if (otService.veryfi(body)){
            response.sendError(HttpServletResponse.SC_OK);
            return;
        }
        response.sendError(HttpServletResponse.SC_NO_CONTENT);
    }
    @GetMapping("api/v1/ot/search")
    public String search(@RequestParam String data){
        return objectResponse.OtJsonList(otService.search(data));
    }
}
