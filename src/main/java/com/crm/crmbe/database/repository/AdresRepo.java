package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.Adres;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdresRepo extends CrudRepository<Adres,Long> {

    Iterable<Adres> findByType(String type);
    Optional<Adres> findByNameAndTypeAndGusAndActive(String name,String Type,String Gus,String active);
    @Query(value = "select * from adres where type != ?1 and gus like ?2", nativeQuery = true)
    Iterable<Adres> findByTypeAndGus(String type, String gus);
}
