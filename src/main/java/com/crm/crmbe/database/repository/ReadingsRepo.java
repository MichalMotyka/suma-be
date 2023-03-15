package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.Readings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadingsRepo extends CrudRepository<Readings,Long> {

    Optional<Readings> findByUid(String uid);
    Optional<Readings> findByContractor(String contractor);
    Optional<Readings> findByContract(String contract);
}
