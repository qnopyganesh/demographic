package com.demographicwebapi.demographicwebapi.models;

import javax.persistence.*;

import lombok.*;

@Table(name = "login", uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
@Entity
@Data
public class Login {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "contact")
    private String contact;

    @Column(name = "address")
    private String address;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "dob")
    private String dob;

    public Login() {
    }

    public Login(String username, String password, String contact, String address, String firstname, String lastname,
            String dob) {
        this.username = username;
        this.password = password;
        this.contact = contact;
        this.address = address;
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
