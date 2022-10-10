package com.demographicwebapi.demographicwebapi.services;

import com.demographicwebapi.demographicwebapi.models.Login;

public interface LoginService {

    public Login login(String username, String password);

}
