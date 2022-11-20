package com.demographicwebapi.demographicwebapi.services;

import com.demographicwebapi.demographicwebapi.models.Login;

public interface LoginService {

    public Login login(String username, String password);

    public Login signUp(String username, String password,String contact,String address, String firstname, String lastname, String dob);

}
