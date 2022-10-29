package com.crm.crmbe.entity.services;

import com.crm.crmbe.database.services.KontrahentService;
import com.crm.crmbe.entity.Contract;
import com.crm.crmbe.entity.Kontrahent;
import com.crm.crmbe.entity.OT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtBuissnesServeice {

    @Autowired
    KontrahentService kontrahentService;

    public OT createOtWithContract(Contract contract){
        Kontrahent contractor = kontrahentService.findByNumber(contract.getContract());
        return new OT(0L,"",contractor.getPpe(),contractor.getId().toString(),contract.getUid(),"0",contract.getRoz(),"M");
    }
}
