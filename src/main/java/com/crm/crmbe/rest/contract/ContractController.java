package com.crm.crmbe.rest.contract;

import com.crm.crmbe.database.services.ContractService;
import com.crm.crmbe.database.services.KontrahentService;
import com.crm.crmbe.entity.Contract;
import com.crm.crmbe.entity.response.ObjectResponse;
import com.crm.crmbe.entity.services.OtBuissnesServeice;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ContractController {

    @Autowired
    ContractService contractService;
    @Autowired
    OtBuissnesServeice otBuissnesServeice;
    ObjectResponse objectResponse = new ObjectResponse();


    @PutMapping("/api/v1/contract/create")
    public void save(@RequestBody Contract contract, HttpServletResponse response) throws IOException {
        if (contractService.createIfNotExist(contract)){
            response.sendError(HttpServletResponse.SC_OK);
        }else {
            response.sendError(HttpServletResponse.SC_NO_CONTENT);
        }
    }
    @GetMapping("/api/v1/contract/getByPP")
    public String getByContractorS1(@RequestParam String id,HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        Contract contract  = contractService.getByContractorS1(id);
        if (contract != null){
            return gson.toJson(contract);
        }else{
            response.sendError(HttpServletResponse.SC_NO_CONTENT);
        }
        return null;
    }
    @DeleteMapping("/api/v1/contract/delete")
    public void delete(@RequestParam String id,HttpServletResponse response) throws IOException {
        if (contractService.dealteIfExist(id)){
            response.sendError(HttpServletResponse.SC_ACCEPTED);
        }else{
            response.sendError(HttpServletResponse.SC_NO_CONTENT);
        }
    }
    @PutMapping("/api/v1/contract/edit")
    public void edit(@RequestBody Contract body,HttpServletResponse response) throws IOException {
        if (contractService.edit(body)){
            response.sendError(HttpServletResponse.SC_OK);
            return;
        }
        response.sendError(HttpServletResponse.SC_NO_CONTENT);
    }

    @GetMapping("/api/v1/contract/getAll")
    public String getAll(){
       return objectResponse.ContractJsonList(contractService.getAll());
    }
}