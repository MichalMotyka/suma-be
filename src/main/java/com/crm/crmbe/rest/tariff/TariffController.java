package com.crm.crmbe.rest.tariff;


import com.crm.crmbe.database.services.TariffService;
import com.crm.crmbe.entity.Tariff;
import com.crm.crmbe.entity.TariffJson;
import com.crm.crmbe.entity.response.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@RestController
public class TariffController {

    @Autowired
    TariffService tariffService;

    ObjectResponse objectResponse = new ObjectResponse();

    @PutMapping("/api/v1/tariff/save")
    public void create(@RequestBody TariffJson tariffJson, HttpServletResponse response) throws IOException {
        if (tariffService.saveIfNotExist(tariffJson)){
            response.sendError(HttpServletResponse.SC_CREATED);
        }else {
            response.sendError(HttpServletResponse.SC_OK);
        }
    }
    @DeleteMapping("/api/v1/tariff/delete")
    public void drop(@RequestParam String id,HttpServletResponse response) throws IOException {
        if(tariffService.deleteIfExist(id)){
            response.sendError(HttpServletResponse.SC_OK);
        }else{
            response.sendError(203);
        }
    }

    @GetMapping("/api/v1/tariff/get")
    public Tariff getById(@RequestParam String id){
        return tariffService.getById(id);
    }
    @GetMapping("/api/v1/tariff/get_all")
    public String getAll(){
        ArrayList<TariffJson> tariffJsonArrayList = new ArrayList<>();
        tariffService.getAll().forEach(value -> {
            if (tariffJsonArrayList.size() == 0) {
                tariffJsonArrayList.add(new TariffJson(value.getId(), value.getTarif_id(), value.getName(), new long[]{value.getComponent_id()}, value.getActive()));
            } else {
                 AtomicBoolean create = new AtomicBoolean(false);
                tariffJsonArrayList.forEach(data -> {
                    if (data.getTarif_id().equals(value.getTarif_id())) {
                        long[] newArray = Arrays.copyOf(data.getComponent_id(), data.getComponent_id().length + 1);
                        newArray[newArray.length - 1] = value.getComponent_id();
                        data.setComponent_id(newArray);
                        create.set(false);
                    }else{
                        create.set(true);
                    }
                });
                if (create.get()) {
                    tariffJsonArrayList.add(new TariffJson(value.getId(), value.getTarif_id(), value.getName(), new long[]{value.getComponent_id()}, value.getActive()));
                    create.set(false);
                }
            }
        });
        return objectResponse.TariffJsonList(tariffJsonArrayList);
    }
    @GetMapping("/api/v1/tariff/getByUid")
    public String getByUid(@RequestParam String uid){
       return objectResponse.TariffList(tariffService.getByUid(uid));
    }

}
