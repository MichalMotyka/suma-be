package com.crm.crmbe.database.services;

import com.crm.crmbe.database.repository.PermissionRepo;
import com.crm.crmbe.entity.PermisionList;
import com.crm.crmbe.entity.Permission;
import com.crm.crmbe.entity.Role;
import com.crm.crmbe.entity.RoleList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public void savePermision(Permission permission){
        permissionRepo.save(permission);
    }

    public List<Role> mapPermisionsToRole(List<Permission> permissions){
        List<Role> roleList = new RoleList().roleList;
        permissions.forEach(permission -> {
            roleList.forEach(role -> {
                if (role.getName().equals(permission.getPermision())){
                    role.setActive(true);
                }
            });
        });
        return roleList;
    }

    public void updateRole(Permission permission, Role role) {
       Optional<Permission> permissionOptional = permissionRepo.findPermissionsByUserIDAndPermision(permission.getUserID(), permission.getPermision());
       if (permissionOptional.isPresent() && !role.isActive()){
           permission.setId(permissionOptional.get().getId());
           permissionRepo.delete(permission);
       } else if (role.isActive() && !permissionOptional.isPresent()) {
           permission.setId(UUID.randomUUID().toString());
           savePermision(permission);
       }
    }
}
