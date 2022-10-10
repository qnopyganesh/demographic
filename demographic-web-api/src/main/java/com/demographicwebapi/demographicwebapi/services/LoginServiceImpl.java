package com.demographicwebapi.demographicwebapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demographicwebapi.demographicwebapi.models.Login;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private Login loginRepo;

    public Login login(String username, String password) {
        Login user = loginRepo.findByUsername(username);

        if (user.getPassword() == password) {
            return user;
        }

        return null;
    }

}
