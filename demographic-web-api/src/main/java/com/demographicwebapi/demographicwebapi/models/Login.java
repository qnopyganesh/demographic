package com.demographicwebapi.demographicwebapi.models;

import javax.persistence.*;

import lombok.*;

@Table(name = "login")
@Entity
@Data
public class Login {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    // @Column(name = "email")
    // private String email;

    @Column(name = "password")
    private String password;

    public Login() {
    }

    public Login(String username, String email, String password) {
        this.username = username;

        this.password = password;
    }

    public Login findByUsername(String username2) {
        return null;
    }

}
