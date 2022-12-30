package com.crm.crmbe.database.services;

import com.crm.crmbe.entity.Permission;
import com.crm.crmbe.entity.Role;
import com.crm.crmbe.entity.User;
import com.crm.crmbe.database.repository.UserRepo;
import com.crm.crmbe.entity.UserComponent;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServices {

   @Autowired
   private UserRepo userRepo;

   @Autowired
   private PermissionSerices permissionSerices;

    public User findByLogin(String login) {
       return userRepo.findByLogin(login);
    }

    public void save(User user) {
        userRepo.save(user);
    }

    public Optional<User> findByID(String id) {
        return userRepo.findById(id);
    }

    public void updateToken(String id, String token) {
        userRepo.updateToken(id, token);
    }

    public User findByToken(String token) {
        return userRepo.findByCurrentToken(token);
    }

    public User findByLoginAndPassword(User user) {
        return userRepo.findByLoginAndPassword(user.getLogin(), user.getPassword());
    }
    public boolean findIsUserExist(User user){
        return userRepo.findUsersByLogin(user.getLogin()).isPresent();
    }
    public List<User> findAllUser(){
        return (List<User>) userRepo.findAll();
    }

    public boolean createUser(UserComponent userComponent){
        if (userRepo.findUsersByLogin(userComponent.getName()).isPresent()){
            return false;
        }
        userComponent.setPassword(Hashing.sha512().hashString(userComponent.getPassword(), StandardCharsets.UTF_8).toString());
        userRepo.save(new User(UUID.randomUUID().toString(),userComponent.getName(),userComponent.getPassword(),"","",userComponent.getRoleName()));
        Permission permission = new Permission("", findByLogin(userComponent.getName()).getId(),null);
        System.out.println(userComponent.getRole().length);
        for (Role role:userComponent.getRole()) {
            if (role.isActive()) {
                permission.setId(UUID.randomUUID().toString());
                permission.setPermision(role.getName());
                permissionSerices.savePermision(permission);
            }
        }
        return true;
    }
}
