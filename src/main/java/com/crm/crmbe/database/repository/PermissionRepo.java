package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepo extends CrudRepository<Permission, Long> {
     List<Permission> findPermissionsByUserID(String id);

     Optional<Permission> findPermissionsByUserIDAndPermision(String userID,String permision);
}
