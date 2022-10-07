package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.ComponentRepo;
import com.crm.crmbe.entity.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComponentService {

    @Autowired
    ComponentRepo componentRepo;

    public Iterable<Component> findAll(){
       return componentRepo.findAll();
    }
    public boolean saveIfNotExist(Component component){
        boolean isExist = componentRepo.findByNameAndActive(component.getName(),"T").isPresent();
        if (isExist){
            return false;
        }
        if (component.getTyp().equals("Zużyciowy")){
            component.setTyp("R");
        }else{
            component.setTyp("P");
        }
        componentRepo.save(component);
        return true;
    }
    public boolean isExist(Component component){
        return componentRepo.findByNameAndActive(component.getName(),"T").isPresent();
    }
    public void save(Component component){
        componentRepo.save(component);
    }
    public Component findById(long id){
        return componentRepo.findById(id).get();
    }
}
