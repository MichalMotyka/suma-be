package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.ContractRepo;
import com.crm.crmbe.database.repository.KontrahentRepo;
import com.crm.crmbe.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContractService {

    @Autowired
    ContractRepo contractRepo;
    @Autowired
    KontrahentService kontrahentService;


    public boolean createIfNotExist(Contract contract){
        if (!contractRepo.findByStateAndContract("A",contract.getContract()).isPresent()){
            contract.setRoz(contract.getRoz().substring(0,10));
            contract.setUid(UUID.randomUUID().toString());
            contract.setState("S");
            contractRepo.save(contract);
        }
        return false;
    }

    public Contract getByContractorS1(String id){
//        Long contractorID = kontrahentService.findByPP(id).getId();
        Optional<Contract> contract = contractRepo.findByStateAndContract("S", Long.valueOf(id));
        if(contract.isPresent()){
            return contract.get();
        }
        return null;
    }
}
