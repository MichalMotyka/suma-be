package com.crm.crmbe.database.services;


import com.crm.crmbe.database.repository.OtRepo;
import com.crm.crmbe.entity.OT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OtService {

    @Autowired
    OtRepo otRepo;

    public String createIfNotExist(OT ot){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmssyyMMdd");
            LocalDateTime myObj = LocalDateTime.now();
            String id = String.valueOf(dtf.format(myObj)+String.valueOf((int)(Math.random()*10)));
            ot.setUid(id);
            otRepo.save(ot);
        return id;
    }
    public OT findByUid(String uid){
       return otRepo.findByUid(uid).get();
    }
}
