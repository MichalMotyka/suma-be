package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepo extends CrudRepository<Price,Long> {

    boolean existsByName(String name);
    @Query(value = "Select Distinct uid from price", nativeQuery = true)
    Iterable<String> findDistinctAll();

    Iterable<Price> findByUid(String uid);
    Iterable<Price> findDistinctByUid(String uid);
}
