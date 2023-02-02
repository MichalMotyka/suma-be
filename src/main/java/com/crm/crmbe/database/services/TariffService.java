package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.TariffRepo;
import com.crm.crmbe.entity.Tariff;
import com.crm.crmbe.entity.TariffJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class TariffService {

    @Autowired
    TariffRepo tariffRepo;


    public List<Tariff> getByUid(String uid){
        return (List<Tariff>) tariffRepo.findByTarif_id(uid);
    }
    public boolean saveIfNotExist(TariffJson tariffJson) {
        try {
            if (!tariffRepo.findByNameAndActive(tariffJson.getName(), "T").isPresent()) {
                String uuid = UUID.randomUUID().toString();
                for (long x : tariffJson.getComponent_id()) {
                    tariffRepo.save(new Tariff(0, uuid, tariffJson.getName(), x, "T"));
                }
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    public Tariff getById(String id){
       return tariffRepo.findById(Long.parseLong(id)).get();
    }
    public boolean deleteIfExist(String id){
        AtomicBoolean isExist = new AtomicBoolean(false);
       tariffRepo.findByTarif_id(id).forEach(value->{
           isExist.set(true);
           value.setActive("N");
           tariffRepo.save(value);
       });
       return isExist.get();
    }


    public List<Tariff> getAll(){
        return (List<Tariff>) tariffRepo.findAll();
    }

    public List<Tariff> search(String data) {
        return (List<Tariff>) tariffRepo.findByName(data);
    }
}
