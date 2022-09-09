package com.crm.crmbe.rest.adres;

import com.crm.crmbe.database.services.AdresService;
import com.crm.crmbe.entity.Adres;
import com.crm.crmbe.entity.response.ObjectResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AdresController {

    @Autowired
    AdresService adresService;

    ObjectResponse objectResponse = new ObjectResponse();

    @PutMapping("/api/v1/adres/create")
    public void addAdres(@RequestBody Adres adres,HttpServletResponse response) throws IOException {
            if(adresService.saveIfNotExist(adres)){
                try {
                    response.sendError(HttpServletResponse.SC_CREATED,"Successful created address");
                    return;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                response.sendError(HttpServletResponse.SC_OK,"Address can't be created address already exist");
                return;
            }
    }
    @GetMapping ("/api/v1/adres/get_state")
    public String getAllState(){
        return  objectResponse.StateList(adresService.getAllState());
    }
    @DeleteMapping("/api/v1/adres/delete_state")
    public void deleteState(@RequestBody Adres adres,HttpServletResponse response){
        if(adresService.deleteIfExist(adres)){
            try {
                response.sendError(HttpServletResponse.SC_ACCEPTED,"Address successful deleted");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                response.sendError(HttpServletResponse.SC_CONFLICT,"Adress dose not exist");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @GetMapping("/api/v1/adres/get_adres")
    public String getAdres(@RequestParam(name = "gus") String gus){
        System.out.println(gus);
        return objectResponse.StateList(adresService.findAllAdresForState(gus));
    }
}
