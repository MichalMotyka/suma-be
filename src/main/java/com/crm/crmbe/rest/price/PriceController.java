package com.crm.crmbe.rest.price;

import com.crm.crmbe.database.services.ComponentService;
import com.crm.crmbe.database.services.PriceService;
import com.crm.crmbe.entity.Component;
import com.crm.crmbe.entity.ComponentDocument;
import com.crm.crmbe.entity.Price;
import com.crm.crmbe.entity.PriceDocument;
import com.crm.crmbe.entity.response.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@EnableTransactionManagement
public class PriceController {

    @Autowired
    ComponentService componentService;


    ObjectResponse objectResponse = new ObjectResponse();
    @Autowired
    private PriceService priceService;

    @GetMapping("api/v1/price/get_all")
    public String getAll(HttpServletResponse response){
        return objectResponse.PriceJsonList(priceService.getAll());
    }

    @PutMapping("api/v1/price/save")
    public void save(@RequestBody PriceDocument price, HttpServletResponse response) throws IOException, NoSuchFieldException {
        if (priceService.createIfNotExist(price)){
            response.sendError(HttpServletResponse.SC_CREATED);
        }else{
            response.sendError(HttpServletResponse.SC_OK);
        }
    }

    @DeleteMapping("api/v1/price/delete")
    public void delete(@RequestParam String id,HttpServletResponse response) throws IOException {
        if (priceService.deleteIfExist(id)){
            response.sendError(HttpServletResponse.SC_ACCEPTED);
        }else {
            response.sendError(HttpServletResponse.SC_OK);
        }
    }
    @GetMapping("api/v1/price/get_by")
    public String getByuid(@RequestParam String id){
        return objectResponse.PriceJsonList(priceService.getByuid(id));
    }
    @GetMapping("api/v1/price/get_by_id")
    public Price getById(@RequestParam Long id){
        return priceService.getById(id);
    }
}
