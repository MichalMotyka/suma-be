package com.crm.crmbe.entity;


import com.google.common.hash.Hashing;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.nio.charset.StandardCharsets;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private String id;
    private String login;
    private String password;
    private String permission;
    @Column(name = "currenttoken")
    private String currentToken;

    public User(String id,String login, String password, String permission) {
        this.id = id;
        this.login = login;
        this.password = Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString();
        this.permission = permission;
        this.currentToken = "";
    }
    public User(){}

    public User(String login, String password) {
        this.login = login;
        this.password = Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString();
    }

    public static void HashPassword(User user) {
        user.setPassword(Hashing.sha512().hashString(user.getPassword(), StandardCharsets.UTF_8).toString());
    }
}
