package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.Tariff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TariffRepo extends CrudRepository<Tariff,Long> {

    Optional<Tariff> findByNameAndActive(String name,String active);
    @Query(value = "select * from Tariff where tarif_id= ?1", nativeQuery = true)
    Iterable<Tariff> findByTarif_id(String tarif_id);
}
