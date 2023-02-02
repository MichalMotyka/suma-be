package com.crm.crmbe.rest.kontrahent;


import com.crm.crmbe.database.services.KontrahentService;
import com.crm.crmbe.entity.Kontrahent;
import com.crm.crmbe.entity.KontrahentImpl;
import com.crm.crmbe.entity.response.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class KontrahentController {

    @Autowired
    KontrahentService kontrahentService;
    ObjectResponse objectResponse = new ObjectResponse();

    @PutMapping("/api/v1/kontrahent/save")
    public void saveKontrahent(@RequestBody KontrahentImpl kontrahent, HttpServletResponse httpServletResponse, HttpServletRequest request) throws IOException {
        //TODO Validatory sprawdzajace poprawnosc danych jeśli błąd zwraca 200 jesli nie 201
        Cookie[] cookies = request.getCookies();
        try {
            if (kontrahent.getNumerKlienta() == null){
                kontrahentService.saveKontrahent(kontrahent,cookies);
                httpServletResponse.sendError(HttpServletResponse.SC_CREATED);
            }else {
                kontrahentService.saveKontrahent(kontrahent,cookies);
                httpServletResponse.sendError(HttpServletResponse.SC_OK);
            }

        } catch (IOException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_EXPECTATION_FAILED);
        }
    }
    @GetMapping("/api/v1/kontrahent/list")
    public String getKontrahent(){
        return objectResponse.KontrahentList(kontrahentService.getAllKontrahent());
    }

    @GetMapping("/api/v1/kontrahent/get")
    public Kontrahent getById(@RequestParam Long id){
        return kontrahentService.findById(id);
    }

    @DeleteMapping("/api/v1/kontrahent/delete")
    public void removeKontrahent(@RequestParam String id,HttpServletResponse response) throws IOException {
        if(kontrahentService.removeKontrahent(id)){
            response.sendError(HttpServletResponse.SC_ACCEPTED);
        }else{
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,"Kontrahent dosn't exist");
        }
    }
    @GetMapping("/api/v1/kontrahent/get_uid")
    public Kontrahent getByUid(@RequestParam String uid){
        return kontrahentService.findByNumber(uid);
    }

    @GetMapping("/api/v1/kontrahent/search")
    public String search(@RequestParam String data){
        return objectResponse.KontrahentList(kontrahentService.search(data));
    }

}
