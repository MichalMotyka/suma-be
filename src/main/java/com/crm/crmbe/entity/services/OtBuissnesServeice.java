package com.crm.crmbe.entity.services;

import com.crm.crmbe.database.services.ContractService;
import com.crm.crmbe.database.services.KontrahentService;
import com.crm.crmbe.database.services.PpService;
import com.crm.crmbe.entity.Contract;
import com.crm.crmbe.entity.Kontrahent;
import com.crm.crmbe.entity.OT;
import com.crm.crmbe.entity.pp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtBuissnesServeice {

    @Autowired
    KontrahentService kontrahentService;

    @Autowired
    PpService ppService;


    public OT createOtWithContract(Contract contract){
        pp pp =  ppService.getByContractor(contract.getContract());
        return new OT(0L,"",pp.getId(),contract.getContract(),contract.getId(),0L,contract.getRoz(),"M");
    }
}
