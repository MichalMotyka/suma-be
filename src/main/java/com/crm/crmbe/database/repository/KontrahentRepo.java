package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.Kontrahent;
import com.crm.crmbe.entity.KontrahentImpl;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KontrahentRepo extends CrudRepository<KontrahentImpl,Long> {
    Optional<Kontrahent> findByNumerKlienta(String numerKlienta);
    Optional<Kontrahent> findByPpe(String ppe);
    Iterable<Kontrahent> findByNumerKlientaOrNazwa(String NumerKlienta,String nazwa);
}
