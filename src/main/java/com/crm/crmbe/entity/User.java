package com.crm.crmbe.entity;


import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.nio.charset.StandardCharsets;


@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@ToString
public class User {
    @Id
    @Column(name = "id")
    private String id;
    private String login;
    private String password;
    @Column(name = "currenttoken")
    private String currentToken;
    @Column(name="refreshtoken")
    private String refreshToken;
    private String role;
    private boolean active = true;
    public User(String id,String login, String password) {
        this.id = id;
        this.login = login;
        this.password = Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString();
        this.currentToken = "";
    }
    public User(){}

    public User(String login, String password) {
        this.login = login;
        this.password = Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString();
    }

    public User HashPassword() {
        this.password = Hashing.sha512().hashString(this.password, StandardCharsets.UTF_8).toString();
        return this;
    }
}
