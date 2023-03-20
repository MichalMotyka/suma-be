package com.crm.crmbe.database.repository;

import com.crm.crmbe.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface UserRepo extends CrudRepository<User, String> {
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
    Optional<User> findById(String id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Users SET currentToken =?2 WHERE id = ?1", nativeQuery = true)
    void updateToken(String id, String token);
    @Query(value = "SELECT * FROM Users WHERE currentToken = ?1", nativeQuery = true)
    User findByCurrentToken(String token);
     Optional<User> findUsersByLogin(String login);
     Optional<User> findByRefreshToken(String token);
}
