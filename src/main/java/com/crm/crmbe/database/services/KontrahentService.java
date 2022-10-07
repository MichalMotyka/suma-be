package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.HisKontrahentRepo;
import com.crm.crmbe.database.repository.KontrahentRepo;
import com.crm.crmbe.entity.HistoricKontrahent;
import com.crm.crmbe.entity.Kontrahent;
import com.crm.crmbe.entity.KontrahentImpl;
import com.crm.crmbe.services.utils.CookiController;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KontrahentService {

    @Autowired
    KontrahentRepo kontrahentRepo;

    @Autowired
    HisKontrahentRepo hisKontrahentRepo;
    @Autowired
    UserServices userServices;

    public void saveKontrahent(KontrahentImpl kontrahent, Cookie[] cookies){
        Optional<Kontrahent> existingKontrahent = kontrahentRepo.findByNumerKlienta(kontrahent.getNumerKlienta());
        if (existingKontrahent.isPresent()){
            kontrahent.setId(existingKontrahent.get().getId());
        }
        if(kontrahent.getId() == 0){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmssyyMMdd");
            LocalDateTime myObj = LocalDateTime.now();
            kontrahent.setNumerKlienta(String.valueOf(dtf.format(myObj)+String.valueOf((int)(Math.random()*10))));
            kontrahentRepo.save(kontrahent);
        }else{
            ModelMapper modelMapper = new ModelMapper();
            HistoricKontrahent historicKontrahent = modelMapper.map(existingKontrahent.get(), HistoricKontrahent.class);
            String token = CookiController.findCookie("authorization", cookies);
            userServices.findByToken(token);
            historicKontrahent.setUser_id(userServices.findByToken(token).getId());
            hisKontrahentRepo.save(historicKontrahent);
            kontrahentRepo.save(kontrahent);


        }
    }
    public List<Kontrahent> getAllKontrahent(){
        List<Kontrahent> kontrahents = new ArrayList<>();
        kontrahentRepo.findAll().forEach(kontrahent -> {
            kontrahents.add(kontrahent);
        });
        return kontrahents;
    }

    public boolean removeKontrahent(String id){
        Optional<KontrahentImpl> kontrahent = kontrahentRepo.findById(Long.parseLong(id));
        if(kontrahent.isPresent()){
            kontrahent.get().setState("N");
            kontrahentRepo.save(kontrahent.get());
            return true;
        }else{
            return false;
        }
    }
}
