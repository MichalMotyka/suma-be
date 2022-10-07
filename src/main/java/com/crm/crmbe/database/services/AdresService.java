package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.AdresRepo;
import com.crm.crmbe.entity.Adres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AdresService {

    @Autowired
    AdresRepo adresRepo;

    public List<Adres> getAllState(){
        List<Adres> adresList = new ArrayList<>();
        adresRepo.findByType("województwo").forEach(adres -> {
            adresList.add(adres);
        });
        return adresList;
    }
    public boolean saveIfNotExist(Adres adres){
        if (!adresRepo.findByNameAndTypeAndGusAndActive(adres.getName(),adres.getType(),adres.getGus(),"T").isPresent()){
            adresRepo.save(adres);
            return true;
        }
        else return false;
    }
    public boolean deleteIfExist(Adres adres){
        if (adresRepo.findByNameAndTypeAndGusAndActive(adres.getName(),adres.getType(),adres.getGus(),"T").isPresent()){
            adres.setActive("N");
            adresRepo.save(adres);
            return true;
        }else return false;
    }
    public List<Adres> findAllAdresForState(String gus){
        List<Adres> adresList = new ArrayList<>();
        adresRepo.findByTypeAndGus("województwo",gus.substring(0,2)+"%").forEach(
               adres -> {
                   adresList.add(adres);
               }
        );
        return adresList;
    }
    public List<Adres> findAllAdresNoState(){
        List<Adres> adresList = new ArrayList<>();
        adresRepo.findByTypeIsNotAndActive("województwo","T").forEach(
                adres -> {
                    adresList.add(adres);
                }
        );
        return adresList;
    }
    public List<Adres> findAllAdresNoStateHistoric(){
        List<Adres> adresList = new ArrayList<>();
        adresRepo.findByTypeIsNot("województwo").forEach(
                adres -> {
                    adresList.add(adres);
                }
        );
        return adresList;
    }
    public Adres findAdresById(String id){
       return adresRepo.findById(Long.parseLong(id)).get();
    }
}
