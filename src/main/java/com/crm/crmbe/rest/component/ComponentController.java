package com.crm.crmbe.rest.component;

import com.crm.crmbe.database.services.ComponentService;
import com.crm.crmbe.entity.Component;
import com.crm.crmbe.entity.response.ObjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class ComponentController {

    @Autowired
    ComponentService componentService;
    ObjectResponse objectResponse = new ObjectResponse();

    @GetMapping("api/v1/component/get_all")
    public String getAll(){
        return objectResponse.ComponentList((List<Component>) componentService.findAll());
    }
    @PutMapping("api/v1/component/save")
    public void save(@RequestBody Component component, HttpServletResponse response) throws IOException {
       boolean isSaved = componentService.saveIfNotExist(component);
       if(isSaved){
           response.sendError(HttpServletResponse.SC_CREATED);
       }else{
           response.sendError(HttpServletResponse.SC_OK);
       }
    }
    @DeleteMapping("api/v1/component/delete")
    public void delete(@RequestBody Component component, HttpServletResponse response) throws IOException {
        if (componentService.isExist(component)){
            if (component.getTyp().equals("Zużyciowy")){
                component.setTyp("R");
            }else{
                component.setTyp("P");
            }
            component.setActive("N");
            componentService.save(component);
            response.sendError(HttpServletResponse.SC_OK);
        }else{
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }

    }
    @GetMapping("api/v1/component/get_by")
    public Component getById(@RequestParam long id){
        return componentService.findById(id);
    }
    @GetMapping("api/v1/component/search")
    public String search(@RequestParam String data){
        return objectResponse.ComponentList(componentService.findByName(data));
    }

}
