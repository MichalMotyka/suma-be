package com.crm.crmbe.database.services;


import com.crm.crmbe.database.repository.OtRepo;
import com.crm.crmbe.entity.Contract;
import com.crm.crmbe.entity.Kontrahent;
import com.crm.crmbe.entity.OT;
import com.crm.crmbe.entity.pp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OtService {

    @Autowired
    OtRepo otRepo;

    @Autowired
    MeterService meterService;

    @Autowired
    ContractService contractService;

    @Autowired
    PpService ppService;

    @Autowired
    KontrahentService kontrahentService;



    public String createIfNotExist(OT ot){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmssyyMMdd");
            LocalDateTime myObj = LocalDateTime.now();
            String id = String.valueOf(dtf.format(myObj)+String.valueOf((int)(Math.random()*10)));
            ot.setUid(id);
            ot.setStatus("S");
            otRepo.save(ot);
        return id;
    }
    public boolean deleteIfExist(Long id){
        Optional<OT> ot = otRepo.findById(id);
        if (ot.isPresent()){
           OT zlecenie =  ot.get();
           if (zlecenie.getStatus() == "Z"){
               return false;
           }
           zlecenie.setStatus("A");
           otRepo.save(zlecenie);
           return true;
        }
        return false;
    }
    public OT findByUid(String uid){
       if (otRepo.findByUid(uid).isPresent()){
           return otRepo.findByUid(uid).get();
       }
       return null;
    }

    public List<OT> getAll(){
        return (List<OT>) otRepo.findAll();
    }

    public boolean edit(OT ot){
       Optional<OT> existingOT = otRepo.findByUid(ot.getUid());
       if (existingOT.isPresent()){
           ot.setId(existingOT.get().getId());
           otRepo.save(ot);
           return true;
       }
       return false;
    }

    public boolean veryfi(OT body) {
        Optional<OT> existingOT = otRepo.findByUid(body.getUid());
        if (existingOT.isPresent() && existingOT.get().getStatus().equals("S")){
            OT ot = existingOT.get();
            ot.setStatus("W");
            otRepo.save(ot);
            return true;
        }else if(existingOT.isPresent() && existingOT.get().getStatus().equals("W")){
            OT ot = existingOT.get();
            ot.setStatus("Z");
            Contract contract = contractService.getById(ot.getContract());
            pp pp =ppService.getById(ot.getPp());
            if (contract.getState().equals("S") && pp.getMeter() == 0 && ot.getAction().equals("M")){
                pp.setMeter(ot.getMeter());
                contract.setState("A");
                contractService.edit(contract);
                ppService.updatePP(pp);
            }else if (contract.getState().equals("P") && pp.getMeter() != 0 && ot.getAction().equals("D")) {
                pp.setMeter(0L);
                ppService.updatePP(pp);
                contract.setState("Z");
                contractService.edit(contract);
            } else if (existingOT.get().getAction().equals("P") || existingOT.get().getAction().equals("W")) {
                ot.setStatus("Z");

        }else return false;
            otRepo.save(ot);
            return true;
        }
        return false;
    }
    public OT findById(Long id){
        return otRepo.findById(id).get();
    }

    public List<OT> search(String value){
        List<OT> otList = new ArrayList<>();
        try {
          pp pp =  ppService.getByUid(value);
          if (pp != null &&otRepo.findOTByPp(pp.getId()).isPresent()){
              otList.add(otRepo.findOTByPp(pp.getId()).get());
          }
          if (otRepo.findByUid(value).isPresent()){
              otList.add(otRepo.findByUid(value).get());
          }
          List<Kontrahent> kontrahent = kontrahentService.findByNameAndNumber(value);
          if (kontrahent != null){
            kontrahent.forEach(data->{
                otList.add(otRepo.findOTByConctrator(data.getId()).get());
            });
          }
          Contract contract = contractService.getByUid(value);
          if (contract != null && otRepo.findOTByContract(contract.getId()).isPresent()){
              otList.add(otRepo.findOTByContract(contract.getId()).get());
          }
        }catch (Exception e){
            e.printStackTrace();
        }
        return otList;
    }
}
