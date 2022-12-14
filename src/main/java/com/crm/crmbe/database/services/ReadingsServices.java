package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.ReadingItemRepo;
import com.crm.crmbe.database.repository.ReadingsRepo;
import com.crm.crmbe.entity.ReadingItem;
import com.crm.crmbe.entity.Readings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReadingsServices {

    @Autowired
    ReadingsRepo readingsRepo;

    @Autowired
    KontrahentService kontrahentService;

    @Autowired
    ReadingItemRepo readingItemRepo;

    public List<Readings> getAllReadings(){
        return (List<Readings>) readingsRepo.findAll();
    }

    public Long createReading(Readings readings){
        //TODO walidacja daty
        String uid =readings.getContract()+ UUID.randomUUID().toString().split("-")[0];
        readings.setUid(uid);
        readingsRepo.save(readings);
        return readingsRepo.findByUid(uid).get().getId();
    }
    public boolean activate(Readings readings){
        if (readingsRepo.findById(readings.getId()).isPresent()){
            readings.setStatus("W");
            readingsRepo.save(readings);
            return true;
        }
        return false;

    }
    public void createReadingIrem(ReadingItem readingItem){
        readingItemRepo.save(readingItem);
    }

    public void delete(long id){
        Readings readings = readingsRepo.findById(id).get();
        readings.setStatus("A");
        readingsRepo.save(readings);
    }

}
