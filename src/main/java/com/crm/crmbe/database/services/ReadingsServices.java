package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.ReadingItemRepo;
import com.crm.crmbe.database.repository.ReadingsRepo;
import com.crm.crmbe.entity.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReadingsServices {

    @Autowired
    ReadingsRepo readingsRepo;

    @Autowired
    KontrahentService kontrahentService;

    @Autowired
    ReadingItemRepo readingItemRepo;

    @Autowired
    ContractService contractService;

    @Autowired
    PriceService priceService;

    @Autowired
    ComponentService componentService;

    public List<Readings> getAllReadings() {
        return (List<Readings>) readingsRepo.findAll();
    }

    public Long createReading(Readings readings) {
        //TODO walidacja daty
        String uid = readings.getContract() + UUID.randomUUID().toString().split("-")[0];
        readings.setUid(uid);
        readingsRepo.save(readings);
        return readingsRepo.findByUid(uid).get().getId();
    }

    public boolean activate(Readings readings) {
        if (readingsRepo.findById(readings.getId()).isPresent()) {
            readings.setStatus("W");
            readingsRepo.save(readings);
            return true;
        }
        return false;

    }

    public void createReadingIrem(ReadingItem readingItem) {
        readingItemRepo.save(readingItem);
    }

    public void delete(long id) {
        Readings readings = readingsRepo.findById(id).get();
        readings.setStatus("A");
        readingsRepo.save(readings);
    }

    public void end(Readings readings) {
        readings.setStatus("R");
        countPrice(readings);
        readingsRepo.save(readings);
    }

    public void countPrice(Readings readings) {
        AtomicLong suma = new AtomicLong();
        Kontrahent kontrahent = kontrahentService.findById(Long.valueOf(readings.getContractor()));
        Contract contract = contractService.getByContractor(Long.valueOf(readings.getContractor()));
        List<ReadingItem> readingItemList = (List<ReadingItem>) readingItemRepo.findAllByReadingId(readings.getId());
        //List<Price> priceList = priceService.getAllByTarif(contract.getTarif());
        Price pricecount = priceService.getById(contract.getPrice());
        readingItemList.forEach(readingItem -> {
            //Optional<Price> pricecount = priceList.stream().filter(price -> price.getComponent() == readingItem.getElement()).findFirst();
                long price = (long) (pricecount.getPrice() * readingItem.getWear());
                if (componentService.findById(readingItem.getElement()).getTyp().equals("P")) {
                    suma.set(suma.get() + price);
                } else {
                    suma.set(suma.get() - price);
                }
        });
        kontrahent.setSaldo(kontrahent.getSaldo() + suma.get());
        kontrahentService.editsaldo((KontrahentImpl) kontrahent);
    }

    public List<ReadingsElement> getByIdItems(long id) {
        List<ReadingsElement> readingsElements = new ArrayList<>();
        readingItemRepo.findAllByReadingId(id).forEach(value -> {
            readingsElements.add(new ReadingsElement(value.getId(), componentService.findById(value.getElement()).getName(), 0, 0 + value.getWear(), value.getWear()));
        });
        return readingsElements;
    }

    public List<Readings> search(String data) {
        List<Readings> result = new ArrayList<>();
        if (kontrahentService.findByNameAndNumber(data) != null) {
            kontrahentService.findByNameAndNumber(data).forEach(value -> {
                if (readingsRepo.findByContractor(String.valueOf(value.getId())).isPresent()) {
                    result.add(readingsRepo.findByContractor(String.valueOf(value.getId())).get());
                }
            });
        }
        if (contractService.search(data) != null) {
            if (contractService.getByUid(data) != null) {
                if (readingsRepo.findByContract(String.valueOf(contractService.getByUid(data).getId())).isPresent()) {
                    result.add(readingsRepo.findByContract(String.valueOf(contractService.getByUid(data).getId())).get());
                }
            }
        }
        if (readingsRepo.findByUid(data).isPresent()) {
            result.add(readingsRepo.findByUid(data).get());
        }
        return result;
    }
}
