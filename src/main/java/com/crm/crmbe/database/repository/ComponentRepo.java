package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.Component;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComponentRepo extends CrudRepository<Component,Long> {

    Optional<Component> findByNameAndActive(String name, String active);

    Iterable<Component> findByName(String data);
}
