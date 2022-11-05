package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.PriceRepo;
import com.crm.crmbe.entity.Component;
import com.crm.crmbe.entity.ComponentDocument;
import com.crm.crmbe.entity.Price;
import com.crm.crmbe.entity.PriceDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PriceService {

    @Autowired
    private PriceRepo priceRepo;

    @Autowired
    private ComponentService componentService;

    @Transactional//(readOnly = false,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public boolean createIfNotExist(PriceDocument priceDocument) {
        if (!priceRepo.existsByName(priceDocument.getName())) {
            String uuid = UUID.randomUUID().toString();
            for (ComponentDocument component : priceDocument.getComponents()) {
                priceRepo.save(new Price(0, uuid, priceDocument.getName(), priceDocument.getTarif(), priceDocument.getTarif_name(), component.getId(), component.getName(), component.getPrice(),"T"));
            }
            return true;
        }
        return false;
    }
    public List<PriceDocument> getByuid(String uid){
        List<PriceDocument> priceDocuments = new ArrayList<>();
        List<Price> priceList = (List<Price>) priceRepo.findByUid(uid);
        List<ComponentDocument> documents = new ArrayList<>();
            for (Price price : priceList) {

                Component component = componentService.findById(price.getComponent());
                documents.add(new ComponentDocument(component.getId(), component.getName(), component.getTyp(), component.getActive(), price.getPrice()));
            }
        priceDocuments.add(new PriceDocument(priceList.get(0).getId(), priceList.get(0).getUid(), priceList.get(0).getName(), priceList.get(0).getTarif(), priceList.get(0).getTarname(),priceList.get(0).getActive(), documents.toArray(new ComponentDocument[priceDocuments.size()])));
        return priceDocuments;
    }

    public List<PriceDocument> getAll() {
        List<PriceDocument> priceDocuments = new ArrayList<>();
        priceRepo.findDistinctAll().forEach(value -> {
            int x = 0;
            List<Price> priceList = (List<Price>) priceRepo.findByUid(value);
            for (Price price : priceList) {
                List<ComponentDocument> documents = new ArrayList<>();
                Component component = componentService.findById(price.getComponent());
                documents.add(new ComponentDocument(component.getId(), component.getName(), component.getTyp(), component.getActive(), price.getPrice()));
                x++;
                if (priceList.size() == x) {
                    priceDocuments.add(new PriceDocument(price.getId(), price.getUid(), price.getName(), price.getTarif(), price.getTarname(),price.getActive(), documents.toArray(new ComponentDocument[priceDocuments.size()])));
                }
            }
        });
        return priceDocuments;
    }

    public boolean deleteIfExist(String id){
      List<Price> priceList = (List<Price>) priceRepo.findByUid(id);
      if (priceList.size() != 0){
          priceList.forEach(value->{
              value.setActive("N");
              priceRepo.save(value);
          });
          return true;
      }else {
          return false;
      }
    }
    public Price getById(Long id){
        return priceRepo.findById(id).get();
    }
}
