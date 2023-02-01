package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.PpRepo;
import com.crm.crmbe.entity.pp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class PpService {

    @Autowired
    PpRepo ppRepo;
    @Autowired
    KontrahentService kontrahentService;

    public boolean createIfNotActive(pp pp){
//        pp.setContractor(kontrahentService.findByNumber(pp.getContractor()).getId());
        if (!ppRepo.findByContractor(pp.getContractor()).isPresent()){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmssyyMMdd");
            LocalDateTime myObj = LocalDateTime.now();
            String id = String.valueOf(dtf.format(myObj)+String.valueOf((int)(Math.random()*10)));
            pp.setUid(id);
            pp.setStatus("Z");
            ppRepo.save(pp);
            return true;
        }
        return false;
    }
    public List<pp> getAll(){
        return (List<pp>) ppRepo.findAll();
    }
    public List<pp> getActive(){
        return (List<pp>) ppRepo.findByStatus("A");
    }
    public pp getByUid(String id){
        if (ppRepo.findByUid(id).isPresent()){
            return ppRepo.findByUid(id).get();
        }
       return null;
    }
    public pp getById(Long id){
        return ppRepo.findById(id).get();
    }

    public pp getByContractor(Long contractor){
      return ppRepo.findByContractor(contractor).get();
    }

    public void updatePP(pp pp){
        ppRepo.save(pp);
    }

}
