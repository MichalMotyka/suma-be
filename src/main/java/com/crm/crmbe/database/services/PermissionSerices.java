package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.PermissionRepo;
import com.crm.crmbe.entity.PermisionList;
import com.crm.crmbe.entity.Permission;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionSerices {
    @Autowired
    private  PermissionRepo permissionRepo;

    public List<Permission> findPermisionByUserId(String id){
        return permissionRepo.findPermissionsByUserID(id);
    }
    public Optional<Permission> findPermisionByUserIdAndPermision(String userid, PermisionList permission){
        return  permissionRepo.findPermissionsByUserIDAndPermision(userid,permission.toString());
    }
}
