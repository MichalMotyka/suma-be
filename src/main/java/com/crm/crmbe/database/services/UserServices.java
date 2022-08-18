package com.crm.crmbe.database.services;

import com.crm.crmbe.entity.User;
import com.crm.crmbe.database.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

   @Autowired
   private UserRepo userRepo;

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
}
