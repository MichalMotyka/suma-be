package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.ReadingItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingItemRepo extends CrudRepository<ReadingItem,Long> {

}
