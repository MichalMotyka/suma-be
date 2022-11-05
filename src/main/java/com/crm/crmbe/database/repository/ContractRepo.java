package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.Contract;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepo extends CrudRepository<Contract,Long> {

    Optional<Contract> findByStateAndContract(String state,Long contractor);
    Optional<Contract> findByUid(String uid);
}
