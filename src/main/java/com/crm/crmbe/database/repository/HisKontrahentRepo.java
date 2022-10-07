package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.HistoricKontrahent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HisKontrahentRepo extends CrudRepository<HistoricKontrahent,Long> {
}
