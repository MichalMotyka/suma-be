package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.pp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PpRepo extends CrudRepository<pp,Long> {

    Optional<pp> findByContractor(Long contract);
    Iterable<pp> findByStatus(String status);
    Optional<pp> findByUid(String uid);

}
