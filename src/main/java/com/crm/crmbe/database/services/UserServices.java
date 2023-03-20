package com.crm.crmbe.database.services;

import com.crm.crmbe.entity.Permission;
import com.crm.crmbe.entity.Role;
import com.crm.crmbe.entity.User;
import com.crm.crmbe.database.repository.UserRepo;
import com.crm.crmbe.entity.UserComponent;
import com.google.common.hash.Hashing;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;

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
    public User findByRefreshToken(String token){
        Optional<User> optionalUser =  userRepo.findByRefreshToken(token);
        return optionalUser.orElse(null);
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
        userRepo.save(new User(UUID.randomUUID().toString(),userComponent.getName(),userComponent.getPassword(),"","",userComponent.getRoleName(),true));
        Permission permission = new Permission("", findByLogin(userComponent.getName()).getId(),null);
        for (Role role:userComponent.getRole()) {
            if (role.isActive()) {
                permission.setId(UUID.randomUUID().toString());
                permission.setPermision(role.getName());
                permissionSerices.savePermision(permission);
            }
        }
        return true;
    }
    public UserComponent getUserComponent(User user){
        Role[] role = permissionSerices.mapPermisionsToRole(permissionSerices.findPermisionByUserId(user.getId())).toArray(new Role[0]);
        UserComponent userComponent = new UserComponent(user.getId(),user.getLogin(), user.getRole(),role);
        return userComponent;
    }
    public boolean updateIfExist(UserComponent userComponent){
        Optional<User> exUser = userRepo.findById(userComponent.getId());
        if (exUser.isPresent()){
            User user = new User();
            if (userComponent.getPassword() == null){
                user.setPassword(exUser.get().getPassword());
            }else{
                user.setPassword(Hashing.sha512().hashString(userComponent.getPassword(), StandardCharsets.UTF_8).toString());
            }
            user.setId(userComponent.getId());
            user.setRole(userComponent.getRoleName());
            user.setLogin(userComponent.getName());
            user.setCurrentToken(exUser.get().getCurrentToken());
            userRepo.save(user);
            Arrays.stream(userComponent.getRole()).forEach(value ->{
                permissionSerices.updateRole(new Permission("",user.getId(),value.getName()),value);
            });
            return true;
        }
        return false;
    }

    public boolean deleteIfExist(String id){
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()){
            User newUser = user.get();
            newUser.setActive(false);
            userRepo.save(newUser);
            return true;
        }
        return false;
    }
}
