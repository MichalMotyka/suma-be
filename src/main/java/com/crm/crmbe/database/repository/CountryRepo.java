package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepo extends CrudRepository<Country,Long> {

    Optional<Country> findByNameAndPrefixAndActive(String name,String prefix,String active);

    Optional<Country> findByNameAndActive(String name,String active);
}
