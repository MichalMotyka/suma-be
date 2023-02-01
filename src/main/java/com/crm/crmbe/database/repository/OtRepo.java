package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.OT;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtRepo extends CrudRepository<OT,Long> {

    Optional<OT> findByUid(String uid);

    Optional<OT> findOTByPp(Long pp);
    Optional<OT> findOTByConctrator(Long id);
    Optional<OT> findOTByContract(Long id);
}
