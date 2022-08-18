package com.demographicwebapi.demographicwebapi.models;

import lombok.Data;

@Data
public class Person implements Comparable<Person>{
    Integer className;
    String firstName; 
    String surName ; 
    String encoded;
    String gender;

    Person(){

    }

    Person(Integer className, String firstName, String surName, String encoded , String gender){
        this.className = className;
        this.firstName  = firstName;
        this.surName = surName;
        this.encoded = encoded;
        this.gender = gender;
    }

    @Override
    public int compareTo(Person person) {
        return this.firstName.compareTo(person.firstName);
    }


}
