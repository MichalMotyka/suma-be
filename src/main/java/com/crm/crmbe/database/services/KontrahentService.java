package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.HisKontrahentRepo;
import com.crm.crmbe.database.repository.KontrahentRepo;
import com.crm.crmbe.entity.HistoricKontrahent;
import com.crm.crmbe.entity.Kontrahent;
import com.crm.crmbe.entity.KontrahentImpl;
import com.crm.crmbe.entity.pp;
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
    PpService ppService;

    @Autowired
    HisKontrahentRepo hisKontrahentRepo;
    @Autowired
    UserServices userServices;

    public void saveKontrahent(KontrahentImpl kontrahent, Cookie[] cookies){
        Optional<Kontrahent> existingKontrahent = kontrahentRepo.findByNumerKlienta(kontrahent.getNumerKlienta());
        existingKontrahent.ifPresent(value -> kontrahent.setId(value.getId()));
        if(kontrahent.getId() == 0){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmssyyMMdd");
            LocalDateTime myObj = LocalDateTime.now();
            String id = String.valueOf(dtf.format(myObj)+String.valueOf((int)(Math.random()*10)));
            kontrahent.setNumerKlienta(id);
            kontrahentRepo.save(kontrahent);
            existingKontrahent = kontrahentRepo.findByNumerKlienta(kontrahent.getNumerKlienta());
            ppService.createIfNotActive(new pp(0L,"",existingKontrahent.get().getId(), 0L, 0L, 0L,""));
            pp pp = ppService.getByContractor(existingKontrahent.get().getId());
            kontrahent.setPpe(pp.getUid());
            kontrahent.setId(existingKontrahent.get().getId());
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
    public void editsaldo(KontrahentImpl kontrahent){
        kontrahentRepo.save(kontrahent);
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
    public Kontrahent findByNumber(String number){
      return kontrahentRepo.findByNumerKlienta(number.toString()).get();
    }
    public Kontrahent findById(Long id){
        return kontrahentRepo.findById(id).get();
    }
    public Kontrahent findByPP(String pp){
       return kontrahentRepo.findByPpe(pp).get();
    }
}
