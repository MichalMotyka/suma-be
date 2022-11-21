package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.MeterRepo;
import com.crm.crmbe.entity.Meter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeterService {

    @Autowired
    MeterRepo meterRepo;

    public boolean createIfNotExist(Meter meter){
        if (!meterRepo.findByNameAndModel(meter.getName(), meter.getModel()).isPresent()){
            meter.setStatus("T");
            meterRepo.save(meter);
            return true;
        }
        return false;
    }
    public Meter getByModel(String model){
        return meterRepo.findByModel(model).get();
    }
    public Meter getById(Long id){
        return meterRepo.findById(id).get();
    }

    public List<Meter> getAll(){
       return (List<Meter>) meterRepo.findAll();
    }

    public List<Meter> getActive(){
        return (List<Meter>) meterRepo.findByStatus("T");
    }

    public boolean delete(Long id){
        if (meterRepo.findById(id).isPresent()){
            Meter meter = meterRepo.findById(id).get();
            meter.setStatus("N");
            meterRepo.save(meter);
            return true;
        }else{
            return false;
        }
    }
}
