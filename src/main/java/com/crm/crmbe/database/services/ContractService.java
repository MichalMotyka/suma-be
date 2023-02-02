package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.ContractRepo;
import com.crm.crmbe.database.repository.KontrahentRepo;
import com.crm.crmbe.entity.Contract;
import com.crm.crmbe.entity.Kontrahent;
import com.crm.crmbe.entity.OT;
import com.crm.crmbe.entity.pp;
import com.crm.crmbe.entity.services.OtBuissnesServeice;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.rmi.server.UID;
import java.util.*;
import java.util.stream.Stream;

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
    @Autowired
    PpService ppService;
    @Autowired
    MeterService meterService;




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
    public Contract getByUid(String uid){
        if (contractRepo.findByUid(uid).isPresent()){
            return contractRepo.findByUid(uid).get();
        }
        return null;
    }
    public Contract getById(Long id){
        return contractRepo.findById(id).get();
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

    public boolean activate(Long id,String date) {
        Optional<Contract> optContract = contractRepo.findById(id);
        Contract contract = new Contract();
        if (optContract.isPresent()){
            contract = optContract.get();
        }else return false;

        Kontrahent kontrahent = kontrahentService.findById(contract.getContract());
        pp pp=ppService.getByUid(kontrahent.getPpe());
        OT ot = otService.findById(contract.getOt());
        if (contract.getState().equals("S") && pp.getMeter() == 0 && ot.getStatus().equals("W")){
            if (otService.veryfi(ot) && pp.getMeter() != 0){
                contract.setState("A");
                contractRepo.save(contract);
                return true;
            }else return false;
        }else if(contract.getState().equals("S") && pp.getMeter() != 0 && ot.getStatus().equals("Z")){
            contract.setState("A");
            contractRepo.save(contract);
            return true;
        } else if (contract.getState().equals("A")) {
            contract.setState("P");
            String otId = otService.createIfNotExist(new OT(0L,"",pp.getId(),contract.getContract(),contract.getId(),pp.getMeter(),date,"D","S",""));
            if (otId != null){
                contract.setOt(new Long(otId));
                contract.setEndDate(date);
                this.contractRepo.save(contract);
                return true;
            }
        } else return false;
        return false;
    }
    public Contract getContractByOt(Long id){
       if (contractRepo.findByOt(id).isPresent()) return contractRepo.findByOt(id).get();
       return null;
    }
    public Contract getByContractor(Long id){
        if (contractRepo.findByContractAndState(id,"A").isPresent() && contractRepo.findByContractAndState(id,"A").get().getState().equals("A")) return contractRepo.findByContractAndState(id,"A").get();
        return null;
    }

    public List<Contract> search(String data) {
        List<Contract> contractList = new ArrayList<>();
        if (contractRepo.findByUid(data).isPresent()){
            contractList.add(contractRepo.findByUid(data).get());
        }
        List<Kontrahent> kontrahentList = kontrahentService.findByNameAndNumber(data);
        if (kontrahentList != null && kontrahentList.size() != 0){
            kontrahentList.forEach(valeu ->{
                contractList.addAll((Collection<? extends Contract>) contractRepo.findContractByContractOrPayer(valeu.getId(),valeu.getId()));
            });
        }
        return contractList;
    }
}
