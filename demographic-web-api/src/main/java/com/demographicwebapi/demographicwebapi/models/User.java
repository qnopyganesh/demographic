package com.demographicwebapi.demographicwebapi.models;

import javax.persistence.*;

import lombok.*;

// import java.sql.Date;

@Table(name = "s_user")
@Entity
@Data
public class User {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "dob")
    private String dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "firstnameEncoded")
    private String firstnameEncoded;

    @Column(name = "lastnameEncoded")
    private String lastnameEncoded;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "email_id")
    private String email_id;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "contact")
    private String contact_number;

    public User(String firstname, String lastname, String dob, String gender, String address, String firstnameEncoded,
            String lastnameEncoded, Double latitude, Double longitude) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.firstnameEncoded = firstnameEncoded;
        this.lastnameEncoded = lastnameEncoded;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public User() {

    }

}
