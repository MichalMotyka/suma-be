package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.ContractRepo;
import com.crm.crmbe.database.repository.KontrahentRepo;
import com.crm.crmbe.entity.Contract;
import com.crm.crmbe.entity.services.OtBuissnesServeice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContractService {

    @Autowired
    ContractRepo contractRepo;
    @Autowired
    KontrahentService kontrahentService;
    @Autowired
    OtService otService;
    @Autowired
    OtBuissnesServeice otBuissnesServeice;


    public boolean createIfNotExist(Contract contract){
        if (!contractRepo.findByStateAndContract("A",contract.getContract()).isPresent()){
            contract.setRoz(contract.getRoz().substring(0,10));
            String uid = UUID.randomUUID().toString();
            contract.setUid(uid);
            contract.setState("S");
            contractRepo.save(contract);
            contract.setId(contractRepo.findByUid(uid).get().getId());
            String otUid = otService.createIfNotExist(otBuissnesServeice.createOtWithContract(contract));
            contract.setOt(otService.findByUid(otUid).getId());
            contractRepo.save(contract);
            return true;
        }
        return false;
    }

    public boolean edit(Contract body){
        if (contractRepo.findById(body.getId()).isPresent()){
            contractRepo.save(body);
            return true;
        }
        return false;
    }

    public boolean dealteIfExist(String id){
       Optional<Contract> contract = contractRepo.findByUid(id);
        if (contract.isPresent()){
            Contract contractModel = contract.get();
            contractModel.setState("O");
            contractRepo.save(contractModel);
            return true;
        }
        return false;
    }

    public Contract getByContractorS1(String id){
        Optional<Contract> contract = contractRepo.findByStateAndContract("S", Long.valueOf(id));
        if(contract.isPresent()){
            return contract.get();
        }
        return null;
    }

    public List<Contract> getAll(){
        return (List<Contract>) contractRepo.findAll();
    }

}
