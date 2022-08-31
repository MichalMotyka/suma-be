package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepo extends CrudRepository<Country,Long> {

    Optional<Country> findByNameAndPrefix(String name,String prefix);
}
