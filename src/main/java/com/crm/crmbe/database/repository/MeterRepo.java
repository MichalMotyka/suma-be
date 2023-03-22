package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.Meter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeterRepo extends CrudRepository<Meter,Long> {

    Optional<Meter> findByNameAndModel(String name,String model);
    Iterable<Meter> findByStatus(String status);
    Optional<Meter> findByModel(String model);

    Iterable<Meter> findByName(String name);
}
