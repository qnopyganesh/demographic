package com.demographicwebapi.demographicwebapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demographicwebapi.demographicwebapi.models.Login;
import com.demographicwebapi.demographicwebapi.repositories.LoginRepo;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepo loginRepo;

    public Login login(String username, String password) {
        Login user = loginRepo.findByUsername(username);
        return user.getPassword().compareTo(password) == 0 ?user:null;
    }

    public Login signUp(String username, String password,String contact, String address, String firstname,String lastname , String dob) {
        Login user = loginRepo.findByUsername(username);
        if (user == null) {
            user = new Login(username, password,contact,address,firstname,lastname,dob);
            loginRepo.save(user);
            return user;
        }
        return null;
    }
}
