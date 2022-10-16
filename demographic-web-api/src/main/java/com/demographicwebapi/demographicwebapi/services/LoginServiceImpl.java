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

        if (user.getPassword() == password) {
            return user;
        }
        return null;
    }

    public Login signUp(String username, String password) {
        Login user = loginRepo.findByUsername(username);
        if (user == null) {
            user = new Login(username, password);
            loginRepo.save(user);
            return user;
        }
        return null;
    }
}
